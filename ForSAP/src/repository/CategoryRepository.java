package repository;


import channelCategories.ChannelCategory;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CategoryRepository  {
    private Map<String, ChannelCategory> categories;

    public CategoryRepository() {
        this.categories = new LinkedHashMap<>();
    }

    public void add(ChannelCategory element) {
        this.categories.putIfAbsent(element.getType(), element);
    }

    public boolean remove(ChannelCategory element) {
        return this.categories.remove(element.getType())!=null;
    }

    public ChannelCategory getByName(String name) {
        return this.categories.get(name);
    }

    public Collection<ChannelCategory> getEntities() {
        return Collections.unmodifiableCollection(this.categories.values());
    }
}
