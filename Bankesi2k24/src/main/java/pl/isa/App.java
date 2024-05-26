package pl.isa;

import pl.isa.model.User;
import pl.isa.services.UserService;
import pl.isa.view.SubMenu;
import pl.isa.services.FormService;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        FormService formService = new FormService();
        User user;
        UserService userService = new UserService();

        do {
            switch (formService.action) {
                case 0:
                    formService.mainForm();
                    break;
                case 1:
                    String[] credentials = formService.loginForm();

                    user = UserService.findUser(credentials[0]);
                    if(user != null){
                        if(userService.verifyCredentials(user, credentials[0], credentials[1])){
                            System.out.println("Welcome!!!  " + credentials[0]);
                            formService.action = 11;
                        }
                        else{
                            System.out.println("Invalid login or password");
                            formService.action = 1;
                        }
                    }else{
                        System.out.println("Invalid login or password");
                        formService.action = 0;
                    }
                    break;
                case 11:
                    SubMenu.subMenu();
                    formService.action = 0;
                    break;
                case 2:
                    formService.registrationScreen();
                    formService.action = 0;
                    break;
                default:
                    break;
            }
        }while(formService.action != -1);
    }
}
