package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.dao.DaoDQLDiscountCard;
import main.java.ru.clevertec.check.dao.DiscountCardDaoFromCSV;
import main.java.ru.clevertec.check.dao.ProductDaoFromCSV;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.service.CashRegister;
import main.java.ru.clevertec.check.service.CashRegisterTask2;


public class CheckRunner {

    public static void main(String[] args) {
        CashRegister cashRegister = new CashRegister();
        cashRegister.printReceipt(args);

    }
}
