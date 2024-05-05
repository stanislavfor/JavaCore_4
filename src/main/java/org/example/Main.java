package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account accountMain = null;
        AccountDebit accountDebit = null;
        AccountCredit accountCredit = null;

        boolean initialBalanceValid = false;
        while (!initialBalanceValid) {
            try {
                System.out.print("Введите начальный баланс основного счета : ");
                double initialBalance = scanner.nextDouble();
                accountMain = new Account(initialBalance);
                initialBalanceValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка : " + e.getMessage());
            }
        }

        boolean debitBalanceValid = false;
        while (!debitBalanceValid) {
            try {
                System.out.print("Введите начальный баланс дебетового счета : ");
                double debitBalance = scanner.nextDouble();
                accountDebit = new AccountDebit(debitBalance);
                debitBalanceValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка : " + e.getMessage());
            }
        }

        boolean creditBalanceValid = false;
        while (!creditBalanceValid) {
            try {
                System.out.print("Введите баланс кредитного счета : ");
                double creditBalance = scanner.nextDouble();
                accountCredit = new AccountCredit(creditBalance);
                creditBalanceValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка : " + e.getMessage());
            }
        }

        while (true) {
            System.out.print("Введите команду (Пополнить - 1, Перевести - 2, exit - 0) : ");
            String command = scanner.next();
            if ("0".equalsIgnoreCase(command)) {
                break;
            }

            try {
                switch (command.toLowerCase()) {
                    case "1":
                        System.out.print("Введите сумму депозита : ");
                        double depositAmount = scanner.nextDouble();
                        accountMain.deposit(depositAmount);
                        System.out.println("Новый баланс после внесения депозита : " + accountMain.getBalance());
                        break;
                    case "2":
                        boolean transactionCompleted = false;
                        while (!transactionCompleted) {
                            try {
                                System.out.print("Введите сумму перевода: ");
                                double transferAmount = scanner.nextDouble();
                                System.out.print("Выберите счёт для перевода (дебетовый - 1; кредитный - 2): ");
                                int accountChoice = scanner.nextInt();
                                if (accountChoice == 1) {
                                    Transaction.transfer(accountMain, accountDebit, transferAmount);
                                    System.out.println("Транзакция выполнена.");
                                    System.out.println("Баланс дебетового счета: " + accountDebit.getBalance());
                                    transactionCompleted = true;
                                } else if (accountChoice == 2) {
                                    Transaction.transfer(accountMain, accountCredit, transferAmount);
                                    System.out.println("Транзакция выполнена.");
                                    System.out.println("Баланс кредитного счета: " + accountCredit.getBalance());
                                    transactionCompleted = true;
                                } else {
                                    System.out.println("Неверный ввод. Повторите попытку");
                                }
                            } catch (ExceptionOfTransaction e) {
                                System.out.println("Ошибка: " + e.getMessage());
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("Неверный формат ввода.");
                                scanner.nextLine();
                            }
                        }

                        System.out.println("Баланс основного счета после перевода : " + accountMain.getBalance());
                        break;
                    default:
                        System.out.println("Неизвестная команда. Повторите попытку.");
                }
            } catch (IllegalArgumentException | ExceptionOfInsufficientFunds e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Выход из программы.");
        System.out.println("Окончательный баланс основного счёта : " + accountMain.getBalance());
        System.out.println("Окончательный баланс дебетового счёта : " + accountDebit.getBalance());
        System.out.println("Окончательный баланс кредитного счёта : " + accountCredit.getBalance());
    }

}
