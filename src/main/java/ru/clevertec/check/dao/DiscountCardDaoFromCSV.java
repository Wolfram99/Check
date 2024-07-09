package ru.clevertec.check.dao;

import ru.clevertec.check.dao.DaoDQL;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.models.DiscountCard;
import ru.clevertec.check.service.builder.Director;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

public class DiscountCardDaoFromCSV implements DaoDQL<DiscountCard>, DaoDQLDiscountCard {
    private final String PATH_TO_FILE_DISCOUNT_CARD = "./src/main/resources/discountCards.csv";
    private final String VALUE_SEPARATOR = ";";

    @Override
    public DiscountCard findById(Long id) {
        DiscountCard discountCard = null;

        try {
            var list = Files.lines(Paths.get(PATH_TO_FILE_DISCOUNT_CARD))
                    .filter(s -> s.startsWith(String.valueOf(id)))
                    .flatMap(s -> Stream.of(s.split(VALUE_SEPARATOR)))
                    .toList();

            if (!list.isEmpty()) {
                discountCard = Director.createDiscountCard(list);
            } else {
                throw new BadRequestException();
            }
        } catch (BadRequestException e) {
            Director.createErrorReceipt(e.getMessage()).receiptFormation();

            System.exit(0);
        } catch (IOException e) {
            try {
                throw new InternalServerException();
            } catch (InternalServerException ex) {
                Director.createErrorReceipt(ex.getMessage()).receiptFormation();

                System.exit(0);
            }
        }
        return discountCard;
    }


    public DiscountCard findByNumber(Integer number) {
        DiscountCard discountCard = null;

        try {
            var list = Files.lines(Paths.get(PATH_TO_FILE_DISCOUNT_CARD)) //получаем стрим строк из файла
                    .filter(s -> s.substring(s.indexOf(";") + 1).startsWith(String.valueOf(number)))
                    .flatMap(s -> Stream.of(s.split(VALUE_SEPARATOR)))
                    .toList();

            if (!list.isEmpty()) {
                discountCard = Director.createDiscountCard(list);
            } else {
                throw new BadRequestException();
            }
        } catch (BadRequestException e) {
            Director.createErrorReceipt(e.getMessage()).receiptFormation();

            System.exit(0);
        } catch (IOException e) {
            try {
                throw new InternalServerException();
            } catch (InternalServerException ex) {
                Director.createErrorReceipt(ex.getMessage()).receiptFormation();

                System.exit(0);
            }
        }

        return discountCard;
    }
}
