package ru.clevertec.check.service;

import ru.clevertec.check.dao.DaoDQL;
import ru.clevertec.check.dao.DaoDQLDiscountCard;
import ru.clevertec.check.dao.DiscountCardDaoFromCSV;
import ru.clevertec.check.dao.ProductDaoFromCSV;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.models.Product;
import ru.clevertec.check.service.builder.Director;


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
