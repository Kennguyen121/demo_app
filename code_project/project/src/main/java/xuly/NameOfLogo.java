package xuly;

import javax.swing.*;
import java.awt.*;

public class NameOfLogo extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
    private Color textColor;
    private Color shadowColor;
    private int textSize; // New variable to store text size
	private static final Color BG_COLOR = new Color(251, 227, 248);
    public NameOfLogo(String text, Color textColor, Color shadowColor, int textSize) {
        this.text = text;
        this.textColor = textColor;
        this.shadowColor = shadowColor;
        this.textSize = textSize; // Initialize text size
        setBackground(BG_COLOR);
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

}
