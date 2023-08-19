package pl.zarembapawel.customers.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return service.getCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Customer customer) {
        HttpStatus status = service.add(customer);
        return new ResponseEntity<>(status);
    }
}
