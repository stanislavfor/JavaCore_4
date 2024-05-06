package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account accountMain = null;
        AccountDebit accountDebit = null;
        AccountCredit accountCredit = null;
        String accountName1;
        String accountName2;

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
            System.out.print("Введите команду (1 - Пополнить, 2 - Перевести, 3 - Баланс, 0 - exit) : ");
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
                        System.out.println("Баланс основного счёта после внесения депозита : " + accountMain.getBalance());
                        break;
                    case "2":
                        boolean transactionCompleted = false;
//                        while (!transactionCompleted) {
//                            try {
//                                System.out.print("Введите сумму перевода : ");
//                                double transferAmount = scanner.nextDouble();
//                                System.out.print("Выберите счёт для перевода (дебетовый - 1; кредитный - 2) : ");
//                                int accountChoice = scanner.nextInt();
//                                if (accountChoice == 1) {
//                                    Transaction.transfer(accountMain, accountDebit, transferAmount);
//                                    System.out.println("Транзакция выполнена.");
//                                    System.out.println("Баланс дебетового счета: " + accountDebit.getBalance());
//                                    transactionCompleted = true;
//                                } else if (accountChoice == 2) {
//                                    Transaction.transfer(accountMain, accountCredit, transferAmount);
//                                    System.out.println("Транзакция выполнена.");
//                                    System.out.println("Баланс кредитного счета : " + accountCredit.getBalance());
//                                    transactionCompleted = true;
//                                } else {
//                                    System.out.println("Неверный ввод. Повторите попытку");
//                                }
//                            } catch (ExceptionOfTransaction e) {
//                                System.out.println("Ошибка: " + e.getMessage());
//                            } catch (java.util.InputMismatchException e) {
//                                System.out.println("Неверный формат ввода.");
//                                scanner.nextLine();
//                            }
//                        }
                        while (!transactionCompleted) {
                        try {
                            System.out.print("Введите сумму перевода : ");
                            double transferAmount = scanner.nextDouble();
                            System.out.print("Выберите счёт для пополнения (0 - основной, 1 - дебетовый, 2 - кредитный) : ");
                            int toAccountChoice = scanner.nextInt();
                            Account toAccount = null;
                            switch (toAccountChoice) {
                                case 0:
                                    toAccount = accountMain;
                                    accountName2 = "основного ";
                                    break;
                                case 1:
                                    toAccount = accountDebit;
                                    accountName2 = "дебетового ";
                                    break;
                                case 2:
                                    toAccount = accountCredit;
                                    accountName2 = "кредитного ";
                                    break;
                                default:
                                    System.out.println("Неверная команда. Повторите ввод.");
                                    continue;
                            }
                            System.out.print("Выберите счёт, с которого переводить (0 - основной, 1 - дебетовый, 2 - кредитный) : ");
                            int fromAccountChoice = scanner.nextInt();
                            Account fromAccount = null;
                            switch (fromAccountChoice) {
                                case 0:
                                    fromAccount = accountMain;
                                    accountName1 = "основного ";
                                    break;
                                case 1:
                                    fromAccount = accountDebit;
                                    accountName1 = "дебетового ";
                                    break;
                                case 2:
                                    fromAccount = accountCredit;
                                    accountName1 = "кредитного ";
                                    break;
                                default:
                                    System.out.println("Невернная команда. Повторите ввод.");
                                    continue;
                            }

                            if (fromAccount == toAccount) {
                                System.out.println("Перевод средства на тот же счет не возможен.");
                                continue;
                            }
                            Transaction.transfer(fromAccount, toAccount, transferAmount);
                            System.out.println("Транзакция выполнена.");
                            System.out.println("Остаток на балансе " + accountName1 + "счета : " + fromAccount.getBalance());
                            System.out.println("Баланс " + accountName2 + "счета после пополнения : " + toAccount.getBalance());
                            transactionCompleted = true;
                        } catch (ExceptionOfTransaction e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Неверный формат ввода.");
                            scanner.nextLine();
                        }
                }

//                        System.out.println("Баланс основного счета после перевода : " + accountMain.getBalance());
                        break;
                    case "3":
                        System.out.println("Баланс основного счёта : " + accountMain.getBalance());
                        System.out.println("Баланс дебетового счёта : " + accountDebit.getBalance());
                        System.out.println("Баланс кредитного счёта : " + accountCredit.getBalance());
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
