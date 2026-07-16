package GDSE77_ITS1010_Sanduni_himasara;

import java.util.Arrays;
import java.util.Scanner;

public class PegionService{

    static Scanner input = new Scanner(System.in);

    static String username = "danujav";
    static String password = "1234";

    //SUPPLERS ARRAY
    static String[][] suppliers = new String[0][2]; // id , name

    // CATEGORIES ARRAY
    static String[][] categories = new String[0][3];

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
    clearConsole();
    System.out.println("\n+======================================================+");
    System.out.println("|                   ADD SUPPLIERS                      |");
    System.out.println("+======================================================+\n");

    System.out.print("Supplier ID: S");
    String num = input.next();               // read numeric part only
    String sId = "S" + num;                 // build full ID

    // Check duplicate ID using existing findId method
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

    System.out.print("\nDo you want to add another supplier? (Y/N): ");
    char choice = input.next().charAt(0);

    if (choice == 'Y' || choice == 'y') {
        clearConsole();
        addSuppliers();
    } else {
        clearConsole();
        supplierManage();   // return to supplier menu, not homepage
    }
}

    public static void growArray() {
        String[][] tempArray = new String[suppliers.length + 1][2];
            for (int i = 0; i < suppliers.length; i++) {
                tempArray[i] = suppliers[i];
            }
        suppliers = tempArray;
    }

    public static boolean findId(String[][] suppliers, String id) {
        for (int i = 0; i < suppliers.length; i++) {
            if (suppliers[i][0] != null &&
                    suppliers[i][0].equals(id)) {
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
            System.out.print("Supplier ID: S");
            String sId = "S" + input.next();

            // Find supplier index
            int userIndex = -1;

            for (int i = 0; i < suppliers.length; i++) {
                if (suppliers[i][0].equals(sId)) {
                    userIndex = i;
                    break;
                }
            }

            if (userIndex == -1) {
                System.out.println("Can't find supplier ID. Try again!\n");
                continue;
            }

            System.out.println("Supplier Name: "
                    + suppliers[userIndex][1]);

            while (true) {

                System.out.print("Enter the new supplier name: ");
                String name = input.next();

                if (suppliers[userIndex][1].equals(name)) {
                    System.out.println("Same as current name. Please enter another name!");
                    continue;
                }
                suppliers[userIndex][1] = name;
                break;
            }

            System.out.println("\nUpdated Successfully.");
            System.out.print("Do you want to update another supplier (Y/N)? : ");

            String choice = input.next();

            if (choice.equalsIgnoreCase("Y")) {
                clearConsole();
                updateSupliers();

            } else {
                clearConsole();
                supplierManage();
            }
            break;
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
            System.out.println("--------------------------------------------------------------");
            System.out.printf(
                    "%-15s %-20s\n",
                    "SUPPLIER ID",
                    "SUPPLIER NAME");
            System.out.println("--------------------------------------------------------------");

            for (int i = 0; i < suppliers.length; i++) {
                System.out.printf(
                        "%-15s %-20s\n",
                        suppliers[i][0],
                        suppliers[i][1]
                );
            }
        }

        System.out.print("\nDo you want to go to supplier manage page (Y/N): ");
        String choice = input.next();

        if (choice.equalsIgnoreCase("Y")) {
            clearConsole();
            supplierManage();
        } else {
            clearConsole();
            homepage();
        }
    }

    public static void deleteSuppliers() {
        clearConsole();
        System.out.println("\n+======================================================+");
        System.out.println("|                    DELETE SUPPLIERS                  |");
        System.out.println("+======================================================+\n");

        System.out.print("Supplier ID: S");
        String id = "S" + input.next();

        boolean found = false;

        for (int i = 0; i < suppliers.length; i++) {
            if (id.equals(suppliers[i][0])) {
                suppliers = deleteFromArray(suppliers, id);
                System.out.println("\nSupplier deleted successfully!\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nSupplier ID Not Found!!\n");
        }

        System.out.print("Do you want to delete another supplier? (Y/N): ");
        String choice = input.next();

        if (choice.equalsIgnoreCase("Y")) {
            clearConsole();
            deleteSuppliers();
        } else {
            clearConsole();
            supplierManage();
        }
    }

    private static String[][] deleteFromArray(
            String[][] suppliers,
            String id
    ){
        String[][] temp =new String[suppliers.length - 1][2];
        int j = 0;
        for (int i = 0; i < suppliers.length; i++) {
            if (!id.equals(suppliers[i][0])) {
                temp[j] = suppliers[i];
                j++;
            }
        }
        return temp;
    }

    public static void searchSuppliers() {
        clearConsole();
        System.out.println("\n+======================================================+");
        System.out.println("|                   SEARCH SUPPLIERS                   |");
        System.out.println("+======================================================+\n");

        System.out.print("Supplier ID: S");
        String id = "S" + input.next();

        boolean found = false;

        for (int i = 0; i < suppliers.length; i++) {
            if (suppliers[i][0].equals(id)) {
                System.out.println("\nSupplier ID   : "+ suppliers[i][0]);
                System.out.println("Supplier Name : "+ suppliers[i][1]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nCan't find supplier ID. Try again!");
        }
        System.out.print("\nDo you want to search another supplier (Y/N)? "
        );

        String choice = input.next();

        if (choice.equalsIgnoreCase("Y")) {
            clearConsole();
            searchSuppliers();
        } else {
            clearConsole();
            supplierManage();
        }
    }

    public static void stockManage() {
        System.out.println("\n+======================================================+");
        System.out.println("|                     STOCK MANAGE                     |");
        System.out.println("+======================================================+\n");

        System.out.println("[1] Manage Item categories\t\t[2] Manage Item\n[3] Get Items Supplier Wise\t\t[4] View Item Categories\n[5] Rank Item Per Unit Price\t\t[6] Home Page\n");
        
        System.out.print("Enter an option to continue > ");
        byte option = input.nextByte();

        clearConsole();

        switch (option) {
            case 1 -> manageItemCategories();
            case 2 -> manageItems();
            case 3 -> getItemsSupplierWise();
            case 4 -> {
                viewItemCategories();
                clearConsole();
                stockManage(); // Go back to stock menu
            }
            case 5 -> rankItemsPerUnitPrice();
            case 6 -> homepage();
            default -> {
                System.out.println("Invalid option. Please select again.\n");
                supplierManage();
            }
        }
    }   

   public static void manageItems() {

    while (true) {

        System.out.println("\n+======================================================+");
        System.out.println("|                      MANAGE ITEM                    |");
        System.out.println("+======================================================+\n");

        System.out.println("[1] Add New Item");
        System.out.println("[2] View All Item");
        System.out.println("[3] Update Item");
        System.out.println("[4] Delete Item");
        System.out.println("[5] Search Item");
        System.out.println("[6] Back to Stock Management");

        System.out.print("\nEnter an option to continue > ");
        byte option = input.nextByte();

        clearConsole();

            switch (option) {

                case 1 -> addItem();
                case 2 -> viewAllItems();
                case 3 -> updateItems();
                case 4 -> deleteItems();
                case 5 -> searchItems();
                case 6 -> {
                    clearConsole();
                    stockManage();
                    return;
                }
                default -> {
                    System.out.println("Invalid option. Please try again!");
                }
            }
            clearConsole();
        }
    }

    public static void searchItems() {

        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("|                         SEARCH ITEM                           |");
        System.out.println("+---------------------------------------------------------------+\n");

        if (items == null || items.length == 0) {
            System.out.println("No items found.");
            return;
        }

        input.nextLine();

        System.out.print("Enter Item Code or Description: ");
        String search = input.nextLine();

        boolean found = false;

        for (String[] item : items) {

            if (item[0].equalsIgnoreCase(search)
                    || item[1].toLowerCase().contains(search.toLowerCase())) {

                System.out.println("\nItem Found!");

                System.out.println("----------------------------------------");
                System.out.println("Item Code  : " + item[0]);
                System.out.println("Description: " + item[1]);
                System.out.println("Category ID : " + item[2]);
                System.out.println("Supplier ID : " + item[3]);
                System.out.println("Unit Price  : " + item[4]);
                System.out.println("Quantity    : " + item[5]);
                System.out.println("----------------------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo matching item found!");
        }
    }

    public static void deleteItems() {

        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("|                         DELETE ITEM                           |");
        System.out.println("+---------------------------------------------------------------+\n");

        if (items == null || items.length == 0) {
            System.out.println("No items found.");
            return;
        }

        System.out.print("Enter Item Code to delete: ");
        String code = input.next();

        int index = -1;

        for (int i = 0; i < items.length; i++) {
            if (items[i][0].equalsIgnoreCase(code)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("\nItem not found!");
            return;
        }

        System.out.println("\nItem Found:");

        System.out.println("Item Code  : " + items[index][0]);
        System.out.println("Description: " + items[index][1]);

        System.out.print("\nAre you sure you want to delete this item? (Y/N): ");
        String choice = input.next();

        if (!choice.equalsIgnoreCase("Y")) {
            System.out.println("\nDelete operation cancelled.");
            return;
        }

        String[][] temp = new String[items.length - 1][6];

        int tempIndex = 0;

        for (int i = 0; i < items.length; i++) {
            if (i != index) {
                temp[tempIndex] = items[i];
                tempIndex++;
            }
        }

        items = temp;

        System.out.println("\nItem deleted successfully!");
    }

    public static void updateItems() {

        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("|                         UPDATE ITEM                           |");
        System.out.println("+---------------------------------------------------------------+\n");

        if (items == null || items.length == 0) {

            System.out.println("No items found.");
            return;
        }

        System.out.print("Enter Item Code to update: ");
        String code = input.next();

        int index = -1;

        for (int i = 0; i < items.length; i++) {
            if (items[i][0].equalsIgnoreCase(code)) {
                index = i;
                break;
            }
        }

        if (index == -1) {

            System.out.println("\nItem not found!");
            return;
        }

        System.out.println("\nCurrent Item Details:");

        System.out.println("Item Code  : " + items[index][0]);
        System.out.println("Description: " + items[index][1]);
        System.out.println("Category ID : " + items[index][2]);
        System.out.println("Supplier ID : " + items[index][3]);
        System.out.println("Unit Price  : " + items[index][4]);
        System.out.println("Quantity    : " + items[index][5]);

        input.nextLine();

        System.out.print("\nEnter New Description: ");
        String description = input.nextLine();

        double unitPrice;

        while (true) {

            System.out.print("Enter New Unit Price: ");

            if (input.hasNextDouble()) {
                unitPrice = input.nextDouble();
                if (unitPrice > 0) {
                    break;
                }

                System.out.println("Price must be greater than 0!");

            } else {
                System.out.println("Invalid price!");
                input.next();
            }
        }

        int quantity;

        while (true) {

            System.out.print("Enter New Quantity: ");

            if (input.hasNextInt()) {
                quantity = input.nextInt();
                if (quantity >= 0) {
                    break;
                }

                System.out.println("Quantity cannot be negative!");

            } else {

                System.out.println("Invalid quantity!");
                input.next();
            }
        }

        items[index][1] = description;
        items[index][4] = String.valueOf(unitPrice);
        items[index][5] = String.valueOf(quantity);

        System.out.println("\nItem updated successfully!");
    }

    public static void viewAllItems() {
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("|                           VIEW ITEMS                          |");
        System.out.println("+---------------------------------------------------------------+");

        if (items == null || items.length == 0) {
            System.out.println("No items found.\n");
            return;
        }

        System.out.printf(
                "%-12s %-20s %-12s %-12s %-10s %-10s%n",
                "Item Code",
                "Description",
                "Category ID",
                "Supplier ID",
                "Price",
                "Quantity"
        );
        System.out.println("--------------------------------------------------------------------------------");
        for (String[] item : items) {
            System.out.printf(
                    "%-12s %-20s %-12s %-12s %-10s %-10s%n",
                    item[0],
                    item[1],
                    item[2],
                    item[3],
                    item[4],
                    item[5]
            );
        }
        System.out.println("-------------------------------------------------------------------------------");
    
        System.out.println("\nPress Enter to continue...");
        input.nextLine();
    
    }

    public static void addItem() {
    clearConsole();

    System.out.println("\n+---------------------------------------------------------------------+");
    System.out.println("|                              ADD ITEM                               |");
    System.out.println("+---------------------------------------------------------------------+\n");

    // CHECK CATEGORIES
        if (categories.length == 0) {

            System.out.println(
                    "OOPS! It seems that you don't have any item categories in the system.\n"
            );

            System.out.print("Do you want to add a new item category? (Y/N): ");
            String choice = input.next();

            if (choice.equalsIgnoreCase("Y")) {

                clearConsole();
                manageItemCategories();

            } else {

                clearConsole();
                stockManage();
            }

            return;
        }

        // CHECK SUPPLIERS
        if (suppliers.length == 0) {

            System.out.println(
                    "OOPS! It seems that you don't have any suppliers in the system.\n"
            );

            System.out.print("Do you want to add a new supplier? (Y/N): ");
            String choice = input.next();

            if (choice.equalsIgnoreCase("Y")) {

                clearConsole();
                supplierManage();

            } else {

                clearConsole();
                stockManage();
            }

            return;
        }

        // ITEM CODE
        String code;

        while (true) {

            System.out.print("Item Code : I");
            code = "I" + input.next();

            boolean exists = false;

            for (String[] item : items) {

                if (item[0] != null &&
                        item[0].equalsIgnoreCase(code)) {

                    exists = true;
                    break;
                }
            }

            if (exists) {

                System.out.println(
                        "Item code already exists. Try another item code!\n"
                );

            } else {

                break;
            }
        }

        // DISPLAY SUPPLIERS
        System.out.println("\nSupplier List:");

        System.out.println("+-----+----------------+----------------------+");
        System.out.println("|  #  | SUPPLIER ID    | SUPPLIER NAME        |");
        System.out.println("+-----+----------------+----------------------+");

        for (int i = 0; i < suppliers.length; i++) {

            System.out.printf(
                    "| %-3d | %-14s | %-20s |%n",
                    (i + 1),
                    suppliers[i][0],
                    suppliers[i][1]
            );
        }

        System.out.println("+-----+----------------+----------------------+");

        int supplierNumber;

        while (true) {

            System.out.print("Enter the supplier number: ");
            supplierNumber = input.nextInt();

            if (supplierNumber >= 1 &&
                    supplierNumber <= suppliers.length) {

                break;

            } else {

                System.out.println(
                        "Invalid supplier number. Please try again!"
                );
            }
        }

        // DISPLAY CATEGORIES
        System.out.println("\nItem Categories:");

        System.out.println("+-----+----------------+----------------------+");
        System.out.println("|  #  | CATEGORY ID    | CATEGORY NAME        |");
        System.out.println("+-----+----------------+----------------------+");

        for (int i = 0; i < categories.length; i++) {

            System.out.printf(
                    "| %-3d | %-14s | %-20s |%n",
                    (i + 1),
                    categories[i][0],
                    categories[i][1]
            );
        }

        System.out.println("+-----+----------------+----------------------+");

        int categoryNumber;

        while (true) {

            System.out.print("Enter the category number: ");
            categoryNumber = input.nextInt();

            if (categoryNumber >= 1 &&
                    categoryNumber <= categories.length) {

                break;

            } else {

                System.out.println(
                        "Invalid category number. Please try again!"
                );
            }
        }

        // DESCRIPTION
        input.nextLine();

        System.out.print("Description: ");
        String description = input.nextLine();

        // UNIT PRICE
        double unitPrice;

        while (true) {

            System.out.print("Unit Price: ");

            if (input.hasNextDouble()) {

                unitPrice = input.nextDouble();

                if (unitPrice > 0) {
                    break;
                }

                System.out.println(
                        "Unit price must be greater than 0!"
                );

            } else {

                System.out.println(
                        "Invalid price. Please enter a number!"
                );

                input.next();
            }
        }

        // QUANTITY
        int quantity;

        while (true) {

            System.out.print("Qty On Hand: ");

            if (input.hasNextInt()) {

                quantity = input.nextInt();

                if (quantity >= 0) {
                    break;
                }

                System.out.println(
                        "Quantity cannot be negative!"
                );

            } else {

                System.out.println(
                        "Invalid quantity. Please enter a number!"
                );

                input.next();
            }
        }

        // GROW ITEMS ARRAY
        String[][] temp = new String[items.length + 1][6];

        for (int i = 0; i < items.length; i++) {

            temp[i] = items[i];
        }

        items = temp;

        // ADD ITEM
        int index = items.length - 1;

        items[index][0] = code;
        items[index][1] = description;
        items[index][2] = categories[categoryNumber - 1][0];
        items[index][3] = suppliers[supplierNumber - 1][0];
        items[index][4] = String.valueOf(unitPrice);
        items[index][5] = String.valueOf(quantity);

        System.out.println("\nItem added successfully!");

        System.out.print(
                "\nDo you want to add another item? (Y/N): "
        );

        String choice = input.next();

        if (choice.equalsIgnoreCase("Y")) {

            addItem();

        } else {

            clearConsole();
            manageItems();
        }
    }

    public static void manageItemCategories() {
    while (true) {

        System.out.println("\n+======================================================+");
        System.out.println("|                MANAGE ITEM CATEGORIES               |");
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

                    System.out.print("Enter Category ID: C");
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
                        input.nextLine();

                        System.out.print("Enter Category Name : ");
                        String name = input.nextLine();

                        String[][] temp = new String[categories.length + 1][3];

                        for (int i = 0; i < categories.length; i++) {
                            temp[i] = categories[i];
                        }

                        temp[temp.length - 1] = new String[]{id, name, "Available"};

                        categories = temp;

                        System.out.println("\nCategory added successfully!");
                    }

                    manageItemCategories();
                    clearConsole();
                }


            
                case 2 -> {
                System.out.println("\n+======================================================+");
                System.out.println("|                 VIEW ALL CATEGORIES                  |");
                System.out.println("+======================================================+\n");

                if (categories.length == 0) {

                    System.out.println("No categories found.");
                    manageItemCategories();
                    return;
                }

                System.out.println("----------------------------------------------------------");

                System.out.printf(
                        "%-15s %-20s %-15s%n",
                        "Category ID",
                        "Category Name",
                        "Status"
                );

                System.out.println("----------------------------------------------------------");

                for (String[] category : categories) {
                    System.out.printf(
                            "%-15s %-20s %-15s%n",
                            category[0],
                            category[1],
                            category[2]);
                        }
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

                                found = true;

                                System.out.println("\nCurrent Category Name : "+ category[1]);
                                System.out.println("Current Status : "+ category[2]);

                                input.nextLine();

                                System.out.print("\nEnter New Category Name : ");
                                String newName = input.nextLine();

                                System.out.print("Enter New Status (Available/Unavailable) : ");

                                String newStatus = input.next();

                                if (!newStatus.equalsIgnoreCase("Available")
                                        && !newStatus.equalsIgnoreCase("Unavailable")) {

                                    System.out.println("\nInvalid status! Update cancelled.");

                                } else {
                                    category[1] = newName;
                                    category[2] =
                                            newStatus.substring(0, 1).toUpperCase()
                                            + newStatus.substring(1).toLowerCase();

                                    System.out.println(
                                            "\nCategory updated successfully!"
                                    );
                                }
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("\nCategory ID not found!");
                        }
                    }
                    manageItemCategories();
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

                        boolean found = false;

                        for (String[] category : categories) {

                            if (category[0].equalsIgnoreCase(id)) {

                                found = true;

                                System.out.println("\nCategory Name : "
                                        + category[1]);

                                System.out.println("Current Status : "
                                        + category[2]);

                                System.out.print(
                                        "\nDo you want to make this category "
                                        + "Unavailable? (Y/N) : "
                                );

                                char confirm = input.next().charAt(0);

                                if (confirm == 'Y'||confirm == 'y') {
                                    category[2] = "Unavailable";

                                    System.out.println(
                                            "\nCategory status changed to "
                                            + "Unavailable successfully!"
                                    );

                                } else {
                                    System.out.println("\nDelete operation cancelled."
                                    );
                                }
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("\nCategory ID not found!");
                        }
                    }

                    manageItemCategories();
                    clearConsole();
                }

                case 5 -> {

                    System.out.println("\n+------------------------------------------+");
                    System.out.println("|              SEARCH CATEGORY             |");
                    System.out.println("+------------------------------------------+\n");

                    input.nextLine();

                    System.out.print("Enter Category ID or Name : ");
                    String search = input.nextLine();

                    boolean found = false;

                    System.out.println(
                            "\n----------------------------------------------------------"
                    );

                    System.out.printf(
                            "%-15s %-20s %-15s%n",
                            "Category ID",
                            "Category Name",
                            "Status"
                    );

                    System.out.println(
                            "----------------------------------------------------------"
                    );

                    for (String[] category : categories) {

                        if (category[0].equalsIgnoreCase(search)
                                || category[1].equalsIgnoreCase(search)) {

                            System.out.printf(
                                    "%-15s %-20s %-15s%n",
                                    category[0],
                                    category[1],
                                    category[2]
                            );

                            found = true;
                        }
                    }

                    if (!found) {

                        System.out.println(
                                "\nNo matching category found."
                        );
                    }

                    manageItemCategories();
                    clearConsole();
                }


                case 6 -> {
                    clearConsole();
                    stockManage();
                    return;
                }

                default -> {

                    System.out.println(
                            "\nInvalid option! Please try again."
                    );
                    manageItemCategories();
                    clearConsole();
                }
            }
        }
    }

    public static void rankItemsPerUnitPrice() {

    System.out.println("+-------------------------------------------------+");
    System.out.println("|              RANKED UNIT PRICE                  |");
    System.out.println("+-------------------------------------------------+");

    if (items == null || items.length == 0) {
        System.out.println("\nNo items found.");
        input.nextLine();
        input.nextLine();
        stockManage();
        return;
    }

    String[][] sortedItems = new String[items.length][6];

    for (int i = 0; i < items.length; i++) {
        sortedItems[i] = Arrays.copyOf(items[i], items[i].length);
    }

    // Sort by Unit Price - item[4]
    for (int i = 0; i < sortedItems.length - 1; i++) {

        for (int j = 0; j < sortedItems.length - 1 - i; j++) {

            double price1 = Double.parseDouble(sortedItems[j][4]);
            double price2 = Double.parseDouble(sortedItems[j + 1][4]);

            if (price1 > price2) {

                String[] temp = sortedItems[j];
                sortedItems[j] = sortedItems[j + 1];
                sortedItems[j + 1] = temp;
            }
        }
    }

    System.out.println(
            "+------------+--------------------+------------+------------+------------+"
    );

    System.out.printf(
            "%-12s %-20s %-12s %-12s %-12s %-10s%n",
            "ITEM CODE",
            "DESCRIPTION",
            "CATEGORY ID",
            "SUPPLIER ID",
            "UNIT PRICE",
            "QUANTITY"
    );

    System.out.println(
            "----------------------------------------------------------------------------"
    );

    for (String[] item : sortedItems) {

        System.out.printf(
                "%-12s %-20s %-12s %-12s %-12s %-10s%n",
                item[0],
                item[1],
                item[2],
                item[3],
                item[4],
                item[5]
        );
    }

    System.out.println(
            "----------------------------------------------------------------------------"
    );

    System.out.print("\nDo you want to go stock manage page? (Y/N): ");

    input.nextLine();
    String choice = input.nextLine();

    if (choice.equalsIgnoreCase("Y")) {
        stockManage();
    } else {
        homepage();
    }
}

    public static void getItemsSupplierWise() {

        clearConsole();

        System.out.println("+---------------------------------------------------------------+");
        System.out.println("|                    SEARCH SUPPLIER WISE                       |");
        System.out.println("+---------------------------------------------------------------+");

        if (suppliers.length == 0) {
            System.out.println("\nNo suppliers found.");
            input.nextLine();
            input.nextLine();
            stockManage();
            return;
        }

        boolean searchAgain = true;

        while (searchAgain) {

            System.out.print("\nEnter Supplier ID: ");

            String id = input.next();

            String supplierName = null;

            for (int i = 0; i < suppliers.length; i++) {

                if (suppliers[i][0].equalsIgnoreCase(id)) {

                    supplierName = suppliers[i][1];
                    break;
                }
            }

            if (supplierName == null) {

                System.out.println("\nSupplier not found. Try again!");

            } else {

                System.out.println("\nSupplier Name: " + supplierName);

                System.out.println(
                        "----------------------------------------------------------------------------"
                );

                System.out.printf(
                        "%-12s %-20s %-12s %-12s %-12s %-10s%n",
                        "ITEM CODE",
                        "DESCRIPTION",
                        "CATEGORY ID",
                        "SUPPLIER ID",
                        "UNIT PRICE",
                        "QUANTITY"
                );

                System.out.println(
                        "----------------------------------------------------------------------------"
                );

                boolean found = false;

                for (int i = 0; i < items.length; i++) {

                    // Supplier ID is item[3]
                    if (items[i][3].equalsIgnoreCase(id)) {

                        found = true;

                        System.out.printf(
                                "%-12s %-20s %-12s %-12s %-12s %-10s%n",
                                items[i][0],
                                items[i][1],
                                items[i][2],
                                items[i][3],
                                items[i][4],
                                items[i][5]
                        );
                    }
                }

                if (!found) {
                    System.out.println(
                            "\nNo items found for this supplier."
                    );
                }

                System.out.println(
                        "----------------------------------------------------------------------------"
                );
            }

            System.out.print(
                    "\nDo you want to do another search? (Y/N): "
            );

            String again = input.next();

            searchAgain = again.equalsIgnoreCase("Y");

            if (searchAgain) {
                clearConsole();
            }
        }

        clearConsole();
        stockManage();
    }    

    public static void viewItemCategories() {

    clearConsole();

    System.out.println("\n+======================================================+");
    System.out.println("|                 VIEW ITEM CATEGORIES                 |");
    System.out.println("+======================================================+\n");

    if (categories.length == 0) {

        System.out.println("No categories found.");

    } else {

        System.out.println("+----------------+----------------------+----------------+");
        System.out.printf("| %-14s | %-20s | %-14s |%n",
                "CATEGORY ID",
                "CATEGORY NAME",
                "STATUS");
        System.out.println("+----------------+----------------------+----------------+");

        for (String[] category : categories) {

            System.out.printf("| %-14s | %-20s | %-14s |%n",
                    category[0],
                    category[1],
                    category[2]);
        }

        System.out.println("+----------------+----------------------+----------------+");
    }

    System.out.print("\nPress Enter to continue...");
    input.nextLine();
    input.nextLine();

    stockManage();
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