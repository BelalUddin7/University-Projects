import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GadgetShopGUI extends JFrame implements ActionListener{ // Class representing the GUI for a Gadget Shop
    private ArrayList<Gadget> gadgets; // ArrayList to store gadgets
    private JTextField modelTextbox, priceTextbox, weightTextbox, sizeTextbox, creditTextbox, memoryTextbox, phoneNumberTextbox, durationTextbox, downloadSizeTextbox;
    // Text fields for various gadget attributes
    
    private JTextArea txtDisplay; // Text area to display gadget information
    private JComboBox<String> cmbDisplayNumber; // Combo box to select display number
    private JButton addMobileButton, addMP3Button, clearButton, displayButton, makeCallButton, downloadMusicButton; // Buttons for different actions

    public GadgetShopGUI(){ // Constructor for initializing the GUI
        gadgets = new ArrayList<>(); // Initialize gadgets ArrayList

        setTitle("Gadget Shop"); // Set title of the frame
        setSize(500, 600); // Set size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setLayout(new GridLayout(7, 2)); // Set layout for the frame

        // Add labels and text fields for various gadget attributes
        JLabel lblModel = new JLabel("Model:"); 
        add(lblModel);
        modelTextbox = new JTextField();
        add(modelTextbox);

        JLabel lblPrice = new JLabel("Price:");
        add(lblPrice);
        priceTextbox = new JTextField();
        add(priceTextbox);

        JLabel lblWeight = new JLabel("Weight:");
        add(lblWeight);
        weightTextbox = new JTextField();
        add(weightTextbox);

        JLabel lblSize = new JLabel("Size:");
        add(lblSize);
        sizeTextbox = new JTextField();
        add(sizeTextbox);

        JLabel lblCredit = new JLabel("Credit:");
        add(lblCredit);
        creditTextbox = new JTextField();
        add(creditTextbox);

        JLabel lblMemory = new JLabel("Memory:");
        add(lblMemory);
        memoryTextbox = new JTextField();
        add(memoryTextbox);

        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        add(lblPhoneNumber);
        phoneNumberTextbox = new JTextField();
        add(phoneNumberTextbox);

        JLabel lblDuration = new JLabel("Duration:");
        add(lblDuration);
        durationTextbox = new JTextField();
        add(durationTextbox);

        JLabel lblDownloadSize = new JLabel("Download Size:");
        add(lblDownloadSize);
        downloadSizeTextbox = new JTextField();
        add(downloadSizeTextbox);

        JLabel lblDisplayNumber = new JLabel("Display Number:");
        add(lblDisplayNumber);
        String[] displayNumbers = {"-1", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        cmbDisplayNumber = new JComboBox<>(displayNumbers);
        add(cmbDisplayNumber);

        txtDisplay = new JTextArea();
        add(txtDisplay);

        // Add buttons for different actions
        addMobileButton = new JButton("Add Mobile");
        addMobileButton.addActionListener(this);
        add(addMobileButton);

        addMP3Button = new JButton("Add MP3");
        addMP3Button.addActionListener(this);
        add(addMP3Button);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        add(clearButton);

        displayButton = new JButton("Display All");
        displayButton.addActionListener(this);
        add(displayButton);

        makeCallButton = new JButton("Make A Call");
        makeCallButton.addActionListener(this);
        add(makeCallButton);

        downloadMusicButton = new JButton("Download Music");
        downloadMusicButton.addActionListener(this);
        add(downloadMusicButton);

        setVisible(true); // Set visibility of the frame
    }

    @Override // Method called when an action is performed
    public void actionPerformed(ActionEvent e){ // Handle different actions based on the event source
        if (e.getSource() == addMobileButton) {
            String model = getModel();
            double price = getPrice();
            int weight = getWeight();
            String size = getGadgetsSize();
            int credit = getCredit();
            gadgets.add(new Mobile(model, price, weight, size, credit));
            clearFields();
        } else if (e.getSource() == addMP3Button) {
            String model = getModel();
            double price = getPrice();
            int weight = getWeight();
            String size = getGadgetsSize();
            int memory = getMemory();
            gadgets.add(new MP3(model, price, weight, size, memory));
            clearFields();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == displayButton) {
            displayAll();
        } else if (e.getSource() == makeCallButton) {
            makeCall();
        } else if (e.getSource() == downloadMusicButton) {
            downloadMusic();
        }
    }

    private String getModel() { // Method to retrieve model from the model text field
        return modelTextbox.getText();
    }  // Get model from the model text field

    private double getPrice() { // Method to retrieve price from the price text field
        try {
            return Double.parseDouble(priceTextbox.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    } // Get price from the price text field

    private int getWeight() {  // Method to retrieve weight from the weight text field
        try {
            return Integer.parseInt(weightTextbox.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid weight.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    } // Get weight from the weight text field

    private String getGadgetsSize(){  // Method to retrieve size from the size text field
        return sizeTextbox.getText();
    } // Get size from the size text field

    private int getCredit() { // Method to retrieve credit from the credit text field
        try {
            return Integer.parseInt(creditTextbox.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid credit amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    } // Get credit from the credit text field

    private int getMemory() { // Method to retrieve memory from the memory text field
        try {
            return Integer.parseInt(memoryTextbox.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid memory size.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    } // Get memory from the memory text field

    private String getPhoneNumber() { // Method to retrieve phone number from the phone number text field
        return phoneNumberTextbox.getText();
    } // Get phone number from the phone number text field

    private int getDuration() { // Method to retrieve duration from the duration text field
        try {
            return Integer.parseInt(durationTextbox.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid duration.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }  // Get duration from the duration text field

    private int getDownloadSize() { // Method to retrieve download size from the download size text field
        try {
            return Integer.parseInt(downloadSizeTextbox.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid download size.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }  // Get download size from the download size text field

    private int getDisplayNumber() { // Method to retrieve display number from the combo box
        int displayNumber = -1;
        try {
            displayNumber = Integer.parseInt((String) cmbDisplayNumber.getSelectedItem());
            if (displayNumber < 0 || displayNumber >= gadgets.size()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid display number.", "Error", JOptionPane.ERROR_MESSAGE);
                displayNumber = -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid display number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return displayNumber;
    } // Get display number from the combo box

    private void clearFields() { // Method to clear all text fields
        modelTextbox.setText("");
        priceTextbox.setText("");
        weightTextbox.setText("");
        sizeTextbox.setText("");
        creditTextbox.setText("");
        memoryTextbox.setText("");
        phoneNumberTextbox.setText("");
        durationTextbox.setText("");
        downloadSizeTextbox.setText("");
    } // Clear all text fields

    private void displayAll() {  // Method to display information of all gadgets in the text area
        txtDisplay.setText("");
        for (Gadget gadget : gadgets) {
            gadget.display();
            txtDisplay.append("\n");
        }
    }  // Display information of all gadgets in the text area

    private void makeCall() { // Method to make a call using the selected mobile gadget
        int displayNumber = getDisplayNumber();
        if (displayNumber != -1) {
            Mobile mobile = (Mobile) gadgets.get(displayNumber);
            String phoneNumber = getPhoneNumber();
            int duration = getDuration();
            mobile.makeCall(phoneNumber, duration);
        }
    }  // Make a call using the selected mobile gadget

    private void downloadMusic() { // Method to download music using the selected MP3 gadget
        int displayNumber = getDisplayNumber();
        if (displayNumber != -1) {
            MP3 mp3 = (MP3) gadgets.get(displayNumber);
            int downloadSize = getDownloadSize();
            mp3.downloadMusic(downloadSize);
        }
    } // Download music using the selected MP3 gadget

    public static void main(String[] args) { // Main method to create an instance of GadgetShopGUI
        new GadgetShopGUI();
    } 
}