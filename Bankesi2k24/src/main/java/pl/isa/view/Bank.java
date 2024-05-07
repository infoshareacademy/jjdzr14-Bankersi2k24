package pl.isa.view;

public class Bank {
    private Konto [] konta;
    private int liczbaKont;

    public Bank(){
        liczbaKont = 0; //to jest w zasadzie niepotrzebne
        konta = new Konto[10];
    }
    public void dodajKonto(Konto konto){
        if (liczbaKont>= konta.length){
            throw new IllegalStateException("Bank pelen");
        }

        konta[liczbaKont] = konto;
        liczbaKont++;
    }

    public Konto szukajKonta(int numer){
        if (numer<0 || numer >= liczbaKont){
            throw new IllegalArgumentException("Zly numer konta");
        }
        return konta[numer];
    }


    public static void main(String args[]){

        Konto konto1 = new Konto(50000);
        Konto konto2 = new Konto(15000);

        Bank bank = new Bank();

        bank.dodajKonto(konto1);
        bank.dodajKonto(konto2);

    }

}
