package users;

import channelCategories.Category;
import repository.Repository;

import java.util.Collection;

public interface User<T> {
    int getId();
    String getName();
    String getPassword();
    Collection<T> getContracts();

    Collection<Category> getCategories();
}
