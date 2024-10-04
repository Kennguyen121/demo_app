package nhap1;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabbedPaneExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tabbed Pane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Tạo một JTabbedPane
        final JTabbedPane tabbedPane = new JTabbedPane();

        // Tạo một JButton để thêm tab mới
        JButton addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel newTab = new JPanel();
                newTab.add(new JLabel("New Tab"));
                tabbedPane.addTab("Tab " + (tabbedPane.getTabCount() + 1), newTab);
            }
        });

        // Thêm nút thêm tab mới vào JFrame
        frame.add(addButton, BorderLayout.NORTH);

        // Tạo các JPanel cho các tab mặc định
        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();

        // Thêm nội dung cho mỗi tab
        tab1.add(new JLabel("This is tab 1"));
        tab2.add(new JLabel("This is tab 2"));

        // Thêm tab mặc định vào JTabbedPane
        tabbedPane.addTab("Tab 1", tab1);
        tabbedPane.addTab("Tab 2", tab2);

        // Thêm JTabbedPane vào JFrame
        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
