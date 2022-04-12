package repository;

import channelCategories.Category;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CategoryRepository implements Repository<Category> {
    private Map<String,Category> categories;

    public CategoryRepository() {
        this.categories = new LinkedHashMap<>();
    }

    @Override
    public void add(Category element) {
        this.categories.putIfAbsent(element.getType(), element);
    }

    @Override
    public boolean remove(Category element) {
        return this.categories.remove(element.getType())!=null;
    }

    public Category getByName(String name) {
        return this.categories.get(name);
    }

    @Override
    public Collection<Category> getEntities() {
        return Collections.unmodifiableCollection(this.categories.values());
    }
}
