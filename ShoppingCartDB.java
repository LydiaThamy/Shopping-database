package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDB {
    
    private String dirPath = null;
    private boolean loggedIn = false;
    private String user = null;

    // only instantiate the shopping list once a user logs in or adds something to the cart
    List<String> shoppingList = new ArrayList<>();

    // constructor
    public ShoppingCartDB() {
        // shoppingList = new ArrayList<String>();
    }

    // directory function
    public String getDirPath() {
        return this.dirPath;
    }

    public void directory(String[] args) {
        if (args.length == 0) {
            this.dirPath = "db";
        } else {
            this.dirPath = args[0];
        }
        
        File directory = new File(this.dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    // help function
    public void help() {
        System.out.println("Type 'list' to see a list of items in your shopping cart");
        System.out.println("Type 'add item 1, item 2...' to add items into your shopping cart");
        System.out.println("Type 'delete (index of item)' to delete an item in your shopping cart");
        System.out.println("Type 'quit' to end your shoppping with us");
    }

    // login function
    public void login(String input) throws IOException {
        
        // if a username has been given
        if (input.length() > 6) {

            // find out user
            String username = input.substring(6).trim();
            username  = username.replaceAll("\\p{P}", " ");

            // if the same person logs in again
            if (this.loggedIn && username.equals(this.user)) {
                System.out.println("You are already logged in");

                // if someone logs in with a valid username
            } else if (username.matches("^[a-zA-Z0-9]*$")) {

                // tell system that you are logged in
                this.loggedIn = true;

                // assign new user
                this.user = username;

                // instantiate new shopping list
                this.shoppingList = new ArrayList<String>();
                
                // 1. find out if user file exists
                String dirPathFileName = this.dirPath + File.separator + this.user + ".txt";
                File userFile = new File(dirPathFileName);

                // 2a. if user exists, update the shopping list
                if (userFile.exists()) {
                    System.out.print("Welcome back, " + this.user + ". ");

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
                    System.out.print("Nice to meet you, " + this.user + ". ");
                    userFile.createNewFile();
                }

                // 3. print out shopping list details
                // if the size of the shopping list is more than 0
                if (this.shoppingList.size() > 0) {
                    System.out.println("Your cart contains the following items:");
                    for (int i = 0; i < this.shoppingList.size(); i++) {
                        System.out.println((i + 1) + ". " + this.shoppingList.get(i));
                    }

                    // if shopping list is empty
                } else {
                    System.out.println("Your cart is empty.");
                }

            } else {
                System.out.println("Use a username with alphabets and numbers only e.g. abc123.");
            }
              
            // if someone has not logged in, ask to login with username
        } else {
            System.out.println("Log in with your username e.g. login username.");
        }
}
 
    // list function
    public void list() {

        if (!this.loggedIn) {
            System.out.println("Log in to view the items in your cart.");

        // if list is empty
        } else if (this.shoppingList.isEmpty()) {
            System.out.println("Your cart is empty.");

            // if list is not empty
        } else {
            System.out.println("You have these items in your cart:");
            for (int i = 0; i < this.shoppingList.size(); i++) {
                System.out.println((i + 1) + ". " + this.shoppingList.get(i));
            }
        }
    }

     // add function
    public void add(String input) {

        if (!loggedIn) {
            System.out.println("Log in to add items to your cart.");

        } else if (input.length() > 4) {
            // place items individually in a list
            String[] itemList = input.substring(4).trim().split(",");

            for (int i = 0; i < itemList.length; i++) {
                itemList[i] = itemList[i].trim().toLowerCase().replaceAll("\\p{P}", " ");

                // if item exists in a shopping cart
                if (this.shoppingList.contains(itemList[i])) {
                    System.out.println("You have " + itemList[i] + " in your cart.");

                    // if item is new
                    // item must not be a non-character
                } else if (!itemList[i].isBlank()) {
                    this.shoppingList.add(itemList[i]);
                    System.out.println(itemList[i] + " added to cart");
                }
            }
            
        } else {
            System.out.println("Add items to your cart e.g. add item1, item2...");
        }    
    } 

    // delete function
    public void delete (String input) {
      
        if (!loggedIn) {
            System.out.println("Log in to delete items from your cart.");
                
            // if list is empty
        } else if (this.shoppingList.isEmpty()) {
            System.out.println("Your cart is empty.");
        
        } else if (input.length() > 7) {
            
            // convert string to integer
            try {
                Integer index = Integer.parseInt(input.substring(7).trim());

                    // if incorrect index provided
                    // index bigger than list size
                    // index is less or equal to 0
                if (index > this.shoppingList.size() || index <= 0) {
                    System.out.println("The item index does not exist. Type 'list' to view the items in your cart.");

                    // if correct index provided
                } else {
                    System.out.println(shoppingList.get(index - 1) + " removed from cart");
                    this.shoppingList.remove(index - 1);
                }

                // if a non-number is input, send an error message
            } catch (NumberFormatException nfe) {
                System.out.println("Type the item's index on the shopping list to delete e.g. delete 1.");
                // System.out.println(nfe);
            }

        } else {
            System.out.println("Type the item's index on the shopping list to delete e.g. delete 1.");
        }
    }

    public void save() throws IOException {
        
        if (!this.loggedIn) {
            System.out.println("Log in in before saving your cart.");

        } else {
            // overwrite shopping cart
            String dirPathFileName = dirPath + File.separator + user + ".txt";
            FileWriter fw = new FileWriter(dirPathFileName, false);
            BufferedWriter bw = new BufferedWriter(fw);

            if (this.shoppingList.size() > 0) {
                for (int i = 0; i < this.shoppingList.size(); i++) {
                    bw.append(this.shoppingList.get(i) + "\n");
                }
            }

            bw.flush();
            bw.close();
            fw.close();

            // log out user
            this.user = null;

            // make sure that you are logged out
            this.loggedIn = false;

            // clear shopping list
            this.shoppingList = null;

            System.out.println("Your cart has been saved. Log in to access your cart.");
        }
    }

    public void users() {
        
        // if cartdb exists, list out files in cart db
        File cartdbPath = new File("cartdb");
        if (cartdbPath.exists()) {
            System.out.println("Users in the cartdb directory:");
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
            System.out.println("Users in the db directory:");
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

}