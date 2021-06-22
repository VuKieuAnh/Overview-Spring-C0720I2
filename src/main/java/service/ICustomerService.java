package service;

import exception.NotfoundException;
import model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(Long id) throws NotfoundException;

    void update(int id, Customer customer);

    void remove(Long id);

    List<Customer> findByAddress(String address);
}
