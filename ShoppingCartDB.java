package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDB {
    
    private String dirPath = null;
    private boolean newShoppingList = true;
    private boolean loggedIn = false;
    private String user = null;

    // only instantiate the shopping list once a user logs in or adds something to the cart
    List<String> shoppingList = null;

    // constructor
    public ShoppingCartDB() {
        // shoppingList = new ArrayList<String>();
    }

    // directory function
    public String getDirPath() {
        return dirPath;
    }

    public void directory(String[] args) {
        if (args.length == 0) {
            dirPath = "db";
        } else {
            dirPath = args[0];
        }
        
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    // help function
    public void help() {
        System.out.println("Type 'list' to see a list of items in your shopping cart");
        System.out.println("Type 'add (item 1, item 2, etc...)' to add items into your shopping cart");
        System.out.println("Type 'delete (index of item)' to delete an item in your shopping cart");
        System.out.println("Type 'end' to finish editing your shopping cart");
    }

    // login function
    public void login(String input) throws IOException {
        
        // if a username has been given
        if (input.length() > 6) {

            // find out user
            String username = input.substring(6).trim();
            username  = username.replaceAll("\\p{P}", " ");

            // if the same person logs in again
            if (loggedIn && username.equals(this.user)) {
                System.out.println("You are already logged in");

                // if someone logs in with a valid username
            } else if (username.matches("^[a-zA-Z0-9]*$")) {

                // tell system that you are logged in
                loggedIn = true;

                this.user = username;

                // instantiate new shopping list
                if (newShoppingList) {
                    shoppingList = new ArrayList<String>();
                    newShoppingList = false;
                }

                // 1. find out if user file exists
                String dirPathFileName = dirPath + File.separator + this.user + ".txt";
                File userFile = new File(dirPathFileName);

                // 2a. if user exists, update the shopping list
                if (userFile.exists()) {
                    System.out.println("Welcome back, " + this.user);

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
                    System.out.println("Nice to meet you, " + this.user);
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
                    System.out.println(this.user + ", your cart is empty");
                }

            } else {
                System.out.println("Please use a username with alphabets and numbers only e.g. abc123");
            }
              
            // if someone has not logged in, ask to login with username
        } else {
            System.out.println("Please login with your username e.g. login username");
        }
}
 
    // list function
    public void list() {

        if (newShoppingList) {
            shoppingList = new ArrayList<>();
            System.out.println("Your cart is empty");

        // if list is empty
        } else if (shoppingList.isEmpty()) {
            System.out.println("Your cart is empty");

            // if list is not empty
        } else {
            for (int i = 0; i < shoppingList.size(); i++) {
                System.out.println((i + 1) + ". " + shoppingList.get(i));
            }
        }
    }

     // add function
    public void add(String input) {

        if (input.length() > 4) {

            // instantiate a new shopping list if needed
            if (newShoppingList) {
                shoppingList = new ArrayList<String>();
                newShoppingList = false;
            }

            // place items individually in a list
            String[] itemList = input.substring(4).trim().split(",");

            for (int i = 0; i < itemList.length; i++) {
                itemList[i] = itemList[i].trim().toLowerCase().replaceAll("\\p{P}", " ");

                // if item exists in a shopping cart
                if (shoppingList.contains(itemList[i])) {
                    System.out.println("You have " + itemList[i] + " in your cart");

                    // if item is new
                    // item must not be a non-character
                } else if (!itemList[i].isBlank()) {
                    shoppingList.add(itemList[i]);
                    System.out.println(itemList[i] + " added to cart");
                }
            }
        
        } else {
            System.out.println("Please add items into your cart e.g. add item1, item2...");
        }    
    } 

    // delete function
    public void delete (String input) {
        
        if (input.length() > 7 && newShoppingList == false) {
                
            // convert string to integer
            try {
                Integer index = Integer.parseInt(input.substring(7).trim());

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

                // if a non number is placed in, send an error message
            } catch (NumberFormatException nfe) {
                System.out.println("Please input the item's index on the shopping list to delete e.g. delete 1");
                // System.out.println(nfe);
            }
        } else if (newShoppingList) {
            System.out.println("Your cart is empty");
        } else {
            System.out.println("Please input the item's index on the shopping list to delete e.g. delete 1");
        }
    }

}









   /*
    // login function
    public void login() {
        
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
*/