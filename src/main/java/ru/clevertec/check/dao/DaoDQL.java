package ru.clevertec.check.dao;

public interface DaoDQL<E> {

    E findById(Long id);
}
