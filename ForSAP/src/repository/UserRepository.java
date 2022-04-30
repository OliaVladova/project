package repository;

import users.UserOfTV;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, UserOfTV> users;

    public UserRepository() {
        this.users = new LinkedHashMap<>();
    }

    public void add(UserOfTV element) {
            this.users.put(element.getName(),element);
    }

    public boolean remove(UserOfTV element) {
        return this.users.remove(element.getName())!=null;
    }

    public UserOfTV getByName(String name) {
        return this.users.get(name);
    }

    public Collection<UserOfTV> getEntities() {
        return Collections.unmodifiableCollection(this.users.values());
    }
}
