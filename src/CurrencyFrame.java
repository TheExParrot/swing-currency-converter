import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyFrame extends JFrame {

    private final static int WINDOW_WIDTH = 800;
    private final static int WINDOW_HEIGHT = 600;
    /* Constructor */
    public CurrencyFrame() {
        // setup frame
        setTitle("Currency Converter");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

}
