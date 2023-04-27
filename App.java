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
        System.out.println("Welcome to your shopping cart in the " + shoppingCart.getDirPath() + " directory.");
        System.out.println("Please type a command. You may type 'help' if you need a list of commands.");
        
        // terminal input
        String input = "";
        Scanner scan = new Scanner(System.in);


        // end function
        while (!input.startsWith("end")) {
            input = scan.nextLine();
            // System.out.println(input);

            // help function
            if (input.startsWith("help")) {
                shoppingCart.help();
            }
            
            // login function
            if (input.startsWith("login")) {
                shoppingCart.login(input);
            }

            // list function
            if (input.startsWith("list")) {
                shoppingCart.list();
            }

            // add function
            if (input.startsWith("add")) {
                shoppingCart.add(input);
            }

            // delete function
            if (input.startsWith("delete")) {
                shoppingCart.delete(input);
            }

/*
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
