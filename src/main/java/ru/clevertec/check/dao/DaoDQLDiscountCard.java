package ru.clevertec.check.dao;


import ru.clevertec.check.models.DiscountCard;

public interface DaoDQLDiscountCard{

     DiscountCard findByNumber(Integer number);
}
