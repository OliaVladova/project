package controller;

import Contracts.BaseContract;
import Contracts.Contract;
import Contracts.ContractRepository;
import Contracts.ContractRepositoryImpl;
import channelCategories.Category;
import channelCategories.ChannelCategory;
import channels.TVChannel;
import repository.*;
import suppliers.BaseSupplier;
import suppliers.Supplier;
import users.User;
import users.UserOfTV;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Category> categoryRepository;
    private Repository<TVChannel> channelRepository;
    private Repository<Supplier> supplierRepository;
    private Repository<User> userRepository;
    private ContractRepository contractRepository;

    public ControllerImpl() {
        this.categoryRepository = new CategoryRepository();
        this.channelRepository = new ChannelRepository();
        this.supplierRepository = new SupplierRepository();
        this.userRepository = new UserRepository();
        this.contractRepository = new ContractRepositoryImpl();
    }

    public void addCategory(Category category) {
        this.categoryRepository.add(category);
    }

    public void addChannel(TVChannel channel) {
        this.channelRepository.add(channel);
    }

    public void addSupplier(Supplier supplier) {
        this.supplierRepository.add(supplier);
    }

    public String createUser(String username, String password, int id) {
        User user = this.userRepository.getByName(username);
        if (user != null) {
            throw new IllegalArgumentException("Username is taken!");
        }

        user = new UserOfTV(id, username, password);
        this.userRepository.add(user);
        return String.format("Successfully registered user: %s", username);

    }

    public String createSupplier(int id, String supplierName, double price, String deadline) {
        Supplier supplier = this.supplierRepository.getByName(supplierName);
        if (supplier != null) {
            throw new IllegalArgumentException("Username is taken!");
        }

        supplier = new BaseSupplier(id, supplierName, price, deadline);
        this.supplierRepository.add(supplier);
        return String.format("Successfully registered supplier: %s", supplierName);

    }
    public String createCategory(String name,int id,double price){
        Category category = this.categoryRepository.getByName(name);
        if (category!=null){
            throw new IllegalArgumentException("Category is present!");
        }
        category = new ChannelCategory(id,name,price);
        this.categoryRepository.add(category);
        return String.format("Successfully created category: %s", name);
    }

    public String buyCategory(String name, String username) {
        Category category = this.categoryRepository.getByName(name);
        if (category == null) {
            throw new IllegalArgumentException("Invalid category!");
        }
        User user = this.userRepository.getByName(username);
        if (user == null) {
            throw new IllegalArgumentException("You are not registered!");
        }
        user.getCategories().add(category);

        this.categoryRepository.remove(category);
        return String.format("Successfully bought category: %s", name);
    }

    public String createContract(int id, String date) {
        Contract contract = this.contractRepository.getById(id);
        if (contract != null) {
            throw new IllegalArgumentException("Contract is already present!");
        }
        contract = new BaseContract(id, date);
        this.contractRepository.add(contract);
        return String.format("Successfully created contract with id: %d", id);
    }

    public String signContract(Contract contract, String username, String supplier) {
        User user = this.userRepository.getByName(username);
        if (user == null){
            throw new IllegalArgumentException("No such user!");
        }
        user.getContracts().add(contract);
        supplierRepository.getByName(supplier).getContracts().add(contract);
        return String.format("Contract has been signed between %s and %s", username,supplier);
    }
    public void deleteClient(String username){
        User user = this.userRepository.getByName(username);
        if (user==null){
            throw new IllegalArgumentException("Missing user!");
        }
        this.userRepository.remove(user);
    }
    public void deleteSupplier(String username){
        Supplier supplier = this.supplierRepository.getByName(username);
        if (supplier==null){
            throw new IllegalArgumentException("Missing supplier!");
        }
        this.supplierRepository.remove(supplier);
    }

}
