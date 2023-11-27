package Model;

import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.List;


public class Account {
    private String NumberOfAccount;
    private String File;
    private String NumberOfAccountTransfer;
    private int AmountOfMoney;
    private int AmountTransfer;
    private String Date;
    private List<StringBuilder> Marks = new ArrayList<>();

    public Account(String numberOfAccount, int amountOfMoney, int amountTransfer, String date, List<StringBuilder> marks, String numberOfAccountTransfer, String file ) {
        NumberOfAccount = numberOfAccount;
        AmountOfMoney = amountOfMoney;
        AmountTransfer = amountTransfer;
        Date = date;
        Marks = marks;
        NumberOfAccountTransfer = numberOfAccountTransfer;
        File = file;
    }

    @Override
    public String toString() {
        return "Account{" +
                "NumberOfAccount='" + NumberOfAccount + '\'' +
                ", File='" + File + '\'' +
                ", NumberOfAccountTransfer='" + NumberOfAccountTransfer + '\'' +
                ", AmountOfMoney=" + AmountOfMoney +
                ", AmountTransfer=" + AmountTransfer +
                ", Date='" + Date + '\'' +
                ", Marks=" + Marks +
                '}';
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }

    public String getNumberOfAccountTransfer() {
        return NumberOfAccountTransfer;
    }

    public void setNumberOfAccountTransfer(String numberOfAccountTransfer) {
        NumberOfAccountTransfer = numberOfAccountTransfer;
    }

    public Account() {
    }

    public List<StringBuilder> getMarks() {
        return Marks;
    }

    public void setMarks(List<StringBuilder> marks) {
        Marks = marks;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNumberOfAccount() {
        return NumberOfAccount;
    }

    public void setNumberOfAccount(String numberOfAccount) {
        NumberOfAccount = numberOfAccount;
    }

    public int getAmountOfMoney() {
        return AmountOfMoney;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        AmountOfMoney = amountOfMoney;
    }

    public int getAmountTransfer() {
        return AmountTransfer;
    }

    public void setAmountTransfer(int amountTransfer) {
        AmountTransfer = amountTransfer;
    }
}
