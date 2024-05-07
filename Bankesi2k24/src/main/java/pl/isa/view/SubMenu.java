package pl.isa.view;

import pl.isa.model.BankAccount;

import java.util.Scanner;

public class SubMenu {
    public static void main(String[] args) {
        String newLine = System.lineSeparator();
        System.out.println("wybierz opcje:"+newLine+"1.Account balance"+newLine+"2.Add a new account"+newLine+"3.User date"+newLine+"4.Account list");
        Scanner sc = new Scanner(System.in);
        int opcje = sc.nextInt();
        if (opcje == 1) {
            System.out.println();

        } else if (opcje == 2) {



            System.out.println();
        } else if (opcje == 3) {
            System.out.println();

        } else if (opcje == 4) {
            System.out.println();


        }else {
            System.out.println(" Brak takiej opcji");


    }



    }

}


