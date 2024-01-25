import javax.swing.*;

public class CurrencyApp {
    public static void main(String[] args) {
        // Create Application Frame and Run
        SwingUtilities.invokeLater(() -> {
            CurrencyFrame frame = new CurrencyFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
