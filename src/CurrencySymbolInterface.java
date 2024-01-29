import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class CurrencySymbolInterface {

    private static final String filename = "src/symbols.json";
    private HashMap<String, String> symbols;

    public CurrencySymbolInterface() {
        loadCurrencySymbols();
    }

    private void loadCurrencySymbols() {
        try {
            // Read the content of the file into a string using UTF-8 encoding
            String jsonContent = Files.readString(Paths.get(filename));
            Gson gson = new Gson();
            symbols = gson.fromJson(jsonContent, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSymbol(String code) {
        if (symbols.containsKey(code)) {
            return symbols.get(code);
        }
        return "¤";
    }
}
