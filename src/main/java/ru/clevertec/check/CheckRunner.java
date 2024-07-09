package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.service.CashRegister;
import main.java.ru.clevertec.check.service.CashRegisterTask2;

public class CheckRunner {

    public static void main(String[] args) {
        CashRegister cashRegister = new CashRegisterTask2();
        cashRegister.printReceipt(args);
    }
}
