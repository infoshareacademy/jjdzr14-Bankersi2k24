package pl.isa.view;

import pl.isa.model.BankAccount;

import java.util.Scanner;

public class SubMenu {
    public static void subMenu() {
        String newLine = System.lineSeparator();
        System.out.println("select options: "+"1.Account balance "+"2.Add a new account "+"3.User date "+"4.Account list ");
//        System.out.println("select options:"+newLine+"1.Account balance"+newLine+"2.Add a new account"+newLine+"3.User date"+newLine+"4.Account list");
        Scanner sc = new Scanner(System.in);
        int opcje = sc.nextInt();
        if (opcje == 1) {
            System.out.println("1.Account balance");

        } else if (opcje == 2) {

            System.out.println("2.Add a new account");
        } else if (opcje == 3) {
            System.out.println("3.User date");

        } else if (opcje == 4) {
            System.out.println("4.Account list");


        }else {
            System.out.println(" NO SUCH OPTION ");


    }



    }

}


