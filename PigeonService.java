import java.util.Scanner;
import java.util.function.Supplier;

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
        homepage();
    }

    public static void homepage() {
        System.out.println("\n+======================================================+");
        System.out.println("|        WELCOME TO IJSE STOCK MANAGEMENT SYSTEM        |");
        System.out.println("+======================================================+\n");

        System.out.println("[1] Change the Credentials\t\t[2] Supplier Manage\n[3] Stock Manage\t\t\t[4] Log out\n[5] Exit the system\n");
        System.out.print("Enter an option to continue > ");
        byte option = input.nextByte();

        switch (option) {
            //case 1 -> changeTheCredentials();
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
}
