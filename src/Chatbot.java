import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Chatbot {

    private HashMap<String, String> productDB = fillProductDB();
    private ArrayList<String> unavailable = unavailable();

    // hardcoded responses
    String greetings = "\nChatbot: Greetings! Welcome to Bewhaos A/S automatic chatbot. \nType the name of the tool you are looking for: (type 'help' for more information on how I can help you)\n";
    String goodbye = "\nChatbot: Goodbye! Please return if there is anything else I can help you with. :) \n";
    String help = "\nChatbot: Type the name of the tool you are looking for. \nType 'stock' to see current available tools. \nType 'goodbye' to exit. \n";
    String stock = "\nChatbot: We have the following tools in stock: ";

    public Chatbot() {
        Scanner sc = new Scanner(System.in);
        System.out.println(greetings);
        try {
            while (sc.hasNextLine()) {
                String input = sc.nextLine().toLowerCase();
                if (input.equals("goodbye")) {
                    System.out.println(goodbye);
                    System.exit(0);
                } else if (input.equals("help")) {
                    System.out.println(help);
                } else if (input.equals("stock")) {
                    System.out.println(stock + productDB.keySet() + "\n");
                } else {
                    System.out.println(handleInput(input));
                }
            }
        } finally {
            sc.close();
        }
    }

    // handles input besides exit, stock and help commands - decides what happens
    // with the input afterward
    public String handleInput(String s) {
        String answer = "";
        if (!s.matches("^[a-zA-Z]*$")) {
            answer = "Chatbot: Please only input single words in Latin alphabet (no whitespace, numbers, punctuations, etc.)";
        } else if (unavailable.contains(s)) {
            answer = String.format("Chatbot: Sadly, we do not have any %s in stock.\nA new shipment is on its way.", s);
        } else if (productDB.containsKey(s)) {
            answer = productDB.get(s);
        } else {
            answer = String.format(
                    "Chatbot: We do not have any %s in our sortiment.\nI will let our customer service team know you are looking for it.",
                    s);
        }
        return "\n" + answer + "\n";
    }

    public HashMap<String, String> fillProductDB() {
        HashMap<String, String> db = new HashMap<>();
        db.put("hammer",
                "\nHammers available:\nEstwing Steel Claw Hammer LINK\nHEIKIO Steel-Forged Hammer LINK \nEdward Tools Claw Hammer LINK\n");
        db.put("saw", "\nSaws available:\nDEWALT saw LINK\nBosch saw LINK\n");
        db.put("shovel", "\nShovels available:\nBullt Round Point Shovel LINK\nFiskars D-Handle Spade LINK\n");
        return db;
    }

    public ArrayList<String> unavailable() {
        ArrayList<String> db = new ArrayList<>();
        db.add("screwdriver");
        db.add("wrench");
        db.add("toolbox");
        return db;
    }

    public static void main(String[] args) {
        Chatbot cb = new Chatbot();
    }
}