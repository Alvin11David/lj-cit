package org.example;
import java.util.List;


public interface CRUDRepository<T,ID> {
    void save(T entity);
    T findById(ID id);
    List<T> loadAll();
}
