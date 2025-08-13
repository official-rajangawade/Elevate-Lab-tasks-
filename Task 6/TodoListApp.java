package TODO;

import javax.swing.*;
import java.awt.*;

public class TodoListApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoListApp().createGUI());
    }

    private void createGUI() {
        setUIFont(new Font("Times New Roman", Font.PLAIN, 16));

        JFrame frame = new JFrame("To-Do List");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextField taskField = new JTextField();
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");

        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(deleteButton);

        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to delete.");
            }
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }

    private static void setUIFont(Font font) {
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);

        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof Font) {
                UIManager.put(key, font);
            }
        }
    }
}
