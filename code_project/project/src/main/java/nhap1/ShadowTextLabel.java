package nhap1;

import javax.swing.*;
import java.awt.*;

public class ShadowTextLabel extends JPanel {

    private String text;
    private Color textColor;
    private Color shadowColor;
    private int textSize; // New variable to store text size

    public ShadowTextLabel(String text, Color textColor, Color shadowColor, int textSize) {
        this.text = text;
        this.textColor = textColor;
        this.shadowColor = shadowColor;
        this.textSize = textSize; // Initialize text size
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints for smoother text
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create font with specified size
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, textSize);

        // Calculate text size and position
        FontMetrics fm = g2d.getFontMetrics(font);
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int x = (getWidth() - textWidth) / 2;
        int y = ((getHeight() - textHeight) / 2) + fm.getAscent();

        // Draw shadow text
        g2d.setColor(shadowColor);
        g2d.setFont(font); // Set font
        g2d.drawString(text, x + 2, y + 2);

        // Draw actual text
        g2d.setColor(textColor);
        g2d.drawString(text, x, y);
    }

    // Example usage
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Shadow Text Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);

            // Create a ShadowText component with a larger text size
            ShadowTextLabel shadowText = new ShadowTextLabel("Hello, Shadow!", Color.BLUE, Color.GRAY, 50);
            frame.add(shadowText);

            frame.setVisible(true);
        });
    }
}
