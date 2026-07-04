import java.util.Scanner;

public class PigeonService {
    static Scanner input = new Scanner(System.in);

    static String username = "danujav";
    static String password = "1234";

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

            if (name.equals(username)) {
                System.out.println("Login is Success");
            } else {
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
            //case 2 -> supplierManage();
            //case 3 -> stockManage();
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
