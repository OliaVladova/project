package controller;

import channelCategories.ChannelCategory;
import repository.CategoryRepository;

public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController() {
        this.categoryRepository = new CategoryRepository();
    }
    public void addCategory(ChannelCategory category) {
        this.categoryRepository.add(category);
    }

    public String createCategory(String name,int id,double price){
        ChannelCategory category = this.categoryRepository.getByName(name);
        if (category!=null){
            throw new IllegalArgumentException("Category is present!");
        }
        category = new ChannelCategory(id,name,price);
        this.categoryRepository.add(category);
        return String.format("Successfully created category: %s", name);
    }
}
