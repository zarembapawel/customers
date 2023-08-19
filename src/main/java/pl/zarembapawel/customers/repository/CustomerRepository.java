package pl.zarembapawel.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.zarembapawel.customers.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    CustomerEntity findByEmailOrPhoneNumber(String email, String phoneNumber);
}
