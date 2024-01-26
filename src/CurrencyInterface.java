import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.HashMap;
import com.google.gson.Gson;

public class CurrencyInterface {

    private static final CurrencyInterface instance;

    private static final String API_URL
            = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/";

    private Set<String> currencies;
    private HashMap<String, String> currencyCodes;

    static {
        try {
            instance = new CurrencyInterface();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CurrencyInterface() throws IOException {
        currencyCodes = FetchCurrencies();
        currencies = currencyCodes.keySet();
    }

    private HashMap<String, String> FetchCurrencies() throws IOException {
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

    public String GetCurrencies() {
        return currencies.toString();
    }

    public static CurrencyInterface getInstance() {
        return instance;
    }

}
