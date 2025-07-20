package org.example.task1;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        BankAccount testAccount=new BankAccount("Maria");
        BankAccount testAccountSecond=new BankAccount("Kate");

        System.out.println(testAccount.toString());

        testAccount.deposit(100);
        System.out.println("Баланс "+testAccount.name+" после пополнения:"+testAccount.balance);

        if(!testAccount.withdraw(200)){
            System.out.println("Недостаточно средств");
        }
        testAccount.withdraw(20);
        System.out.println("Баланс "+testAccount.name+" после снятия средств:"+testAccount.balance);

        if(!testAccount.transfer(testAccountSecond,1000)){
            System.out.println("Недостаточно средств");
        }
        testAccount.transfer(testAccountSecond,10);
        System.out.println("Баланс "+testAccount.name+" после перевода средств:"+testAccount.balance);
        System.out.println("Баланс "+testAccountSecond.name+" после перевода средств:"+testAccountSecond.balance);

        BankAccount testAccountThird=new BankAccount("Maria");//отличия в номере счета и времени создания
        if(!testAccount.equals(testAccountThird)){
            System.out.println("Это разные аккаунты");
        }
        System.out.println(testAccount.hashCode());
        System.out.println(testAccountSecond.hashCode());

    }
}