package pl.zarembapawel.customers.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.zarembapawel.customers.model.Customer;
import pl.zarembapawel.customers.service.CustomersService;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomersController {

    private CustomersService service;

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId) {
        Customer customer = service.getCustomer(customerId);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return customer;
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Customer customer) {
        HttpStatus status = service.add(customer);
        return new ResponseEntity<>(status);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> update(@PathVariable Integer customerId, @RequestBody Customer customer) {
        HttpStatus status = service.update(customerId, customer);
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Integer customerId) {
        HttpStatus status = service.delete(customerId);
        return new ResponseEntity<>(status);
    }
}
