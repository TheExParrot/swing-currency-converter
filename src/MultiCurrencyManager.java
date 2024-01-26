import javax.swing.*;
import java.awt.*;

public class MultiCurrencyManager extends JPanel {

    private static final int NUM_CURRENCIES = 2;
    private final CurrencyWidget[] currencyWidgets;

    public MultiCurrencyManager() {
        // columns panel
        super(new GridLayout(1, NUM_CURRENCIES));

        // create num currency widgets
        currencyWidgets = new CurrencyWidget[NUM_CURRENCIES];
        for (int i = 0; i < NUM_CURRENCIES; i++) {
            currencyWidgets[i] = new CurrencyWidget();
            this.add(currencyWidgets[i]);
        }
    }

}
