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
        homepage();
    }

    private static void homepage() {
       System.out.println("\n+======================================================+");
       System.out.println("|                       HOME PAGE                      |");
       System.out.println("+======================================================+\n");
    }
}
