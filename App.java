package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        // directory path
        String user = null;
        String dirPath = null;
        
        if (args.length == 0) {
            dirPath = "db";
        } else {
            dirPath = args[0];
        }
        
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        
        // welcome message
        System.out.println("Welcome to your shopping cart in the " + dirPath + " directory.");
        System.out.println("Please type a command. You may type 'help' if you need a list of commands.");
        
        // terminal input
        String input = "";
        Scanner scan = new Scanner(System.in);

        // instantiate shopping cart object
        ShoppingCartDB shoppingCart = new ShoppingCartDB();

        // !!checkers
        boolean loggedIn = false;    

        // end function
        while (!input.equals("end")) {
            input = scan.next();

            // help function
            if (input.equals("help")) {
                System.out.println("Type 'list' to see a list of items in your shopping cart");
                System.out.println("Type 'add (item 1, item 2, etc...)' to add items into your shopping cart");
                System.out.println("Type 'delete (index of item)' to delete an item in your shopping cart");
                System.out.println("Type 'end' to finish editing your shopping cart");
            }

            /*
            // login function
            if (input.equals("login")) {

                // create new scanner to scan username
                // login loop will have a logical error if you do not create a new scanner
                Scanner userInput = new Scanner(scan.nextLine()); // input after 'login'

                // if a username has been given
                if (userInput.hasNext()) {

                    // find out user
                    String username = userInput.nextLine().replaceAll("\\p{P}", " ").trim();

                    // if the same person logs in again
                    if (username.equals(user)) {
                        System.out.println("You are already logged in");

                        // if someone logs in with a valid username
                    } else if (username.matches("^[a-zA-Z0-9]*$")) {

                        // tell system that you are logged in
                        loggedIn = true;

                        user = username;

                        // instantiate new shopping list
                        shoppingList = new ArrayList<String>();
                        newShoppingList = false;

                        // 1. find out if user file exists
                        String dirPathFileName = dirPath + File.separator + user + ".txt";
                        File userFile = new File(dirPathFileName);

                        // 2a. if user exists, update the shopping list
                        if (userFile.exists()) {
                            System.out.println("Welcome back, " + user);

                            // use file reader to fill up shopping cart list
                            FileReader fr = new FileReader(userFile);
                            BufferedReader br = new BufferedReader(fr);
                            String line = "";

                            while ((line = br.readLine()) != null) {
                                shoppingList.add(line);
                            }

                            br.close();
                            fr.close();

                            // 2b. if the user does not exist
                        } else {
                            System.out.println("Nice to meet you, " + user);
                            userFile.createNewFile();
                        }

                        // 3. print out shopping list details
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

                    } else {
                        System.out.println("Please use a username with alphabets and numbers only e.g. abc123");
                    }
                      
                    // if someone has not logged in, ask to login with username
                } else {
                    System.out.println("Please login with your username e.g. login username");
                }
            } 
            
            // save function
            if (input.equals("save")) {
                if (loggedIn == true) {
                    System.out.println("Your cart has been saved");

                    // overwrite shopping cart
                    String dirPathFileName = dirPath + File.separator + user + ".txt";
                    FileWriter fw = new FileWriter(dirPathFileName, false);
                    BufferedWriter bw = new BufferedWriter(fw);

                    if (shoppingList.size() > 0) {
                        for (int i = 0; i < shoppingList.size(); i++) {
                            bw.append(shoppingList.get(i) + "\n");
                        }
                    }

                    bw.flush();
                    bw.close();
                    fw.close();
                } else {
                    System.out.println("Please login in first before saving");
                }
            }
                */
            // list function
            if (input.equals("list")) {
                shoppingCart.list();
            }

            // add function
            if (input.equals("add")) {
                Scanner itemInput = new Scanner(System.in);
                String items = itemInput.nextLine();
                itemInput.close();

                // use add method with items as an argument
                shoppingCart.add(items);
            }
            /*
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

            // users function
            if (input.equals("users")) {
                
                // if cartdb exists, list out files in cart db
                File cartdbPath = new File("cartdb");
                if (cartdbPath.exists()) {
                    String[] fileList = cartdbPath.list();

                    // only print names if they are text files
                    for (String file:fileList) {
                        if (file.endsWith(".txt")) {
                            file = file.replace(".txt", "");
                            System.out.println(file);
                        }
                    }
                }

                // if db exists, list out files in db
                File dbPath = new File("db");
                if (dbPath.exists()) {
                    String[] fileList = dbPath.list();

                    // only print names if they are text files
                    for (String file:fileList) {
                        if (file.endsWith(".txt")) {
                            file = file.replace(".txt", "");
                            System.out.println(file);
                        }
                    }
                }
            }
            */

        }

        scan.close();
        System.out.println("Thanks for shopping with us. Bye bye!");

    }
}
