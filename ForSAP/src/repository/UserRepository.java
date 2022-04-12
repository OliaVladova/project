package repository;

import users.User;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserRepository implements Repository<User> {
    private Map<String, User> users;

    public UserRepository() {
        this.users = new LinkedHashMap<>();
    }

    @Override
    public void add(User element) {
            this.users.put(element.getName(),element);
    }

    @Override
    public boolean remove(User element) {
        return this.users.remove(element.getName())!=null;
    }

    public User getByName(String name) {
        return this.users.get(name);
    }

    @Override
    public Collection<User> getEntities() {
        return Collections.unmodifiableCollection(this.users.values());
    }
}
