package in.ineuron.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import in.ineuron.model.Customer;

public interface ICustomerDAO extends JpaRepository<Customer, Integer> {

}
