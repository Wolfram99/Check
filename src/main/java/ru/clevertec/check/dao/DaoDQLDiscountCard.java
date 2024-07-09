package main.java.ru.clevertec.check.dao;

import main.java.ru.clevertec.check.models.DiscountCard;

public interface DaoDQLDiscountCard{

     DiscountCard findByNumber(Integer number);
}
