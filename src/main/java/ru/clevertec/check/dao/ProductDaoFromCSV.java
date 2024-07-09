package ru.clevertec.check.dao;

import ru.clevertec.check.dao.DaoDQL;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.models.Product;
import ru.clevertec.check.service.builder.Director;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ProductDaoFromCSV implements DaoDQL<Product> {
    private final String DEFAULT_PATH_TO_FILE = "./src/main/resources/products.csv";
    private final String VALUE_SEPARATOR = ";";
    private String pathToFile;

    public ProductDaoFromCSV(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public ProductDaoFromCSV() {
    }

    @Override
    public Product findById(Long id) {
        Product product = null;

        try {
            /*
             избежание копипаста можно вынести в отдеольный кдасс\метод
             и передавать туда 3 параметра (id, pathToFile, separator)
             такоже код наодится в DiscountCardDaoFromCSV для DiscountCard
             Но так как метод поиска нужного объекта отличается (id, number)
             не стал выносить в отдельный класс
             */
            var list = Files.lines(Paths.get(pathToFile == null ? DEFAULT_PATH_TO_FILE : pathToFile)) //получаем стрим строк из файла
                    .filter(s -> s.startsWith(String.valueOf(id)))
                    .flatMap(s -> Stream.of(s.split(VALUE_SEPARATOR)))
                    .toList();
            if (!list.isEmpty()) {
                product = Director.createProduct(list);
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

        return product;
    }

}
