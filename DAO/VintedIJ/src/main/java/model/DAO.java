package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<E,I> {
    public int add(E entity) throws SQLException;
    public int delete(Integer e);
    public int update(E entity) throws SQLException;
    public ArrayList<E> findAll(E entity) throws SQLException;
}