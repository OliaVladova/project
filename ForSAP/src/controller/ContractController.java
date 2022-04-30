package controller;

import Contracts.BaseContract;
import repository.ContractRepositoryImpl;

public class ContractController {
    private ContractRepositoryImpl contractRepository;

    public ContractController() {
        this.contractRepository = new ContractRepositoryImpl();
    }
    public String createContract(int id,int userId,int supplierId, String date,String deadline) {
        BaseContract contract = this.contractRepository.getById(id);
        if (contract != null) {
            throw new IllegalArgumentException("Contract is already present!");
        }
        contract = new BaseContract(id,userId,supplierId, date,deadline);
        this.contractRepository.add(contract);
        return String.format("Successfully created contract with id: %d", id);
    }
}
