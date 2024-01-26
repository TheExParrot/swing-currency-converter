import javax.swing.*;

public class CurrencyApp {
    public static void main(String[] args) {
        // Create Application Frame and Run
        SwingUtilities.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

        String out = CurrencyInterface.getInstance().GetCurrencies();
        System.out.println(out);
    }
}
