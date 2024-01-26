import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;

public class CurrencyInterface {

    private static final CurrencyInterface instance;

    private static final String API_URL
            = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/";

    private HashSet<String> currencies;
    private String rawCurrencies;

    static {
        try {
            instance = new CurrencyInterface();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CurrencyInterface() throws Exception {
        this.rawCurrencies = FetchCurrenciesJSONRaw();
    }

    private String FetchCurrenciesJSONRaw() throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(API_URL + "currencies.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader((conn.getInputStream())))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        return result.toString();
    }

    public String GetCurrencies() {
        return rawCurrencies;
    }

    public static CurrencyInterface getInstance() {
        return instance;
    }

}
