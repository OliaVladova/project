
import logInForm.LogIn;
import registrationForm.RegistrationForm;
import users.UserOfTV;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equals("Exit")){
            String[] tokens = input.split("\\s+");
            switch (tokens[0]){
                case "LogIn":
                    LogIn logIn = new LogIn(null);
                    UserOfTV user = logIn.user;
                    if (user!= null){
                        System.out.println("Successfully registration of user:" + user.getName());
                    }else {
                        System.out.println("Registration canceled!");
                    }
                    break;
                case "MakeRegistration":
                    RegistrationForm registrationForm = new RegistrationForm(null);
                    UserOfTV userToRegister = registrationForm.user;
                    if (userToRegister != null) {
                        System.out.println("Successfully registration of user:" + userToRegister.getName());
                    } else {
                        System.out.println("Registration canceled!");
                    }
                    break;
            }
            input = scanner.nextLine();
        }
    }
}
