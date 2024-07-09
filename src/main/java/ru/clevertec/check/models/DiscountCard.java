package main.java.ru.clevertec.check.models;

import java.util.Objects;

public class DiscountCard {
    private Long id;
    private Integer number;
    private Short amount;

    public DiscountCard(Long id, Integer number, Short amount) {
        this.id = id;
        this.number = number;
        this.amount = amount;
    }

    public DiscountCard() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Short getAmount() {
        return amount;
    }

    public void setAmount(Short amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return id.equals(that.id) && number.equals(that.number) && amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, amount);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", number=" + number +
                ", amount=" + amount +
                '}';
    }

    public static DiscountCardBuilder builder() {
        return new DiscountCardBuilder();
    }

    public static class DiscountCardBuilder {
        private Long id;
        private Integer number;
        private Short amount;

        public DiscountCardBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DiscountCardBuilder number(Integer number) {
            this.number = number;
            return this;
        }

        public DiscountCardBuilder amount(Short amount) {
            this.amount = amount;
            return this;
        }

        public DiscountCard build() {
            return new DiscountCard(id, number, amount);
        }
    }

    public boolean isNull() {
        if (this.id == null && this.number == null & this.amount == null) {
            return true;
        }

        return false;

    }
}
