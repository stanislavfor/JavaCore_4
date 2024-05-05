package org.example;

// Класс исключения для ситуаций, когда средств на счете недостаточно
class ExceptionOfInsufficientFunds extends Exception {
    public ExceptionOfInsufficientFunds(String message) {

        super(message);

    }
}