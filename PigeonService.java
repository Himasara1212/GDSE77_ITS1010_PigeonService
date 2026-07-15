import java.util.Arrays;
import java.util.Scanner;

public class PigeonService {
    static Scanner input = new Scanner(System.in);

    static String username = "danujav";
    static String password = "1234";

    //SUPPLERS ARRAY
    static String[][] suppliers = new String[0][2]; // id , name

    // CATEGORIES ARRAY
    static String[][] categories = new String[0][2];

    static int userIndex = 0; 

    public static void main(String [] args){
        loginpage();
    }

    public static void loginpage() {
       System.out.println("+======================================================+");
       System.out.println("|                      LOGIN PAGE                      |");
       System.out.println("+======================================================+\n");

       String name = "";

        while (!username.equals(name)) {
            System.out.print("User Name : ");
            name = input.nextLine();

            if (!name.equals(username)) {
                System.out.println("User name is incorrect. Please try again!\n");
            }
        }

        String pw = "";

        while (!password.equals(pw)) {
            System.out.print("Password : ");
            pw = input.nextLine();

            if (pw.equals(password)) {
                System.out.println("Password is Correct");
            } else {
                System.out.println("Password is invalid. Please try again!\n");
            }
        }
        clearConsole();
        homepage();
    }

    public static void homepage() {
        System.out.println("\n+======================================================+");
        System.out.println("|        WELCOME TO IJSE STOCK MANAGEMENT SYSTEM       |");
        System.out.println("+======================================================+\n");

        System.out.println("[1] Change the Credentials\t\t[2] Supplier Manage\n[3] Stock Manage\t\t\t[4] Log out\n[5] Exit the system\n");
        System.out.print("Enter an option to continue > ");
        byte option = input.nextByte();

        clearConsole();

        switch (option) {
            case 1 -> changeTheCredentials();
            case 2 -> supplierManage();
            case 3 -> stockManage();
            case 4 -> loginpage();
            case 5 -> System.exit(0);
            default -> {
                System.out.println("Invalid option. Please select again!\n");
                homepage();
            }
        }
    }

    public static void changeTheCredentials() {
        System.out.println("\n+======================================================+");
        System.out.println("|                   CREDENTIAL MANAGE                  |");
        System.out.println("+======================================================+\n");

        System.out.print("Please enter the user name to verify it's you: ");
        String eName = input.next();

        if(!eName.equals(username)){
            System.out.println("Invalid user name. Try again!");
            return;
        }else{
            System.out.println("Hey "+ eName+"\n");
        }
        
        System.out.print("Do you want to change username (Y/N): ");
        char choice = input.next().charAt(0);

        if (choice == 'Y' || choice == 'y') {

        System.out.print("\nEnter your new username: ");
        String newusername = input.next();
        username = newusername;

        System.out.print("Username changed successfully! ");
        }

        System.out.print("Do you want to Change Password (Y/N): ");
        char change = input.next().charAt(0);

        if (change == 'Y' || change == 'y') {

            System.out.print("\nEnter your current password: ");
            String ePw = input.next();

            if (!ePw.equals(password)) {
                System.out.println("Incorrect password. Try again!");
                return;
            }

            System.out.print("\nEnter your new password: ");
            String newPw = input.next();
            password = newPw;

            System.out.print("Password changed successfully! ");
        }
            System.out.print("Do you want to go to home page (Y/N): ");
            char home = input.next().charAt(0);
        
        clearConsole();
            
        if (home == 'y' || home == 'Y') {
            homepage();
        } else {
            loginpage();
            
        }
    }

    public static void supplierManage() {

        System.out.println("\n+======================================================+");
        System.out.println("|                    SUPPLIER MANAGE                   |");
        System.out.println("+======================================================+\n");

        System.out.println("[1] Add Supplier\t\t[2] View Supplier\n[3] Update Supplier\t\t[4] Delete Supplier\n[5] Search Supplier\t\t[6] Back to Home\n");
    
        System.out.print("Enter an option to continue > ");
        byte option = input.nextByte();

        clearConsole();

        switch (option) {
            case 1 -> addSupplier();
            case 2 -> viewSuppliers();
            //case 3 -> updateSuppliers();
            //case 4 -> deleteSuppliers();
            //case 5 -> searchSuppliers();
            case 6 -> homepage();
            default -> {
                System.out.println("Invalid option. Please select again.\n");
                supplierManage();
            }
        }
    }

