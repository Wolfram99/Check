package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.dao.DaoDQL;
import main.java.ru.clevertec.check.dao.DaoDQLDiscountCard;
import main.java.ru.clevertec.check.dao.DiscountCardDaoFromCSV;
import main.java.ru.clevertec.check.dao.ProductDaoFromCSV;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.models.Product;
import main.java.ru.clevertec.check.service.builder.Director;

import java.util.Arrays;

public class CashRegisterTask2 extends CashRegister {

    public CashRegisterTask2(DaoDQL<Product> daoProduct, DaoDQLDiscountCard daoDiscountCard) {
        super(daoProduct, daoDiscountCard);
    }

    public CashRegisterTask2() {
    }

    @Override
    public void printReceipt(String[] args) {
        DaoDQL<Product> daoProduct = null;
        DaoDQLDiscountCard daoDiscountCard = new DiscountCardDaoFromCSV();
        String saveToFile = null;

        Boolean flagPathToFile = false;
        Boolean flagSaveToFile = false;

        for (String param : args) {
            if (param.contains("pathToFile")) {

                daoProduct = new ProductDaoFromCSV(searchDataAfterPrefix(param));
                flagPathToFile = true;
            }
            if (param.contains("saveToFile")) {
                saveToFile = searchDataAfterPrefix(param);
                flagSaveToFile = true;
            }
        }

        if (!flagPathToFile || !flagSaveToFile) {
            try {
                throw new BadRequestException();
            } catch (BadRequestException e) {
                Director.createErrorReceipt(e.getMessage(), saveToFile).receiptFormation();

                System.exit(0);
            }
        }

        CashRegister cashRegister = new CashRegister(daoProduct, daoDiscountCard);

        cashRegister.printReceipt(Arrays.stream(args).filter(e -> !e.contains("pathToFile")).filter(e -> !e.contains("saveToFile")).toArray(String[]::new));
    }

    @Override
    public String searchDataAfterPrefix(String str) {
        return super.searchDataAfterPrefix(str);
    }

    @Override
    public Boolean checkingInputData(String[] args) {
        return super.checkingInputData(args);
    }
}
