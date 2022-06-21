import java.util.Scanner;

public class Chatbot {
    // String input;
    // hardcoded responses
    String greetings = "Chatbot: Greetings! Welcome to Bewhaos automatic chatbot \nWhat product are you looking for? (type 'help' for more information on how I can help you) -";
    String goodbye = "Chatbot: Goodbye friend! Please return if there is anything else I can help with :) -";
    String help = "Chatbot: I can look up a product for you \nOr you can type 'goodbye' to exit :) -";

    public Chatbot() {
        // input = s;
        Scanner sc = new Scanner(System.in);
        System.out.println(greetings);
        try {
            while (sc.hasNextLine()) {
                String input = sc.nextLine().toLowerCase();
                if (input.equals("goodbye")) {
                    System.out.println(goodbye);
                    System.exit(0);
                }
                if (input.equals("help")) {
                    System.out.println(help);
                } else {
                    System.out.println(handleInput(input));
                    // System.out.println("you are asking for: " + input);
                }
            }
        } finally {
            sc.close();
        }

    }

    public String handleInput(String s) {
        String answer = String.format("You are looking for %s", s);
        return answer;
    }

    public static void main(String[] args) {
        Chatbot cb = new Chatbot();

    }
}