package pl.isa;

import pl.isa.view.WelcomeScreen;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();

        welcomeScreen.registrationScreen();
        welcomeScreen.loginScreen();


    }
}
