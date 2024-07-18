import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GadgetShop {
    private JFrame frame;
    private JTextField modelField, priceField, weightField, sizeField, creditField, memoryField, phoneNumberField, durationField, downloadSizeField, displayNumberField;
    private JTextArea statusArea;
    private ArrayList<Gadget> gadgets;

    public GadgetShop() {
        gadgets = new ArrayList<>();

        frame = new JFrame("Gadget Shop");
        frame.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(7, 4, 5, 5));

        modelField = new JTextField();
        priceField = new JTextField();
        weightField = new JTextField();
        sizeField = new JTextField();
        creditField = new JTextField();
        memoryField = new JTextField();
        phoneNumberField = new JTextField();
        durationField = new JTextField();
        downloadSizeField = new JTextField();
        displayNumberField = new JTextField();

        addLabeledField(inputPanel, "Model:", modelField);
        addLabeledField(inputPanel, "Price:", priceField);
        addLabeledField(inputPanel, "Weight:", weightField);
        addLabeledField(inputPanel, "Size:", sizeField);
        addLabeledField(inputPanel, "Credit:", creditField);
        addLabeledField(inputPanel, "Memory:", memoryField);

        JButton addMobileButton = createButton("Add Mobile", e -> addMobile(), "Add a new mobile phone");
        JButton addMP3Button = createButton("Add MP3", e -> addMP3(), "Add a new MP3 player");
        JButton clearButton = createButton("Clear", e -> clearFields(), "Clear all input fields");
        JButton displayAllButton = createButton("Display All", e -> displayAll(), "Display all gadgets");
        JButton makeCallButton = createButton("Make A Call", e -> makeCall(), "Make a phone call");
        JButton downloadMusicButton = createButton("Download Music", e -> downloadMusic(), "Download music to MP3 player");

        inputPanel.add(addMobileButton);
        inputPanel.add(addMP3Button);
        inputPanel.add(clearButton);
        inputPanel.add(displayAllButton);

        addLabeledField(inputPanel, "Phone No:", phoneNumberField);
        addLabeledField(inputPanel, "Duration:", durationField);
        addLabeledField(inputPanel, "Download:", downloadSizeField);
        addLabeledField(inputPanel, "Display Number:", displayNumberField);

        inputPanel.add(makeCallButton);
        inputPanel.add(downloadMusicButton);

        statusArea = new JTextArea(5, 20);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.RIGHT);
        panel.add(label);
        panel.add(textField);
    }

    private JButton createButton(String text, ActionListener action, String tooltip) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        button.setToolTipText(tooltip);
        return button;
    }

    private String getModel() {
        return modelField.getText();
    }

    private double getPrice() {
        try {
            return Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            showMessage("Invalid price value. Please enter a valid decimal number.");
            return -1;
        }
    }

    private int getWeight() {
        try {
            return Integer.parseInt(weightField.getText());
        } catch (NumberFormatException e) {
            showMessage("Invalid weight value. Please enter a valid integer.");
            return -1;
        }
    }

    private String getSize() {
        return sizeField.getText();
    }

    private int getCredit() {
        try {
            return Integer.parseInt(creditField.getText());
        } catch (NumberFormatException e) {
            showMessage("Invalid credit value. Please enter a valid integer.");
            return -1;
        }
    }

    private int getMemory() {
        try {
            return Integer.parseInt(memoryField.getText());
        } catch (NumberFormatException e) {
            showMessage("Invalid memory value. Please enter a valid integer.");
            return -1;
        }
    }

    private String getPhoneNumber() {
        return phoneNumberField.getText();
    }

    private int getDuration() {
        try {
            return Integer.parseInt(durationField.getText());
        } catch (NumberFormatException e) {
            showMessage("Invalid duration value. Please enter a valid integer.");
            return -1;
        }
    }

    private int getDownloadSize() {
        try {
            return Integer.parseInt(downloadSizeField.getText());
        } catch (NumberFormatException e) {
            showMessage("Invalid download size value. Please enter a valid integer.");
            return -1;
        }
    }

    private int getDisplayNumber() {
        int displayNumber = -1;
        try {
            displayNumber = Integer.parseInt(displayNumberField.getText());
            if (displayNumber < 0 || displayNumber >= gadgets.size()) {
                showMessage("Display number out of range.");
                displayNumber = -1;
            }
        } catch (NumberFormatException e) {
            showMessage("Invalid display number.");
        }
        return displayNumber;
    }

    private void addMobile() {
        String model = getModel();
        double price = getPrice();
        int weight = getWeight();
        String size = getSize();
        int credit = getCredit();

        if (price != -1 && weight != -1 && credit != -1) {
            gadgets.add(new Mobile(model, price, weight, size, credit));
            showMessage("Mobile added successfully.");
        }
    }

    private void addMP3() {
        String model = getModel();
        double price = getPrice();
        int weight = getWeight();
        String size = getSize();
        int memory = getMemory();

        if (price != -1 && weight != -1 && memory != -1) {
            gadgets.add(new MP3(model, price, weight, size, memory));
            showMessage("MP3 player added successfully.");
        }
    }

    private void clearFields() {
        modelField.setText("");
        priceField.setText("");
        weightField.setText("");
        sizeField.setText("");
        creditField.setText("");
        memoryField.setText("");
        phoneNumberField.setText("");
        durationField.setText("");
        downloadSizeField.setText("");
        displayNumberField.setText("");
        showMessage("All fields cleared.");
    }

    private void displayAll() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < gadgets.size(); i++) {
            output.append("Gadget ").append(i).append(":\n");
            output.append(gadgets.get(i).toString()).append("\n\n");
        }
        statusArea.setText(output.toString());
    }

    private void makeCall() {
        int displayNumber = getDisplayNumber();
        if (displayNumber != -1 && gadgets.get(displayNumber) instanceof Mobile) {
            String phoneNumber = getPhoneNumber();
            int duration = getDuration();
            if (duration != -1) {
                Mobile mobile = (Mobile) gadgets.get(displayNumber);
                if (mobile.getCredit() >= duration) {
                    mobile.makeCall(phoneNumber, duration);
                    showMessage("Call made successfully.");
                } else {
                    showMessage("Insufficient credit to make the call.");
                }
            }
        } else {
            showMessage("Selected gadget is not a mobile or invalid display number.");
        }
    }

    private void downloadMusic() {
        int displayNumber = getDisplayNumber();
        if (displayNumber != -1 && gadgets.get(displayNumber) instanceof MP3) {
            int downloadSize = getDownloadSize();
            if (downloadSize != -1) {
                MP3 mp3 = (MP3) gadgets.get(displayNumber);
                if (mp3.getMemory() >= downloadSize) {
                    mp3.downloadMusic(downloadSize);
                    showMessage("Music downloaded successfully.");
                } else {
                    showMessage("Insufficient memory to download music.");
                }
            }
        } else {
            showMessage("Selected gadget is not an MP3 player or invalid display number.");
        }
    }

    private void showMessage(String message) {
        statusArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GadgetShop::new);
    }
}

