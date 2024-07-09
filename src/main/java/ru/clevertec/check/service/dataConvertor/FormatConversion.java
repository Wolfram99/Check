package main.java.ru.clevertec.check.service.dataConvertor;

import main.java.ru.clevertec.check.exception.InternalServerException;
import main.java.ru.clevertec.check.service.builder.Director;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class FormatConversion {

    /*
     *    Класс что бы преобразовывать строковый тип в формате ##,## в BigDecimal в формат ##.##
     */

    public static BigDecimal conversionToBigDecimal(String str) {
        DecimalFormat format = new DecimalFormat("#.##");
        format.setParseBigDecimal(true);
        BigDecimal number = null;
        try {
            number = (BigDecimal) format.parse(str);
        } catch (ParseException e) {
            try {
                throw new InternalServerException();
            } catch (InternalServerException ex) {
                Director.createErrorReceipt(e.getMessage()).receiptFormation();

                System.exit(0);
            }
        }
        return number;
    }

}
