import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyWidget extends JPanel {

    public CurrencyWidget() {

        setBorder(new EmptyBorder(5, 20, 200, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Combo Box for Currency Selection
        String[] currencyArray = CurrencyInterface.getInstance().getCurrenciesNeatArray().toArray(new String[0]);
        JComboBox selector = new JComboBox(currencyArray);

        // Currency Symbol Label
        JLabel symbol = new JLabel("$");
        symbol.setFont(new Font("Sans-Serif", Font.BOLD, 144));
        symbol.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Currency Symbol Label
        JLabel name = new JLabel("Very Dickman");
        name.setFont(new Font("Sans-Serif", Font.PLAIN, 24));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Number Box
        JFormattedTextField input = new JFormattedTextField(new DecimalFormat("#0.#"));
        input.setHorizontalAlignment(SwingConstants.CENTER);
        input.setFont(new Font("Sans-Serif", Font.PLAIN, 16));

        // add listener FOR ACTIONS
        selector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(selector.getSelectedItem().toString());
            }
        });

        // Add Components
        add(selector);
        add(symbol);
        add(name);
        add(Box.createRigidArea(new Dimension(20, 20)));
        add(input);
    }
}
