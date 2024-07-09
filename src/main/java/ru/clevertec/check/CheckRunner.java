package ru.clevertec.check;

import ru.clevertec.check.service.CashRegister;
import ru.clevertec.check.service.CashRegisterTask2;

public class CheckRunner {

    public static void main(String[] args) {
        CashRegister cashRegister = new CashRegisterTask2();
        cashRegister.printReceipt(args);
    }
}
