package Contracts;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ContractRepositoryImpl implements ContractRepository{
    private Map<Integer,Contract> contracts;

    public ContractRepositoryImpl() {
        this.contracts = new LinkedHashMap<>();
    }

    @Override
    public void add(Contract element) {
        this.contracts.putIfAbsent(element.getId(),element);
    }

    @Override
    public boolean remove(Contract element) {
        return this.contracts.remove(element.getId())!=null;
    }

    @Override
    public Contract getById(int id) {
        return this.contracts.get(id);
    }

    @Override
    public Collection<Contract> getEntities() {
        return Collections.unmodifiableCollection(this.contracts.values());
    }
}
