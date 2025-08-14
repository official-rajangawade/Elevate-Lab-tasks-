package EMP_MGT;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmpSystemGUI extends JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Rajan@9130";

    private Connection conn;
    private JTextField nameField, designationField, salaryField, idField;
    private DefaultTableModel tableModel;
    private JTable table;

    public EmpSystemGUI() {

       
        setUIFont(new javax.swing.plaf.FontUIResource("Times New Roman", Font.PLAIN, 16));

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Connected to Database!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed!");
            e.printStackTrace();
            System.exit(1);
        }

        setTitle("Employee Management System");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        formPanel.add(new JLabel("ID (for Update/Delete):"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Designation:"));
        designationField = new JTextField();
        formPanel.add(designationField);

        formPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        formPanel.add(salaryField);


        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View All");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        formPanel.add(buttonPanel);
        add(formPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Designation", "Salary"}, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
        table.setRowHeight(22);
        add(new JScrollPane(table), BorderLayout.CENTER);


        addBtn.addActionListener(e -> addEmployee());
        viewBtn.addActionListener(e -> viewEmployees());
        updateBtn.addActionListener(e -> updateEmployee());
        deleteBtn.addActionListener(e -> deleteEmployee());
    }


    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    private void addEmployee() {
        try {
            String name = nameField.getText();
            String designation = designationField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            String sql = "INSERT INTO employees (name, designation, salary) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, designation);
            pst.setDouble(3, salary);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Employee Added Successfully!");
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Adding Employee!");
            e.printStackTrace();
        }
    }

    private void viewEmployees() {
        try {
            tableModel.setRowCount(0);
            String sql = "SELECT * FROM employees";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("designation"),
                        rs.getDouble("salary")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error Loading Employees!");
            e.printStackTrace();
        }
    }

    private void updateEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String designation = designationField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            String sql = "UPDATE employees SET name=?, designation=?, salary=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, designation);
            pst.setDouble(3, salary);
            pst.setInt(4, id);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Employee Updated!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Employee ID Not Found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Updating Employee!");
            e.printStackTrace();
        }
    }

    private void deleteEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());

            String sql = "DELETE FROM employees WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Employee Deleted!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Employee ID Not Found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Deleting Employee!");
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        designationField.setText("");
        salaryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmpSystemGUI().setVisible(true);
        });
    }
}