    public static void addSupplier() {        
        System.out.println("\n+======================================================+");
        System.out.println("|                    ADD SUPPLIER                      |");
        System.out.println("+======================================================+\n");

        System.out.print("Supplier ID: S");
        String sId = "S" + input.next();

        // Check duplicate ID
        if (findId(suppliers, sId)) {
            System.out.println("Supplier ID already exists. Try another Supplier ID!");
            addSupplier();
            return;
        }

        System.out.print("Supplier Name: ");
        String name = input.next();

        growArray();

        // Add supplier
        suppliers[suppliers.length - 1][0] = sId;
        suppliers[suppliers.length - 1][1] = name;

        System.out.println("\nSupplier Added Successfully!");
        System.out.println(Arrays.deepToString(suppliers));

        System.out.print("\nDo you want to add another supplier? (Y/N): ");
        char choice = input.next().charAt(0);

        if (choice == 'Y' || choice == 'y') {
            clearConsole();
            addSupplier();
        } else {
            clearConsole();
            homepage();
        }
    }

    public static void growArray() {
        String[][] tempArray = new String[suppliers.length+1][2]; // id , name

        for(int i = 0 ; i < suppliers.length; i++ ){
            tempArray[i] = suppliers[i];
        }
        suppliers = tempArray;
    }

    public static boolean findId(String[][] suppliers, String id) {
        for (int i = 0; i < suppliers.length; i++) {
            if (suppliers[i][0] != null && suppliers[i][0].equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void updateSupllier() {
        clearConsole();
        System.out.println("\n+======================================================+");
        System.out.println("|                  UPDATE SUPPLIER                    |");
        System.out.println("+======================================================+\n");

            while (true) {
            System.out.print("Supplier id: S");
            String sId = input.next();

            if (findId(suppliers, sId) == false) {
                System.out.println("can't find supplier id. try again!");
                continue;
            }

            System.out.println("Suplier name: " + suppliers[userIndex][1]);
            
            while (true) {
                System.out.print("Enter the new supplier name : ");
                String name = input.next();

                if (suppliers[userIndex][1].equals(name)) {

                    System.out.println("Same as current name. please enter another name!");
                    continue;

                }
                suppliers[userIndex][1] = name;
                break;
            }
            System.out.print("Updated Successfully. ");

            while (true) {
                System.out.print("Do you want update another supplier(Y/N) ? : ");
                String choice = input.next();

                if (choice.equals("Y") || choice.equals("y")) {
                    updateSupllier();
                } else {
                    supplierManage();
                }
            }
        }
    }
    
    public static void viewSuppliers() {
        clearConsole();
        System.out.println("\n+======================================================+");
        System.out.println("|                    VIEW SUPPLIER                     |");
        System.out.println("+======================================================+\n");

         if (suppliers.length == 0) {
            System.out.println("No suppliers found.\n");
        } else {    
            // Print headings
            System.out.println("--------------------------------------------------------------");
            System.out.printf("%-15s %-20s\n", "SUPPLIER ID", "SUPPLIER NAME");
            System.out.println("--------------------------------------------------------------");

            // Print each supplier
            for (int i = 0; i < suppliers.length; i++) {
                System.out.printf("%-15s %-20s\n", suppliers[i][0], suppliers[i][1]);
            }
        }

        System.out.print("\nDo you want to go to supplier manage page (Y/N): ");
        if (input.next().equalsIgnoreCase("Y")) {
            input.next();
            clearConsole();
            supplierManage();
        } else {
            input.next();
            clearConsole();
            homepage();
        }
    }











    public static void stockManage() {
        System.out.println("\n+======================================================+");
        System.out.println("|                     STOCK MANAGE                     |");
        System.out.println("+======================================================+\n");

        System.out.println("[1] Manage Item categories\t\t[2] Add Item\n[3] Get Items Supplier Wise\t\t[4] View Item\n[5] Update Item\t\t\t\t[6] Delete Item\n[7] Rank Item Per Unit Price\t\t[8] Home Page\n");
        
        System.out.print("Enter an option to continue > ");
        byte option = input.nextByte();

        clearConsole();

        switch (option) {

            // case 1 -> manageItemCategories();
            // case 2 -> addItem();
            // case 3 -> getItemsSupplierWise();
            case 4 -> {
                viewItem();
                System.out.println("\nPress Enter to return to Stock Management...");
                input.next(); // Wait for Enter
                clearConsole();
                stockManage(); // Go back to stock menu
            }
            // case 5 -> updateItem();
            // case 6 -> deleteItem();
            // case 7 -> rankItemPerUnitPrice();
            case 8 -> homepage();
            default -> {
                System.out.println("Invalid option. Please select again.\n");
                supplierManage();
            }
        }
    }

    public static void viewItem() {

        System.out.println("----------------------------------------------------");
        System.out.println("             View All Item Categories              ");
        System.out.println("----------------------------------------------------");

        if (categories == null || categories.length == 0) {
            System.out.println("No categories found.\n");
            return;
        }

        System.out.printf("%-15s %-20s%n","Category ID","Category Name");
        System.out.println("------------------------------------------");

        for (String[] category : categories) {
            System.out.printf("%-15s %-20s%n",category[0],category[1]);
        }
    }

    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("Could not clear screen");
        }
    }
}
