public class Mobile extends Gadget {
    // Additional private instance variable to hold the calling credit of the mobile
    private int callingCredit;

    // Constructor to initialize the Mobile object with provided values, including calling credit
    public Mobile(String model, double price, int weight, String size, int callingCredit) {
        // Call the superclass constructor to initialize inherited properties
        super(model, price, weight, size);
        this.callingCredit = callingCredit;
    }

    // Method to display the details of the Mobile, including calling credit remaining
    public void display() {
        super.display();  // Call the superclass display method to display common details
        System.out.println("Calling credit remaining: " + callingCredit + " minutes");
    }

    // Method to add calling credit to the mobile
    public void addCallingCredit(int creditToAdd) {
        if (creditToAdd > 0) {
            callingCredit += creditToAdd;  // Add the provided credit to the existing calling credit
        } else {
            System.out.println("Please enter a positive amount to add calling credit.");
        }
    }

    // Getter method to retrieve the calling credit of the mobile
    public int getCallingCredit() {
        return callingCredit;
    }

    // Method to make a call from the mobile
    public void makeCall(String phoneNumber, int duration) {
        if (duration <= callingCredit) {  // Check if there is sufficient calling credit
            System.out.println("Making call to " + phoneNumber + " for " + duration + " minutes.");
            callingCredit -= duration;  // Deduct the duration from the calling credit
        } else {
            System.out.println("Insufficient calling credit.");
        }
    }

    // Main method to test the Mobile class
    public static void main(String[] args) {
        // Create a new Mobile object with sample values
        Mobile mobile = new Mobile("iPhone 5s", 45.6, 134, "89mm x 120mm x 12mm", 100);
        mobile.display();              // Display initial details
        mobile.addCallingCredit(50);   // Add calling credit and display updated details
        mobile.makeCall("123456789", 30);  // Make a call and display updated details
        mobile.display();
    }
}
