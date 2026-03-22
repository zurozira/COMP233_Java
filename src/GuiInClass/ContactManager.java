package GuiInClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

/**
 * GUI for a contact manager application
 * Allow user to generate, add, view and delete contacts
 */
public class ContactManager extends JFrame {

    private JTextArea txtName;
    private JTextArea txtNumber;
    private JTextArea txtEmail;

    private JLabel lblName;
    private JLabel lblNumber;
    private JLabel lblEmail;

    private JButton btnSave;
    private JButton btnDeleteAll;
    private JButton btnDelete;
    private JButton btnGenerateData;

    private JPanel controlContainer;
    private JPanel buttonsContainer;

    private JTable tbTable;
    private JScrollPane spScroll;
    private DefaultTableModel model;

    /**
     * Creates the contact manager window and populates it with controls
     */
    public ContactManager() {

        super("Contact Manager");

        setSize(500, 400);

        txtName = new JTextArea(1, 10);
        txtNumber = new JTextArea(1, 10);
        txtEmail = new JTextArea(1, 10);

        lblName = new JLabel("Name: ");
        lblNumber = new JLabel("Number: ");
        lblEmail = new JLabel("Email: ");

        // I do this to align the labels and the text boxes nicely.
        int width = lblNumber.getPreferredSize().width; // because Number has the most letters (widest)
        int height = lblNumber.getPreferredSize().height;
        lblName.setPreferredSize(new Dimension(width, height));
        lblEmail.setPreferredSize(new Dimension(width, height));

        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete this row");
        btnDeleteAll = new JButton("Delete all");
        btnGenerateData = new JButton("Generate data");

        /* Panel for the form */
        controlContainer = new JPanel();
        BoxLayout cContainerBox = new BoxLayout(controlContainer, BoxLayout.Y_AXIS);
        controlContainer.setLayout(cContainerBox);

        JPanel titleRow = new JPanel();
        titleRow.add(new JLabel("Contact Manager - COMP233"));

        JPanel firstRow = new JPanel();
        firstRow.add(lblName);
        firstRow.add(txtName);

        JPanel secondRow = new JPanel();
        secondRow.add(lblNumber);
        secondRow.add(txtNumber);

        JPanel thirdRow = new JPanel();
        thirdRow.add(lblEmail);
        thirdRow.add(txtEmail);

        JPanel fourthRow = new JPanel();
        fourthRow.add(btnSave);

        controlContainer.add(titleRow);
        controlContainer.add(firstRow);
        controlContainer.add(secondRow);
        controlContainer.add(thirdRow);
        controlContainer.add(fourthRow);

        /* Table to display data */
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Number");
        model.addColumn("Email");

        tbTable = new JTable(model);

        spScroll = new JScrollPane(tbTable);
        spScroll.setPreferredSize(new Dimension(0,200));

        /* Bottom panel for buttons and footer */
        buttonsContainer = new JPanel();
        BoxLayout bContainerBox = new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS);
        buttonsContainer.setLayout(bContainerBox);

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.add(btnGenerateData);
        buttonRow.add(btnDeleteAll);
        buttonRow.add(btnDelete);

        JPanel myFooter = new JPanel();
        myFooter.add(new JLabel("2026 - Cong Vu Bui"));

        buttonsContainer.add(buttonRow);
        buttonsContainer.add(myFooter);

        /* Add the components to the main frame */
        add(controlContainer, BorderLayout.NORTH);
        add(spScroll, BorderLayout.CENTER);
        add(buttonsContainer, BorderLayout.SOUTH);


        /*
         * Save button: to add a new contact from input fields to the table
         * Show confirmation if any field is blank
         */
        btnSave.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        if (txtName.getText().isBlank() || txtNumber.getText().isBlank() || txtEmail.getText().isBlank()) {
                            int choice = JOptionPane.showConfirmDialog(null,
                                    "One or more entries is blank, are you sure?",
                                    "Message",
                                    JOptionPane.YES_NO_OPTION);

                            if (choice == JOptionPane.YES_OPTION) {
                                saveData();
                                JOptionPane.showMessageDialog(null, "Contact saved");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Contact saved");
                            saveData();
                        }
                    }
                }
        );

        /*
         * Generate data button: prompt user for a number
         * then generate that amount of contacts
         */
        btnGenerateData.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String amountString = JOptionPane.showInputDialog("How many contact do you want to generate?");
                        if (amountString != null && !amountString.isBlank()) {
                            try {
                                int amount = Integer.parseInt(amountString);
                                generateData(amount);
                            }
                            catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Failed, invalid input");
                            }
                        }
                    }
                }
        );

        /*
         * Delete all button: remove all rows from the table after confirmation.
         * Require the table is not empty
         */
        btnDeleteAll.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (model.getRowCount() > 0) {
                            int choice = JOptionPane.showConfirmDialog(null,
                                    "Delete all contact, are you sure?",
                                    "Message",
                                    JOptionPane.YES_NO_OPTION);

                            if (choice == JOptionPane.YES_OPTION) {
                                deleteAll();
                                JOptionPane.showMessageDialog(null, "Done!");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "There is no contact to delete");
                        }

                    }
                }
        );

        /*
         * Delete button: remove the currently selected row
         * require a row is selected or the table is not empty
         */
        btnDelete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (tbTable.getSelectedRow() != -1) {
                            int choice = JOptionPane.showConfirmDialog(null,
                                    "Delete this contact?",
                                    "Message",
                                    JOptionPane.YES_NO_OPTION);

                            if (choice == JOptionPane.YES_OPTION) {
                                deleteData(model);
                                JOptionPane.showMessageDialog(null, "Done!");
                            }
                        }
                        else {
                            if (model.getRowCount() > 0) {
                                JOptionPane.showMessageDialog(null, "Please select a contact");
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "There is no contact to delete");
                            }
                        }
                    }
                }
        );

        setVisible(true);
    }

    public static void main(String[] args) {
        ContactManager cM = new ContactManager();
    }

    /**
     * Take the data provided in the text boxes and create
     * a new contact in the print label
     */
    public void saveData() {
        String name = txtName.getText();
        String number = txtNumber.getText();
        String email = txtEmail.getText();

        model.addRow(new Object[]{name, number, email});

        txtName.setText("");
        txtNumber.setText("");
        txtEmail.setText("");
    }

    /*
    * Removes all row from the table.
    * Loop backwards to avoid index error
    * */
    public void deleteAll() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    /**
     * Generate a number of contact
     * @param amount the number of contact to generate
     */
    public void generateData(int amount) {

        String name;
        String email;
        char c = 'a';

        for (int i = 0; i < amount; i++) {
            name = String.format("%c", c + i);
            email = String.format("%c@gmail.com", c + i);

            model.addRow(new Object[]{name, i, email});
        }
    }

    /**
     * Remove the currently selected row from the table
     * @param tableModel the table to delete a row from
     */
    public void deleteData(DefaultTableModel tableModel) {
        model.removeRow(tbTable.getSelectedRow());
    }
}