abstract class Gadget {
    private String model;
    private double price;
    private int weight;
    private String size;

    public Gadget(String model, double price, int weight, String size) {
        this.model = model;
        this.price = price;
        this.weight = weight;
        this.size = size;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getSize() {
        return size;
    }

    public void display() {
        System.out.println("Model: " + model);
        System.out.println("Price: " + price);
        System.out.println("Weight: " + weight);
        System.out.println("Size: " + size);
    }

    @Override
    public String toString() {
        return "Model: " + model + "\n" +
               "Price: " + price + "\n" +
               "Weight: " + weight + "\n" +
               "Size: " + size;
    }
}

class Mobile extends Gadget {
    private int credit;

    public Mobile(String model, double price, int weight, String size, int credit) {
        super(model, price, weight, size);
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public void makeCall(String phoneNumber, int duration) {
        System.out.println("Calling " + phoneNumber + " for " + duration + " minutes.");
        credit -= duration;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCredit: " + credit;
    }
}

class MP3 extends Gadget {
    private int memory;

    public MP3(String model, double price, int weight, String size, int memory) {
        super(model, price, weight, size);
        this.memory = memory;
    }

    public int getMemory() {
        return memory;
    }

    public void downloadMusic(int downloadSize) {
        System.out.println("Downloading music of size " + downloadSize + "MB.");
        memory -= downloadSize;
    }

    @Override
    public String toString() {
        return super.toString() + "\nMemory: " + memory;
    }
}
