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

    // item id, name, category id, supplier id, unit price, quantity
    static String[][] items = new String[0][6];

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
            case 1 -> addSuppliers();
            case 2 -> viewSuppliers();
            case 3 -> updateSupliers();
            case 4 -> deleteSuppliers();
            case 5 -> searchSuppliers();
            case 6 -> homepage();
            default -> {
                System.out.println("Invalid option. Please select again.\n");
                supplierManage();
            }
        }
    }

    public static void addSuppliers() {        
        System.out.println("\n+======================================================+");
        System.out.println("|                   ADD SUPPLIERS                      |");
        System.out.println("+======================================================+\n");

        System.out.print("Supplier ID: S");
        String sId = "S" + input.next();

        // Check duplicate ID
        if (findId(suppliers, sId)) {
            System.out.println("Supplier ID already exists. Try another Supplier ID!");
            addSuppliers();
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
            addSuppliers();
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

    public static void updateSupliers() {
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
                    updateSupliers();
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

    public static void deleteSuppliers() {
        System.out.println("\n+======================================================+");
        System.out.println("|                    DELETE SUPLIERS                   |");
        System.out.println("+======================================================+\n");
        System.out.print("Supplier ID : ");
        String id = input.next();

        boolean found = false;

        for (int i = 0; i < suppliers.length; i++) {
            if(id.equals(suppliers[i][0])) {
                suppliers = deleteFromArray(suppliers, id);
                System.out.println("Supplier deleted successfully! \n");
                viewSuppliers();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Supplier Id Not Found!!");
        }

        System.out.print("Do you want to delete another supplier? (Y/N): ");
        if (input.next().equalsIgnoreCase("Y")) {
            input.next();
            clearConsole();
            deleteSuppliers();
        } else {
            input.next();
            clearConsole();
            supplierManage();
        }
    }

    private static String[][] deleteFromArray(String[][] suppliers, String id) {
        String[][] temp = new String[suppliers.length-1][2];  //HERE DECREES THE LENGTH IN ARRAY -1

        int j = 0;
        for(int i = 0; i < suppliers.length; i++) {
            if(!id.equals(suppliers[i][0])) {
                //IF NOT EQUALS THERE COPY PREVIOUS DATA ON THE ARRAY
                temp[j] = suppliers[i];
                j++;
            }
        }

        return temp;
    }

    public static void searchSuppliers() {
        System.out.println("\n+======================================================+");
        System.out.println("|                   SEARCH SUPLIERS                    |");
        System.out.println("+======================================================+\n");

        System.out.print("Supplier ID: S");
        String id = input.next();

        boolean found = false;
        for (int i = 0; i < suppliers.length; i++) {
            if (suppliers[i][0].equals(id)) {
                System.out.println("Supplier ID   : " + suppliers[i][0]);
                System.out.println("Supplier Name : " + suppliers[i][1]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.print("can't find supplier id. try again!");
        }

        System.out.print("added successfully! Do you want to added another find(Y/N)? ");
        if (input.next().equalsIgnoreCase("Y")) {
            input.next();
            clearConsole();
            searchSuppliers();
        } else {
            input.next();
            clearConsole();
            supplierManage();
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
            case 1 -> manageItemCategories();
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

    public static void manageItemCategories() {

    while (true) {

        System.out.println("\n+======================================================+");
        System.out.println("|                MANAGE ITEM CATEGORIES                |");
        System.out.println("+======================================================+\n");

        System.out.println("[1] Add New Category");
        System.out.println("[2] View All Categories");
        System.out.println("[3] Update Category");
        System.out.println("[4] Delete Category");
        System.out.println("[5] Search Category");
        System.out.println("[6] Back to Stock Management");

        System.out.print("\nEnter an option to continue > ");
        byte option = input.nextByte();

        clearConsole();

        switch (option) {

            case 1 -> {
                System.out.println("\n+------------------------------------------+");
                System.out.println("|              ADD NEW CATEGORY            |");
                System.out.println("+------------------------------------------+\n");

                System.out.print("Enter Category ID : ");
                String id = input.next();

                boolean exists = false;

                for (String[] category : categories) {
                    if (category[0].equalsIgnoreCase(id)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("\nCategory ID already exists!");
                } else {

                    System.out.print("Enter Category Name : ");
                    String name = input.next();

                    String[][] temp = new String[categories.length + 1][2];

                    for (int i = 0; i < categories.length; i++) {
                        temp[i] = categories[i];
                    }

                    temp[temp.length - 1] = new String[]{id, name};

                    categories = temp;

                    System.out.println("\nCategory added successfully!");
                }

                System.out.println("\nPress Enter to continue...");
                input.next();
                clearConsole();
            }

            case 2 -> {
                viewItem();

                System.out.println("\nPress Enter to continue...");
                input.next();
                clearConsole();
            }

            case 3 -> {

                System.out.println("\n+------------------------------------------+");
                System.out.println("|              UPDATE CATEGORY             |");
                System.out.println("+------------------------------------------+\n");

                if (categories.length == 0) {
                    System.out.println("No categories found.");
                } else {

                    System.out.print("Enter Category ID: C");
                    String id = input.next();

                    boolean found = false;

                    for (String[] category : categories) {

                        if (category[0].equalsIgnoreCase(id)) {
                            System.out.println("Current Category Name : " + category[1]);
                            System.out.print("Enter New Category Name : ");
                            String newName = input.next();

                            category[1] = newName;

                            System.out.println("\nCategory updated successfully!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("\nCategory ID not found!");
                    }
                }

                System.out.println("\nPress Enter to continue...");
                input.next();
                clearConsole();
            }


            case 4 -> {

                System.out.println("\n+------------------------------------------+");
                System.out.println("|              DELETE CATEGORY             |");
                System.out.println("+------------------------------------------+\n");

                if (categories.length == 0) {
                    System.out.println("No categories found.");
                } else {

                    System.out.print("Enter Category ID: C");
                    String id = input.next();

                    int index = -1;

                    for (int i = 0; i < categories.length; i++) {
                        if (categories[i][0].equalsIgnoreCase(id)) {
                            index = i;
                            break;
                        }
                    }

                    if (index == -1) {
                        System.out.println("\nCategory ID not found!");
                    } else {

                        System.out.println("Category Name : "
                                + categories[index][1]);

                        System.out.print("\nAre you sure you want to delete? (Y/N) : ");
                        char confirm = input.next().toUpperCase().charAt(0);

                        if (confirm == 'Y') {

                            String[][] temp = new String[categories.length - 1][2];

                            int j = 0;

                            for (int i = 0; i < categories.length; i++) {

                                if (i != index) {
                                    temp[j++] = categories[i];
                                }
                            }

                            categories = temp;

                            System.out.println("\nCategory deleted successfully!");
                        } else {
                            System.out.println("\nDelete operation cancelled.");
                        }
                    }
                }

                System.out.println("\nPress Enter to continue...");
                input.next();
                clearConsole();
            }


            case 5 -> {

                System.out.println("\n+------------------------------------------+");
                System.out.println("|              SEARCH CATEGORY             |");
                System.out.println("+------------------------------------------+\n");

                System.out.print("Enter Category ID or Name : ");
                String search = input.next();

                boolean found = false;

                System.out.printf("%-15s %-20s%n",
                        "Category ID", "Category Name");

                System.out.println("------------------------------------------");

                for (String[] category : categories) {

                    if (category[0].equalsIgnoreCase(search)
                            || category[1].equalsIgnoreCase(search)) {

                        System.out.printf("%-15s %-20s%n",
                                category[0], category[1]);

                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("\nNo matching category found.");
                }
                System.out.println("\nPress Enter to continue...");
                input.next();
                clearConsole();
            }


            case 6 -> {
                clearConsole();
                stockManage();
                return;
            }

            default -> {
                System.out.println("\nInvalid option! Please try again.");
                System.out.println("\nPress Enter to continue...");
                input.next();
                clearConsole();
            }
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