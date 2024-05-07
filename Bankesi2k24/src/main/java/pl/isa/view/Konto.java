package pl.isa.view;

public class Konto {
    private double stanKonta;

    Konto (double stanKonta){
        this.stanKonta = stanKonta;
    }

    public void wplac(double kwota){
        if(kwota>0)
            stanKonta = (stanKonta + kwota) - 2.50;
        else{
            System.out.println("Nie możesz wpłacić ujemnej kwoty !!!");
        }
    }

    public void wyplac(double kwota){
        if((stanKonta-kwota) >= 0)
            stanKonta = (stanKonta - kwota) - 2.50;
        else{
            System.out.println("Nie masz tyle środków na koncie !!!");
        }
    }

    public double stanKonta(){
        return stanKonta;
    }

    public void saldo(){
        System.out.println("Stan konta wynosi:" + stanKonta());
    }
}
