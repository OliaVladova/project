package controller;

import Contracts.BaseContract;
import repository.ContractRepositoryImpl;
import channelCategories.ChannelCategory;
import channels.Channel;
import repository.*;
import suppliers.BaseSupplier;
import users.UserOfTV;

public class ControllerImpl {
    private CategoryRepository categoryRepository;
    private ChannelRepository channelRepository;
    private SupplierRepository supplierRepository;
    private UserRepository userRepository;
    private ContractRepositoryImpl contractRepository;

    public ControllerImpl() {
        this.categoryRepository = new CategoryRepository();
        this.channelRepository = new ChannelRepository();
        this.supplierRepository = new SupplierRepository();
        this.userRepository = new UserRepository();
        this.contractRepository = new ContractRepositoryImpl();
    }

    public String buyCategory(String name, String username) {
        ChannelCategory category = this.categoryRepository.getByName(name);
        if (category == null) {
            throw new IllegalArgumentException("Invalid category!");
        }
        UserOfTV user = this.userRepository.getByName(username);
        if (user == null) {
            throw new IllegalArgumentException("You are not registered!");
        }
        user.getCategories().add(category);

        this.categoryRepository.remove(category);
        return String.format("Successfully bought category: %s", name);
    }

    public String signContract(BaseContract contract, String username, String supplierName) {
        UserOfTV user = this.userRepository.getByName(username);
        BaseSupplier supplier = supplierRepository.getByName(supplierName);
        if (user == null) {
            throw new IllegalArgumentException("No such user!");
        }
        if (supplier == null) {
            throw new IllegalArgumentException("No such supplier!");
        }
        user.getContracts().add(contract);
        supplier.getContracts().add(contract);
        return String.format("Contract has been signed between %s and %s", username, supplier);
    }

    public void addPercentage(double percent, String supplierName) {
        BaseSupplier supplier = this.supplierRepository.getByName(supplierName);
        if (supplier == null) {
            throw new NullPointerException("Unexisting supplier!");
        }
        supplier.setPrice(supplier.getPrice() + supplier.getPrice() * percent);
    }


}
