package repository;

import Contracts.Contract;
import suppliers.Supplier;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SupplierRepository implements Repository<Supplier> {
    private Map<String, Supplier> suppliers;

    public SupplierRepository() {
        this.suppliers = new LinkedHashMap<>();
    }

    @Override
    public void add(Supplier element) {
        this.suppliers.put(element.getName(), element);
    }

    @Override
    public boolean remove(Supplier element) {
        return this.suppliers.remove(element.getName())!=null;
    }

    public Supplier getByName(String name) {
        return this.suppliers.get(name);
    }

    @Override
    public Collection<Supplier> getEntities() {
        return Collections.unmodifiableCollection(this.suppliers.values());
    }
     public Contract getContractById(int id){
       Contract contract = this.suppliers.entrySet().stream().filter(suppliers ->suppliers.getValue().getContracts().stream().findFirst().filter(contr ->contr.))
     }
}
