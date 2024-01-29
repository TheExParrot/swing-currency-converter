import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyWidget extends JPanel {

    private String currentNeatCurrency;
    private String currentCurrency;
    private double currentValue;

    private final CurrencySymbolInterface currencySymbolInterface = new CurrencySymbolInterface();


    public CurrencyWidget() {

        setBorder(new EmptyBorder(5, 20, 200, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Combo Box for Currency Selection
        String[] currencyArray = SingletonCurrencyInterface.getInstance().getCurrenciesNeatArray().toArray(new String[0]);
        JComboBox selector = new JComboBox(currencyArray);

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
        valueInput.setHorizontalAlignment(SwingConstants.CENTER);
        valueInput.setFont(new Font("Sans-Serif", Font.PLAIN, 16));

        // add listener for selector
        selector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        // Add Components
        add(selector);
        add(symbol);
        add(name);
        add(Box.createRigidArea(new Dimension(20, 20)));
        add(valueInput);
    }
}
