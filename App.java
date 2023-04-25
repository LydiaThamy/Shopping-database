package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        
        // directory path
        String dirPath;
        
        if (args.length == 0) {
            dirPath = "db";
        } else {
            dirPath = args[0];
        }
        
        File directory = new File (dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // welcome message
        System.out.println("Welcome to your shopping cart. It will be saved in directory " + dirPath + ".");
        System.out.println("Please type a command. You may type 'help' if you need a list of commands.");

        // terminal input
        String input = "";
        Scanner scan = new Scanner(System.in);
 
        // only instantiate the shopping list once a user logs in or adds something to the cart
        List<String> shoppingList = null;

        // checkers
        String user;
        boolean loggedIn = false;
        boolean newShoppingList = true;

        // done function
        while (!input.equals("end")) {
            input = scan.next();

            // help function
            if (input.equals("help")) {
                System.out.println("Type 'list' to see a list of items in your shopping cart");
                System.out.println("Type 'add (item 1, item 2, etc...)' to add items into your shopping cart");
                System.out.println("Type 'delete (index of item)' to delete an item in your shopping cart");
                System.out.println("Type 'end' to finish editing your shopping cart");
            }

            // login function
            if (input.equals("login")) {
                // tell system that you are logged in
                loggedIn = true;
                
                // instantiate shopping list if not yet instantiated
                if (newShoppingList == true) {
                    shoppingList = new ArrayList<String>();
                    newShoppingList = false;
                }
                
                // find out user and welcome message
                user = scan.next();
                System.out.print(user + ", your cart ");

                // find out if user file exists
                File userFile = new File(user + File.separator + dirPath);

                // if user exists, update the shopping list
                if (userFile.exists()) {
                    
                    // use file reader to fill up shopping cart list
                    FileReader fr = new FileReader(userFile);
                    BufferedReader br = new BufferedReader(fr);
                    String line = "";

                    while ((line = br.readLine()) != null) {
                        shoppingList.add(line);
                    }

                    br.close();
                    fr.close();

                    
                    // if the user does not exist
                } else {
                    userFile.createNewFile();
                   
                }

                // if the size of the shopping list is more than 0
                if (shoppingList.size() > 0) {
                    System.out.println(user + ", your cart contains the following items");
                    for (int i = 0; i < shoppingList.size(); i++) {
                        System.out.println((i + 1) + ". " + shoppingList.get(i));
                    }

                // if shopping list is empty
                } else {
                    System.out.println(user + ", your cart is empty");
                }
            }

            // list function
            if (input.equals("list")) {
                
                // if list is empty
                if (shoppingList.isEmpty() || shoppingList==null) {
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

                // instantiate shopping list if not yet instantiated
                if (newShoppingList == true) {
                    shoppingList = new ArrayList<String>();
                    newShoppingList = false;
                }

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
