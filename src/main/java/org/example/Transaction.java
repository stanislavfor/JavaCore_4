package org.example;

class Transaction {
    public static void transfer(Account from, Account to, double amount) throws ExceptionOfTransaction, ExceptionOfInsufficientFunds {
        if (amount <= 0) {
            throw new ExceptionOfTransaction("Сумма перевода должна быть положительной.");
        }
        if (from.getBalance() < amount) {
            throw new ExceptionOfTransaction("Недостаточно средств для перевода.");
        }
        from.withdraw(amount);
        to.deposit(amount);
    }
}

