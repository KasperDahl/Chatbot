import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Chatbot {

    // databases of products and products out of stock
    private HashMap<String, String> productDB = fillProductDB();
    private ArrayList<String> unavailable = unavailable();

    // hardcoded responses
    String greetings = "\nChatbot: Greetings! Welcome to Bewhaos A/S automatic chatbot. \nType the name of the tool you are looking for (type 'help' to see your options).\n";
    String goodbye = "\nChatbot: Goodbye! I hope to see you at Bewhaos A/S soon. :) \n";
    String help = "\nChatbot: Type the name of the tool you are looking for. \nType 'stock' to see current available tools. \nType 'goodbye' to exit. \n";
    String stock = "\nChatbot: We have the following tools in stock: ";

    // constructor receives input. In switch statement the hardcoded strings are
    // handled, otherwise it is passed on to handleInput()-method
    public Chatbot() {
        Scanner sc = new Scanner(System.in);
        System.out.println(greetings);
        try {
            while (sc.hasNextLine()) {
                String input = sc.nextLine().toLowerCase();
                switch (input) {
                    case "goodbye":
                        System.out.println(goodbye);
                        System.exit(0);
                        break;
                    case "help":
                        System.out.println(help);
                        break;
                    case "stock":
                        System.out.println(stock + "\n" + productDB.keySet() + "\n");
                        break;
                    default:
                        System.out.println(handleInput(input));
                        break;
                }
            }
        } finally {
            sc.close();
        }
    }

    // handles input besides hardcoded commands - decides what happens with the
    // input afterward
    public String handleInput(String s) {
        String answer = "";
        if (!s.matches("^[a-zA-Z]*$")) {
            answer = "Please only input single words in Latin alphabet (no whitespace, numbers, punctuations, etc.)";
        } else if (unavailable.contains(s)) {
            answer = String.format("Sadly, we do not have any %s in stock.\nA new shipment is on its way.", s);
        } else if (productDB.containsKey(s)) {
            answer = productDB.get(s);
        } else {
            answer = String.format(
                    "We do not have any %s in our inventory.\nI will let our customer service team know you are looking for it.",
                    s);
            notification(s);
        }
        return "\nChatbot: " + answer + "\n";
    }

    public HashMap<String, String> fillProductDB() {
        HashMap<String, String> db = new HashMap<>();
        db.put("hammer",
                "Hammers available:\nEstwing Steel Claw Hammer LINK\nHEIKIO Steel-Forged Hammer LINK \nEdward Tools Claw Hammer LINK");
        db.put("saw", "Saws available:\nDEWALT saw LINK\nBosch saw LINK");
        db.put("shovel", "Shovels available:\nBullt Round Point Shovel LINK\nFiskars D-Handle Spade LINK");
        return db;
    }

    public ArrayList<String> unavailable() {
        ArrayList<String> db = new ArrayList<>();
        db.add("screwdriver");
        db.add("wrench");
        db.add("toolbox");
        return db;
    }

    // would notify customer service team about the potential new product to be
    // added to the inventory
    public void notification(String s) {
        //
    }

    public static void main(String[] args) {
        new Chatbot();
    }
}