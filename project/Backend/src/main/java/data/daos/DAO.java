package data.daos;


import java.util.List;

public interface DAO<K, E> {

    void flush();

    void persist(E entity);

    void merge(E entity);

    void remove(E entity);

    E findById(K id);

    List<E> findAll();

    public void rollBack();

    E loadEntity(String condition);

    E loadEntity(String condition, String orderBy);

    void removeEntity(String condition);

    int estimatedSize();
}
