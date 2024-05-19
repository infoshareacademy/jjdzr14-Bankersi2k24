package pl.isa;

import pl.isa.dataAccess.Connector;
import pl.isa.dataAccess.FileNames;
import pl.isa.dataAccess.ObjectToJson;
import pl.isa.model.BankAccount;
import pl.isa.model.User;

import java.util.List;
import java.util.Map;

public class Controller {
    private List<User> users;
    private List<BankAccount> bankAccounts;
    private Connector conn;
    private ObjectToJson objectToJson;

    public Controller() {

    }

    private boolean setupFor(FileNames tClass){
        switch (tClass){
            case FileNames.USER:
                this.conn = new Connector(FileNames.USER.name());
                this.objectToJson = new ObjectToJson<User>();
                return true;
            case FileNames.BANKACCOUNT:
                this.conn = new Connector(FileNames.BANKACCOUNT.name());
                this.objectToJson = new ObjectToJson<BankAccount>();
                return true;
            default:
                return false;
        }
    }

    public boolean addBankAccount(BankAccount bankAccount){
        if(this.bankAccounts.add(bankAccount)) return true;
        return false;
    }

    public boolean saveBankAccounts(){
        if(this.setupFor(FileNames.BANKACCOUNT)) {
            if (conn.saveJson(objectToJson.serialize(this.bankAccounts))) return true;
        }
        return false;
    }

    private boolean readBankAccounts(){
        if(this.setupFor(FileNames.BANKACCOUNT)){
            this.bankAccounts = this.objectToJson.deserialize(this.conn.read(), BankAccount.class);
            if(!this.bankAccounts.isEmpty()) return true;
        }
        return false;
    }


}
