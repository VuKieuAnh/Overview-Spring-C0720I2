package service.category;

import model.Category;
import model.Customer;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    void save(Category customer);

    Category findById(Long id);

    void update(int id, Category customer);

    void remove(Long id);
}
