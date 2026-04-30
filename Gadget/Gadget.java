public class Gadget{
    // Private instance variables to hold information about the gadget
    
    private String model;// Model of the gadget
    private double price;// price of gadget
    private int weight;// weight of the gadget
    private String size;// size of the gadget

    public Gadget(String model, double price, int weight, String size){
         // Constructor to initialize the Gadget object with provided values
        this.size = size;
        this.model = model;
        this.price = price;
        this.weight = weight;
    }

    public String getModel(){
        // Getter method to retrieve the model of the gadget
        return model;
    }

    public double getPrice(){
        // Getter method to retrieve the price of the gadget
        return price;
    }

    public int getWeight(){
        // Getter method to retrieve the weight of the gadget
        return weight;
    }

    public String getSize(){
        // Getter method to retrieve the size of the gadget
        return size;
    }

    public void display(){
        // Method to display the details of the gadget
        System.out.println("Model: " + model); // printing out the model with its allocated name
        System.out.println("Price: " + price + " pounds"); //printing out price through the value of pound
        System.out.println("Weight: " + weight + " grams"); // printing out weight through the value of grams
        System.out.println("Size: " + size); // printing out the size in its dimensions
    }

    public static void main(String[] args){ // Main method to test the Gadget class
        Gadget gadget = new Gadget("iPhone 12", 999.99, 164, "71mm x 137mm x 9mm");  // Create a new Gadget object with sample values
        gadget.display(); // Display the details of the gadget
    }
}