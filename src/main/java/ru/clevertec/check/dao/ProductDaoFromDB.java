package ru.clevertec.check.dao;

import ru.clevertec.check.models.Product;

import java.sql.Connection;

public class ProductDaoFromDB extends DaoDB implements DaoDQL<Product> {

    public ProductDaoFromDB(Connection connection) {
        super(connection);
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        return product;
    }
}
