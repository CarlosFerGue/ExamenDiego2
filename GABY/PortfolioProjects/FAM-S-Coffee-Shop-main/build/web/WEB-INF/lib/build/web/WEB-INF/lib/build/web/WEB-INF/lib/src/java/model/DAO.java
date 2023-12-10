

package model;

import java.util.ArrayList;

public interface DAO<E,I> {
    public int add(E entity);
    public int delete(Integer e);
    public int update(E entity);
    public ArrayList<E> findAll(E entity);
}
