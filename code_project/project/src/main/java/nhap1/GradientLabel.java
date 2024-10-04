package nhap1;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class GradientLabel extends JLabel {
    public GradientLabel() {
        setText("Happy, Happy");
        setForeground(Color.WHITE);
        setHorizontalAlignment(CENTER);
        setOpaque(true); // Make the label opaque to show the gradient
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        // Define the gradient points
        Point2D start = new Point(0, 0);
        Point2D end = new Point(0, getHeight());

        // Define the gradient colors
        Color color1 = Color.BLUE;
        Color color2 = Color.GREEN;

        // Create the LinearGradientPaint
        LinearGradientPaint lgp = new LinearGradientPaint(start, end, new float[]{0f, 1f}, new Color[]{color1, color2});

        // Fill the label background with the gradient
        g2d.setPaint(lgp);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gradient Label Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        GradientLabel label = new GradientLabel();
        frame.add(label);

        frame.setVisible(true);
    }
}
