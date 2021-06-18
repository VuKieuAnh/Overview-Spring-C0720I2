package repo;

import model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepo extends CrudRepository<Customer, Long> {
    List<Customer> findByAddress(String address);
}
