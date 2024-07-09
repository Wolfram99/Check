package ru.clevertec.check.service;

import ru.clevertec.check.dao.DaoDQL;
import ru.clevertec.check.dao.DaoDQLDiscountCard;
import ru.clevertec.check.dao.DiscountCardDaoFromCSV;
import ru.clevertec.check.dao.ProductDaoFromCSV;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.models.DiscountCard;
import ru.clevertec.check.models.Product;
import ru.clevertec.check.service.builder.Director;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CashRegister {
    private DaoDQL<Product> daoProduct;
    private DaoDQLDiscountCard daoDiscountCard;

    public CashRegister(DaoDQL<Product> daoProduct, DaoDQLDiscountCard daoDiscountCard) {
        this.daoProduct = daoProduct;
        this.daoDiscountCard = daoDiscountCard;
    }

    public CashRegister() {
        daoProduct = new ProductDaoFromCSV();
        daoDiscountCard = new DiscountCardDaoFromCSV();
    }

    public void printReceipt(String[] args) {
        Map<Product, Integer> shoppingCart = new HashMap<>();
        DiscountCard discountCard = new DiscountCard();
        BigDecimal balanceDebitCard = null;

        // проверка на прсутствие всех параметров
        if (!checkingInputData(args)) {
            try {
                throw new BadRequestException();
            } catch (BadRequestException e) {
                Director.createErrorReceipt(e.getMessage()).receiptFormation();
                System.exit(0);
            }
        }

        // Блок try для выкидывания exception при парсинге данных
        // если нельзя привести к необходимому типу
        try {
            for (String str : args) {

                if (str.contains("discountCard")) {
                    discountCard = daoDiscountCard.findByNumber(Integer.parseInt(searchDataAfterPrefix(str)));
                    continue;
                }
                if (str.contains("balanceDebitCard")) {
                    // Определение баланса
                    balanceDebitCard = new BigDecimal(searchDataAfterPrefix(str));
                    continue;
                }

                // Отальные параметры относятся к отношению id-quantity
                // сначало разобрать текущий переметр
                String[] param = str.split("-");
                // Далее собрать объект Product
                Product pr = daoProduct.findById(Long.parseLong(param[0]));

                // Поместить Product и его колическтво в MAP
                if (!shoppingCart.containsKey(pr)) {
                    shoppingCart.put(pr, Integer.parseInt(param[1]));
                } else { // Если такой ужк ключ есть, необходимо добавить количество к уже сущуствующему

                    shoppingCart.put(pr, shoppingCart.get(pr) + Integer.parseInt(param[1]));
                }
            }

        } catch (NumberFormatException nfe) {
            try {
                throw new BadRequestException();
            } catch (BadRequestException e) {
                Director.createErrorReceipt(e.getMessage()).receiptFormation();

                System.exit(0);
            }
        }

        Director.createSuccessReceiptNotPathToFile(null,shoppingCart,discountCard, balanceDebitCard).receiptFormation();

    }

    public String searchDataAfterPrefix(String str) {
        return str.split("=")[1];
    }


    public Boolean checkingInputData(String[] args) {

        boolean flagPresencesBalanceDebitCard = false;

        for (String param : args) {
            if (param.contains("balanceDebitCard")) {
                flagPresencesBalanceDebitCard = true;
            }
        }


        return flagPresencesBalanceDebitCard;
    }

}

