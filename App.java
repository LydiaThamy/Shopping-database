package sg.edu.nus.iss;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        
        // instantiate shopping cart object
        ShoppingCartDB shoppingCart = new ShoppingCartDB();
        
        // create directory path at startup
        shoppingCart.directory(args);
        
        // welcome message
        System.out.println("Welcome to your shopping cart.");
        System.out.println("You are in the " + shoppingCart.getDirPath() + " directory. Please log in.");
        
        // terminal input
        String input = "";
        Scanner scan = new Scanner(System.in);


        // end function
        while (!input.startsWith("quit")) {
            input = scan.nextLine().trim();

            // System.out.println(input);

            // help function
            if (input.startsWith("help")) {
                shoppingCart.help();
            }
            
            // login function
            else if (input.startsWith("login")) {
                shoppingCart.login(input);
            }

            // list function
            else if (input.startsWith("list")) {
                shoppingCart.list();
            }

            // add function
            else if (input.startsWith("add")) {
                shoppingCart.add(input);
            }

            // delete function
            else if (input.startsWith("delete")) {
                shoppingCart.delete(input);
            }

            // save function
            else if (input.startsWith("save")) {
                shoppingCart.save();
            }

            // users function
            else if (input.equals("users")) {
                shoppingCart.users();
            }
            

            // ending command
            else {
                System.out.println("Type a command. Type 'help' if you need a list of commands.");
            }
        }
        
        scan.close();
        System.out.println("Thanks for shopping with us. Bye bye!");

    }
}
