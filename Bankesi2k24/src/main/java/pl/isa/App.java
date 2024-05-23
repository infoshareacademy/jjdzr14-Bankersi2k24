package pl.isa;

import pl.isa.model.User;
import pl.isa.view.SubMenu;
import pl.isa.view.WelcomeScreen;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        do {
            switch (welcomeScreen.action) {
                case 0:
                    welcomeScreen.showWelcomeScreen();
                case 1:
                    welcomeScreen.loginScreen();
                    break;
                case 2:
                    welcomeScreen.registrationScreen();
                    break;
                default:
                    break;
            }
        }while(welcomeScreen.action == -1);



//        SubMenu.subMenu();

    }
}
