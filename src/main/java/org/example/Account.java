package org.example;

// Класс основного счета
public class Account {
    protected double balance;

    public Account(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным.");
        }
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма депозита не может быть отрицательной.");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws ExceptionOfInsufficientFunds {
        if (amount > balance) {
            throw new ExceptionOfInsufficientFunds("Недостаточно средств. Текущий баланс: " + balance);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

}

