/*public class Main {
    public static void main(String[] args) {
        // This will start the Swing UI in a separate window
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ResumeAnalyzerUI(); // Open the UI
            }
        });
    }
}
*/
public class Main {
    public static void main(String[] args) {
        // This will start the Swing UI in a separate window
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage();  // Open the LoginPage
            }
        });
    }
}
