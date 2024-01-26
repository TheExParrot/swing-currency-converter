import javax.swing.*;
import java.awt.*;

public class CurrencyWidget extends JPanel {

    public CurrencyWidget() {
        // columns panel
        super(new GridLayout(1, 2));
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        this.add(leftPanel);
        this.add(rightPanel);
    }

}
