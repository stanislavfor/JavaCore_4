package org.example;

// Класс исключения для транзакций
class ExceptionOfTransaction extends Exception {
    public ExceptionOfTransaction(String message) {

        super(message);

    }
}