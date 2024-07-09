package ru.clevertec.check.service.FormationResult;



import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.models.DiscountCard;
import ru.clevertec.check.models.Product;
import ru.clevertec.check.service.builder.Director;
import ru.clevertec.check.service.print.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class SuccessReceipt extends Receipt {
    private final Logger logger;
    private Map<Product, Integer> shoppingCart;
    private DiscountCard discountCard;
    private BigDecimal balanceDebitCard;

    private final String HEADER_DATE_TIME = "Date;Time";
    private final String HEADER_LIST_OF_PRODUCT = "QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL";
    private final String HEADER_DISCOUNT = "DISCOUNT CARD;DISCOUNT PERCENTAGE";
    private final String HEADER_AMOUNT = "TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT";
    private final String SEPARATOR = ";";
    private final Character CURRENCY = 36;

    @Override
    public void receiptFormation() {

        StringBuilder sb = new StringBuilder();
        BigDecimal totalAll = new BigDecimal("0");
        BigDecimal discountAll = new BigDecimal("0");

        sb.append(HEADER_DATE_TIME + "\n");
        sb.append(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                + SEPARATOR + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
        sb.append("\n");
        sb.append(HEADER_LIST_OF_PRODUCT + "\n");

        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            BigDecimal totalProduct = entry.getKey().getPrice().
                    multiply(BigDecimal.valueOf(entry.getValue())).setScale(2);
            BigDecimal discountProduct = providingDiscount(discountCard,
                    entry.getKey(),
                    entry.getValue())
                    .multiply(totalProduct)
                    .divide(BigDecimal.valueOf(100), 2);

            sb.append(entry.getValue() + SEPARATOR +
                    entry.getKey().getDescription() + SEPARATOR +
                    entry.getKey().getPrice() + CURRENCY + SEPARATOR +
                    discountProduct + CURRENCY + SEPARATOR +
                    totalProduct + CURRENCY + "\n");
            totalAll = totalAll.add(totalProduct);
            discountAll = discountAll.add(discountProduct);
        }
        sb.append("\n");
        sb.append(HEADER_DISCOUNT + "\n");
        sb.append((discountCard.isNull() ? "NOT FOUND" : discountCard.getNumber()) + SEPARATOR +
                (discountCard.isNull() ? 0 : discountCard.getAmount()) + "%" + "\n"); // сюда ввод информации о дисконтной карте
        sb.append("\n");
        sb.append(HEADER_AMOUNT + "\n");
        BigDecimal totalWithDiscount = totalAll.subtract(discountAll);

        try {
            insufficientFunds(balanceDebitCard, totalWithDiscount);
        } catch (NotEnoughMoneyException e) {
            Director.createErrorReceipt(e.getMessage()).receiptFormation();

            System.exit(0);
        }

        sb.append(totalAll.toString() + CURRENCY + SEPARATOR +
                discountAll + CURRENCY + SEPARATOR +
                totalWithDiscount + CURRENCY + "\n"); // сюда ввод информации общей сумме
        logger.write(sb.toString());
    }


    public BigDecimal providingDiscount(DiscountCard discountCard, Product product, Integer quantityProduct) {
        BigDecimal discount;

        if (product.getWholesaleProduct() && quantityProduct >= 5) {
            discount = new BigDecimal("10");
            return discount;
        }
        if (!discountCard.isNull()) {
            discount = BigDecimal.valueOf(discountCard.getAmount());
            return discount;
        }

        return new BigDecimal("0");
    }

    public void insufficientFunds(BigDecimal balanceDebitCard, BigDecimal totalWithDiscount) throws NotEnoughMoneyException {
        if (balanceDebitCard.compareTo(totalWithDiscount) < 0) {
            throw new NotEnoughMoneyException();
        }
    }

    public SuccessReceipt(Logger logger,Map<Product, Integer> shoppingCart, DiscountCard discountCard, BigDecimal balanceDebitCard) {
        this.logger = logger;
        this.shoppingCart = shoppingCart;
        this.discountCard = discountCard;
        this.balanceDebitCard = balanceDebitCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuccessReceipt that = (SuccessReceipt) o;
        return shoppingCart.equals(that.shoppingCart) && discountCard.equals(that.discountCard) && balanceDebitCard.equals(that.balanceDebitCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCart, discountCard, balanceDebitCard);
    }

    @Override
    public String toString() {
        return "SuccessReceipt{" +
                "shoppingCart=" + shoppingCart +
                ", discountCard=" + discountCard +
                ", balanceDebitCard=" + balanceDebitCard +
                "} " + super.toString();
    }

    public static SuccessReceiptBuilder builder() {
        return new SuccessReceiptBuilder();
    }

    public static class SuccessReceiptBuilder {
        private Logger logger;
        private Map<Product, Integer> shoppingCart;
        private DiscountCard discountCard;
        private BigDecimal balanceDebitCard;

        public SuccessReceiptBuilder logger(Logger logger){
            this.logger = logger;
            return this;
        }

        public SuccessReceiptBuilder shoppingCart(Map<Product, Integer> shoppingCart) {
            this.shoppingCart = shoppingCart;
            return this;
        }

        public SuccessReceiptBuilder discountCard(DiscountCard discountCard) {
            this.discountCard = discountCard;
            return this;
        }

        public SuccessReceiptBuilder balanceDebitCard(BigDecimal balanceDebitCard) {
            this.balanceDebitCard = balanceDebitCard;
            return this;
        }

        public SuccessReceipt build() {
            return new SuccessReceipt(logger,shoppingCart, discountCard, balanceDebitCard);
        }


    }

}
