package sg.edu.nus.iss;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String dirPath;
        
        if (args.length == 0) {
            dirPath = "db";
        } else {
            dirPath = args[0];
        }
        
        System.out.println("Welcome to your shopping cart");
        System.out.println("It will be saved in " + dirPath);
        System.out.println("Please type a command. You may type 'help' if you need a list of commands.");

        String input = "";
        List<String> shoppingList = new ArrayList<>();

        // Console con = System.console();
        Scanner scan = new Scanner(System.in);

        // done function
        while (!input.equals("end")) {
            // input = con.readLine();
            input = scan.next();

            // help function
            if (input.equals("help")) {
                System.out.println("Type 'list' to see a list of items in your shopping cart");
                System.out.println("Type 'add (item 1, item 2, etc...)' to add items into your shopping cart");
                System.out.println("Type 'delete (index of item)' to delete an item in your shopping cart");
                System.out.println("Type 'end' to finish editing your shopping cart");
            }

            // list function
            if (input.equals("list")) {
                
                // if list is empty
                if (shoppingList.isEmpty()) {
                    System.out.println("Your cart is empty");

                // if list is not empty
                } else {
                    for (int i = 0; i < shoppingList.size(); i++) {
                        System.out.println((i + 1) + ". " + shoppingList.get(i));
                    }
                }
            }

            // add function
            if (input.equals("add")) {
                String itemInput = scan.nextLine();

                // place items individually in a list
                String[] item = itemInput.split(",");
                
                for (int i = 0; i < item.length; i++) {
                    item[i] = item[i].trim().toLowerCase().replaceAll("\\p{P}", " ");
                    
                    // if item exists in a shopping cart
                    if (shoppingList.contains(item[i])) {
                        System.out.println("You have " + item[i] + " in your cart");
                        
                        // if item is new
                        // item must not be a non-character
                    } else if (!item[i].isBlank()) {
                        shoppingList.add(item[i]);
                        System.out.println(item[i] + " added to cart");
                    }
                }
            }

            // delete function
            if (input.equals("delete")) {

                try {
                    Integer index = Integer.parseInt(scan.next());

                    // if list is empty
                    if (shoppingList.isEmpty()) {
                        System.out.println("Your cart is empty");
    
                    // if incorrect index provided
                    // index bigger than list size
                    // index is less or equal to 0
                    } else if (index > shoppingList.size() || index <= 0) {
                        System.out.println("Incorrect item index");
    
                    // if correct index provided
                    } else {
                        System.out.println(shoppingList.get(index - 1) + " removed from cart");
                        shoppingList.remove(index - 1);
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("Please input a number to delete e.g. delete 1");
                    // System.out.println(nfe);
                }

            }

        }

        scan.close();
        System.out.println("Thanks for shopping with us. Bye bye!");

    }
}
