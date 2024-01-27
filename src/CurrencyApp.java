import javax.swing.*;
import java.io.IOException;

public class CurrencyApp {
    public static void main(String[] args) throws IOException {
        // Create Application Frame and Run
        SwingUtilities.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
