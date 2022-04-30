package repository;

import Contracts.BaseContract;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ContractRepositoryImpl {
    private Map<Integer, BaseContract> contracts;

    public ContractRepositoryImpl() {
        this.contracts = new LinkedHashMap<>();
    }

    public void add(BaseContract element) {
        this.contracts.putIfAbsent(element.getId(),element);
    }

    public boolean remove(BaseContract element) {
        return this.contracts.remove(element.getId())!=null;
    }

    public BaseContract getById(int id) {
        return this.contracts.get(id);
    }

    public Collection<BaseContract> getEntities() {
        return Collections.unmodifiableCollection(this.contracts.values());
    }
}
