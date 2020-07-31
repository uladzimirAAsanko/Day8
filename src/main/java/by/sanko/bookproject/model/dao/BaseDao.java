package by.sanko.bookproject.model.dao;

import by.sanko.bookproject.exception.DAOException;

public interface BaseDao <T, E> {
    boolean create(T item)throws DAOException;
    T read(E tag) throws DAOException;
    boolean update(T item) throws DAOException;
    boolean delete(T item) throws DAOException;
}
