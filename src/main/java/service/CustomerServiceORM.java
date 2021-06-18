package service;

import model.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import repo.ICustomerRepo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerServiceORM implements ICustomerService{


    @Autowired(required = true)
    private ICustomerRepo customerRepo;


    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepo.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);

    }

    @Override
    public Customer findById(Long id) {
        return customerRepo.findOne(id)
                ;
    }

    @Override
    public List<Customer> findByAddress(String address) {
        return customerRepo.findByAddress(address);
    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void remove(Long id) {
        customerRepo.delete(id);
    }
}
