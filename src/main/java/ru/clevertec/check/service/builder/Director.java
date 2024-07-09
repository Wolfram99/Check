package ru.clevertec.check.service.builder;

import ru.clevertec.check.models.DiscountCard;
import ru.clevertec.check.models.Product;
import ru.clevertec.check.service.FormationResult.ErrorReceipt;
import ru.clevertec.check.service.FormationResult.SuccessReceipt;
import ru.clevertec.check.service.dataConvertor.FormatConversion;
import ru.clevertec.check.service.print.WriteToFile;
import ru.clevertec.check.service.print.WriteToFileAndConsole;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Director {

    public static Product createProduct(List<String> list){
        return Product.builder()
                .id(Long.parseLong(list.get(0)))
                .description(list.get(1))
                .price(FormatConversion.conversionToBigDecimal(list.get(2)))
                .quantityInStock(Integer.parseInt(list.get(3)))
                .wholesaleProduct(list.get(4).equals("+"))
                .build();
    }

    public static DiscountCard createDiscountCard(List<String> list){
        return DiscountCard.builder()
                .id(Long.parseLong(list.get(0)))
                .number(Integer.parseInt(list.get(1)))
                .amount(Short.parseShort(list.get(2)))
                .build();

    }
    public static ErrorReceipt createErrorReceipt(String message, String path){
        return ErrorReceipt.builder()
                .logger(new WriteToFileAndConsole(new WriteToFile(path)))
                .message(message)
                .build();
    }

    public static ErrorReceipt createErrorReceipt(String message){
        return ErrorReceipt.builder()
                .logger(new WriteToFileAndConsole(new WriteToFile()))
                .message(message)
                .build();
    }

    public static SuccessReceipt createSuccessReceiptNotPathToFile(String pathToFile, Map<Product, Integer> shoppingCart, DiscountCard discountCard, BigDecimal balanceDebitCard){
        return SuccessReceipt.builder()
                .logger(new WriteToFileAndConsole(new WriteToFile(pathToFile)))
                .shoppingCart(shoppingCart)
                .discountCard(discountCard)
                .balanceDebitCard(balanceDebitCard)
                .build();
    }

}
