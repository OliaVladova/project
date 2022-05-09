import startUp.StartUp;

import javax.swing.*;

public class Main  extends JDialog {
    public static void main(String[] args) {
       // Scanner scanner = new Scanner(System.in);

     //   String input = scanner.nextLine();
       /* while (!input.equals("Exit")){
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

            }
            input = scanner.nextLine();
        }*/
        StartUp startUp = new StartUp(null);

    }
}
