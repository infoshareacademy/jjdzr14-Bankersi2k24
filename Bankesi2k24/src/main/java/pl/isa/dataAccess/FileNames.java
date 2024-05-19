package pl.isa.dataAccess;

public enum FileNames {
    USER("users.txt"),
    BANKACCOUNT("bank_accounts.txt");


    FileNames(String fileName) {
        this.fileName = fileName;
    };

    private String fileName;

}
