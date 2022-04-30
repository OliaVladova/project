package repository;

import Contracts.BaseContract;
import suppliers.BaseSupplier;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SupplierRepository {
    private Map<String, BaseSupplier> suppliers;

    public SupplierRepository() {
        this.suppliers = new LinkedHashMap<>();
    }

    public void add(BaseSupplier element) {
        this.suppliers.put(element.getName(), element);
    }

    public boolean remove(BaseSupplier element) {
        return this.suppliers.remove(element.getName())!=null;
    }

    public BaseSupplier getByName(String name) {
        return this.suppliers.get(name);
    }

    public Collection<BaseSupplier> getEntities() {
        return Collections.unmodifiableCollection(this.suppliers.values());
    }

}
