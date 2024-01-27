import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashMap;
import com.google.gson.Gson;

public class CurrencyInterface {

    private static final CurrencyInterface instance;

    private static final String API_URL
            = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/";

    private Set<String> currenciesSet;
    private ArrayList<String> currenciesNeatArrayList;
    private HashMap<String, String> currencyCodes;
    private HashMap<String, String> neatNameToCode;

    private HashMap<String, Double> conversionCache;

    static {
        try {
            instance = new CurrencyInterface();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CurrencyInterface() throws IOException {
        // fetch currency codes & names
        currencyCodes = fetchCurrencies();
        currenciesSet = currencyCodes.keySet();

        // setup neat names list
        currenciesNeatArrayList = new ArrayList<String>();
        neatNameToCode = new HashMap<String, String>();
        for (String code: currenciesSet) {
            String curName;
            if (currencyCodes.get(code).equals("")) {
                curName = code.toUpperCase();
            } else {
                curName =  code.toUpperCase() + " (" + currencyCodes.get(code) + ")";
            }
            currenciesNeatArrayList.add(curName);
            neatNameToCode.put(curName, code);
        }
        Collections.sort(currenciesNeatArrayList);

        // initialise conversion cache
        conversionCache = new HashMap<>();
    }

    private HashMap<String, String> fetchCurrencies() throws IOException {
        // get HTTP Request
        StringBuilder result = new StringBuilder();
        URL url = new URL(API_URL + "currencies.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Read raw JSON String
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader((conn.getInputStream())))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        String rawJson = result.toString();

        // Convert to HashMap
        Gson gson = new Gson();
        return gson.fromJson(rawJson, HashMap.class);
    }

    public ArrayList<String> getCurrenciesNeatArray() {
        return currenciesNeatArrayList;
    }

    public static CurrencyInterface getInstance() {
        return instance;
    }

    public double convertCurrency(String src, double amount, String out) throws IOException {
        // check if both currencies are valid currencies, if not return negative value
        if (!currenciesSet.contains(src) || !currenciesSet.contains(out)) {
            return -1.0;
        }

        double conversionRate;

        // check if conversion is already in cache
        String concat = src + out;
        if (conversionCache.containsKey(concat)) {
            conversionRate = conversionCache.get(concat);
        } else {
            // if not in cache, fetch from API and add to cache (both ways)
            conversionRate = fetchConversionRate(src, out);
            conversionCache.put(concat, conversionRate);
            conversionCache.put(out + src, 1 / conversionRate);
        }
        return amount * conversionRate;
    }

    private double fetchConversionRate(String src, String out) throws IOException {
        // get HTTP Request
        StringBuilder result = new StringBuilder();
        URL url = new URL(API_URL + "currencies/" + src + "/" + out + ".json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Read raw JSON String
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader((conn.getInputStream())))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        String rawJson = result.toString();

        // Convert to HashMap and return result rate
        Gson gson = new Gson();
        HashMap<String, Double> mapResult = gson.fromJson(rawJson, HashMap.class);
        return mapResult.get(out);
    }

}
