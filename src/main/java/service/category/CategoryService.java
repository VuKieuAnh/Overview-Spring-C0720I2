package service.category;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.ICategoryRepository;

import java.util.List;

//@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public void save(Category customer) {
        categoryRepository.save(customer);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void update(int id, Category customer) {
        customer.setId(new Long(id));
        categoryRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.delete(id);
    }
}
