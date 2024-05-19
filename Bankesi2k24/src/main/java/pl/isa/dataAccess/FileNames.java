package pl.isa.dataAccess;

public enum FileNames {
    USER("users.txt"),
    BANKACCOUNT("bank_accounts.txt");


    FileNames(String fileName) {
        this.name = fileName;
    };

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
