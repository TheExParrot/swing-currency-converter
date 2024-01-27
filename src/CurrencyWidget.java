import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CurrencyWidget extends JPanel {

    public CurrencyWidget() {
        String[] currencyArray = CurrencyInterface.getInstance().getCurrenciesNeatArray().toArray(new String[0]);
        JComboBox selector = new JComboBox(currencyArray);
        add(selector);
    }
}
