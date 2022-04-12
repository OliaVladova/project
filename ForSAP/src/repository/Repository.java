package repository;

import java.util.Collection;

public interface Repository<T> {
    void add(T element);

    boolean remove(T element);
    T getByName(String name);


    Collection<T> getEntities();
}
