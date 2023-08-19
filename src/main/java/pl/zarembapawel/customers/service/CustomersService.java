package pl.zarembapawel.customers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.zarembapawel.customers.model.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomersService {

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        customers.add(mockExampleCustomer());
        return customers;
    }

    public Customer getCustomer(Integer id) {
        return mockExampleCustomer();
    }

    public HttpStatus add(Customer customer) {
        log.info("Adding customer: {}", customer);
        if (isCustomerExist(customer)) {
            log.error("Customer already exist");
            return HttpStatus.CONFLICT;
        }
        customer.setDateAdd(LocalDateTime.now());
        log.info("Customer has been added");
        return HttpStatus.CREATED;
    }

    private boolean isCustomerExist(Customer customer) {
        return false;
    }

    private Customer mockExampleCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setEmail("example@example.com");
        customer.setPhoneNumber("111222333");
        customer.setAddress("street 123, city");
        customer.setDateAdd(LocalDateTime.now());
        return customer;
    }
}
