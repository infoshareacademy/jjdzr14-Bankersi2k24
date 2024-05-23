package pl.isa;

import pl.isa.view.SubMenu;
import pl.isa.view.WelcomeScreen;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();

        switch (welcomeScreen.showWelcomeScreen()){
            case 1:
                welcomeScreen.loginScreen();
                break;
            case 2:
                welcomeScreen.registrationScreen();
                break;
            default:
        }



        SubMenu.subMenu();

    }
}
