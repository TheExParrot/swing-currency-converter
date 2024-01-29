import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

public class CurrencyWidget extends JPanel {

    private final CurrencySymbolInterface currencySymbolInterface = new CurrencySymbolInterface();
    private final MultiCurrencyManager manager;

    private String currentNeatCurrency;
    private String currentCurrency;
    private double currentValue;
    private JTextField inputField;
    private boolean hasBeenSelectedOnce;


    public CurrencyWidget(MultiCurrencyManager manager) {
        this.manager = manager;

        setBorder(new EmptyBorder(5, 20, 50000, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Combo Box for Currency Selection
        String[] currencyArray
                = SingletonCurrencyInterface.getInstance().getCurrenciesNeatArray().toArray(new String[0]);
        JComboBox selector = new JComboBox(currencyArray);
        hasBeenSelectedOnce = false;

        // Currency Symbol Label
        JLabel symbol = new JLabel("¤");
        symbol.setFont(new Font("Sans-Serif", Font.BOLD, 144));
        symbol.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Currency Name
        JLabel name = new JLabel("No Currency Selected");
        name.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Number Box
        JFormattedTextField valueInput = new JFormattedTextField(new DecimalFormat("#0.#"));
        this.inputField = valueInput;
        valueInput.setHorizontalAlignment(SwingConstants.CENTER);
        valueInput.setFont(new Font("Sans-Serif", Font.PLAIN, 16));

        // add listener for selector
        selector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update Currency Selection
                if (!hasBeenSelectedOnce) {
                    hasBeenSelectedOnce = true;
                }
                currentNeatCurrency = selector.getSelectedItem().toString();
                name.setText(currentNeatCurrency);
                currentCurrency = SingletonCurrencyInterface.getInstance().getCodeFromNeat(currentNeatCurrency);
                symbol.setText(currencySymbolInterface.getSymbol(currentCurrency));
            }
        });

        // add listener for number input box
        valueInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentValue = Double.parseDouble(valueInput.getValue().toString());
                manager.update(currentCurrency, currentValue);
            }
        });

        // Add Components
        add(selector);
        add(symbol);
        add(name);
        add(Box.createRigidArea(new Dimension(20, 20)));
        add(valueInput);
    }

    public String getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(String currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double newValue) {
        this.currentValue = newValue;
        String txt = String.format("%.6f", currentValue);
        this.inputField.setText(txt);
    }

    public boolean hasBeenSelectedOnce() {
        return hasBeenSelectedOnce;
    }
}
