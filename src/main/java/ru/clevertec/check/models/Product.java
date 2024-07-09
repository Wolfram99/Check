package main.java.ru.clevertec.check.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long id;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private Boolean wholesaleProduct;


    public Product(Long id, String description, BigDecimal price, Integer quantityInStock, Boolean wholesaleProduct) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.wholesaleProduct = wholesaleProduct;
    }

    public Product() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Boolean getWholesaleProduct() {
        return wholesaleProduct;
    }

    public void setWholesaleProduct(Boolean wholesaleProduct) {
        this.wholesaleProduct = wholesaleProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && description.equals(product.description) && price.equals(product.price) && quantityInStock.equals(product.quantityInStock) && wholesaleProduct.equals(product.wholesaleProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, quantityInStock, wholesaleProduct);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", wholesaleProduct=" + wholesaleProduct +
                '}';
    }


    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private Long id;
        private String description;
        private BigDecimal price;
        private Integer quantityInStock;
        private Boolean wholesaleProduct;

        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder quantityInStock(Integer quantityInStock) {
            this.quantityInStock = quantityInStock;
            return this;
        }

        public ProductBuilder wholesaleProduct(Boolean wholesaleProduct) {
            this.wholesaleProduct = wholesaleProduct;
            return this;
        }

        public Product build() {
            return new Product(id, description, price, quantityInStock, wholesaleProduct);
        }
    }


}
