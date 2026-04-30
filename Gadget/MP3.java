public class MP3 extends Gadget {
    // Additional private instance variable to hold the used memory of the MP3 player
    private int usedMemory;

    // Constructor to initialize the MP3 object with provided values, including used memory
    public MP3(String model, double price, int weight, String size, int usedMemory) {
        // Call the superclass constructor to initialize inherited properties
        super(model, price, weight, size);
        this.usedMemory = usedMemory;
    }

    // Getter method to retrieve the used memory of the MP3 player
    public int getUsedMemory() {
        return usedMemory;
    }

    // Method to download music to the MP3 player
    public void downloadMusic(int memoryRequired) {
        if (memoryRequired <= usedMemory) {  // Check if there is enough memory
            usedMemory -= memoryRequired;    // Reduce the used memory after downloading
            System.out.println("Music downloaded.");
        } else {
            System.out.println("Insufficient memory to download the music.");
        }
    }

    // Method to delete music from the MP3 player
    public void deleteMusic(int openMemory) {
        usedMemory += openMemory;  // Increase the available memory after deleting music
        System.out.println("Music deleted.");
    }

    // Method to display the details of the MP3 player, including available memory
    public void display() {
        super.display();  // Call the superclass display method to display common details
        System.out.println("Available memory: " + usedMemory + " MB");
    }

    // Main method to test the MP3 class
    public static void main(String[] args) {
        // Create a new MP3 object with sample values
        MP3 mp3 = new MP3("iPod", 199.99, 38, "2.4in x 1.6in x 0.45in", 5700);
        mp3.display();              // Display initial details
        mp3.downloadMusic(1500);    // Download music and display updated details
        mp3.display();
        mp3.deleteMusic(700);       // Delete music and display updated details
        mp3.display();
    }
}
