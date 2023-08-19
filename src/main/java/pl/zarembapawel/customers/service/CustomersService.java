package pl.zarembapawel.customers.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.zarembapawel.customers.entity.CustomerEntity;
import pl.zarembapawel.customers.model.Customer;
import pl.zarembapawel.customers.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CustomersService {

    private CustomerRepository repository;

    private ModelMapper mapper;

    public List<Customer> getAll() {
        List<CustomerEntity> entities = repository.findAll();
        List<Customer> customers = new ArrayList<>();
        entities.forEach(e -> {
            customers.add(mapper.map(e, Customer.class));
        });
        return customers;
    }

    public Customer getCustomer(Integer id) {
        Optional<CustomerEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return mapper.map(entity, Customer.class);
        }
        return null;
    }

    public HttpStatus add(Customer customer) {
        log.info("Adding customer: {}", customer);
        if (isCustomerExist(customer)) {
            log.error("Customer already exist");
            return HttpStatus.CONFLICT;
        }
        customer.setDateAdd(LocalDateTime.now());
        customer.setDateUpdate(LocalDateTime.now());
        CustomerEntity entity = mapper.map(customer, CustomerEntity.class);
        repository.save(entity);
        log.info("Customer has been added");
        return HttpStatus.CREATED;
    }

    public HttpStatus update(Integer id, Customer customer) {
        log.info("Updating customer ID: {}: {}", id, customer);
        Optional<CustomerEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            customer.setId(id);
            customer.setDateAdd(entity.get().getDateAdd());
            customer.setDateUpdate(LocalDateTime.now());
            CustomerEntity newEntity = mapper.map(customer, CustomerEntity.class);
            repository.save(newEntity);
            log.info("Customer has been updated");
            return HttpStatus.NO_CONTENT;
        }
        log.error("Customer not found");
        return HttpStatus.NOT_FOUND;
    }

    public HttpStatus delete(Integer id) {
        log.info("Deleting customer ID: {}", id);
        Optional<CustomerEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            repository.deleteById(id);
            log.info("Customer has been deleted");
            return HttpStatus.NO_CONTENT;
        }
        log.error("Customer not found");
        return HttpStatus.NOT_FOUND;
    }

    private boolean isCustomerExist(Customer customer) {
        CustomerEntity entity = repository.findByEmailOrPhoneNumber(customer.getEmail(),
                                                                    customer.getPhoneNumber());
        return entity != null;
    }
}
