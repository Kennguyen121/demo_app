package nhap1;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class RainbowExample extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        // Define the colors of the rainbow
        Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(75,0,130), new Color(128,0,128) };

        // Define the positions for the colors
        float[] fractions = { 0.0f, 1.0f / 6, 2.0f / 6, 3.0f / 6, 4.0f / 6, 5.0f / 6, 1.0f };

        // Create a gradient paint
        LinearGradientPaint gradientPaint = new LinearGradientPaint(
                new Point2D.Float(0, 0), new Point2D.Float(getWidth(), 0),
                fractions, colors);

        // Set the paint to the graphics context
        g2d.setPaint(gradientPaint);

        // Fill a rectangle with the gradient
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rainbow Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.getContentPane().add(new RainbowExample());
        frame.setVisible(true);
    }
}
