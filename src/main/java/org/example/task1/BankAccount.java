package org.example.task1;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    public String name;
    public int balance=0;
    LocalDateTime open_date=LocalDateTime.now();
    boolean status_block=false;
    String number;

public BankAccount(String name){
    this.name=name;
    this.number = generateNumber();
}
public boolean deposit(int amount){
    if (amount > 0) {
        balance+=amount;
        return true;
    }
    return false;
}
public boolean withdraw(int amount)
{
    if(balance>=amount&&amount>0) {
        balance-=amount;
        return true;
    }
    return false;
}
public boolean transfer(BankAccount otherAccount, int amount)
{
    if(balance>=amount&&amount>0){
        balance-=amount;
        otherAccount.balance+=amount;
        return true;
    }
    return false;
}
    @Override
    public String toString() {
        return "Информация о счёте: {" +
                "владелец: '" + name+ '\'' +
                ", баланс: " + balance +
                ", дата открытия счета: " + open_date + '\'' +
                ", статус блокировки счета: " + status_block + '\'' +
                '}';
    }
    private String generateNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            accountNumber.append(random.nextInt(10));
        }

        return accountNumber.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount other_account = (BankAccount) o;
        return this.balance == other_account.balance &&
                this.status_block == other_account.status_block &&
                Objects.equals(name, other_account.name) &&
                Objects.equals(open_date, other_account.open_date) &&
                Objects.equals(number, other_account.number);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, balance, open_date,status_block, number);
    }
}
