package Contracts;

import java.util.Collection;

public interface ContractRepository {
    void add(Contract element);

    boolean remove(Contract element);
    Contract getById(int id);


    Collection<Contract> getEntities();
}
