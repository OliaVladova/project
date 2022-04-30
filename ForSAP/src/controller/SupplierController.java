package controller;

import repository.SupplierRepository;
import suppliers.BaseSupplier;

public class SupplierController {
    private SupplierRepository supplierRepository;

    public SupplierController() {
        this.supplierRepository = new SupplierRepository();
    }

    public void addSupplier(BaseSupplier supplier) {
        this.supplierRepository.add(supplier);
    }

    public String createSupplier(int id, String supplierName, double price, String deadline) {
        BaseSupplier supplier = this.supplierRepository.getByName(supplierName);
        if (supplier != null) {
            throw new IllegalArgumentException("Username is taken!");
        }

        supplier = new BaseSupplier(id, supplierName, price);
        this.supplierRepository.add(supplier);
        return String.format("Successfully registered supplier: %s", supplierName);

    }

    public void deleteSupplier(String username) {
        BaseSupplier supplier = this.supplierRepository.getByName(username);
        if (supplier == null) {
            throw new IllegalArgumentException("Missing supplier!");
        }
        this.supplierRepository.remove(supplier);
    }
}
