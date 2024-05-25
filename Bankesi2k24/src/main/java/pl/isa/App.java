package pl.isa;

import pl.isa.model.User;
import pl.isa.services.UserService;
import pl.isa.view.SubMenu;
import pl.isa.view.WelcomeScreen;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        User user = new User();
        UserService userService = new UserService();

        do {
            switch (welcomeScreen.action) {
                case 0:
                    welcomeScreen.showWelcomeScreen();
                    break;
                case 1:
                    String[] credentials = welcomeScreen.loginScreen();

                    user = UserService.findUser(credentials[0]);
                    if(user != null){
                        if(userService.verifyCredentials(user, credentials[0], credentials[1])){
                            System.out.println("Welcome!!!  " + credentials[0]);
                            welcomeScreen.action = 11;
                        }
                        else{
                            System.out.println("Invalid login or password");
                            welcomeScreen.action = 1;
                        }
                    }else{
                        System.out.println("Invalid login or password");
                        welcomeScreen.action = 0;
                    }
                    break;
                case 11:
                    SubMenu.subMenu();
                    welcomeScreen.action = 0;
                    break;
                case 2:
                    welcomeScreen.registrationScreen();
                    welcomeScreen.action = 0;
                    break;
                default:
                    break;
            }
        }while(welcomeScreen.action != -1);
    }
}
