import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;

    /* Constructor */
    public AppFrame() {
        // Setup Frame
        setTitle("Currency Converter");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        setFocusTraversalPolicy(new LayoutFocusTraversalPolicy());

        // Add Application Label
        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Currency Converter");
        topPanel.add(titleLabel);

        // Add Currency Widget
        MultiCurrencyManager currencyWidget = new MultiCurrencyManager();

        // Set up the main content pane with BorderLayout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(currencyWidget, BorderLayout.CENTER);
    }

}
