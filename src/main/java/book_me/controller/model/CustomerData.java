package book_me.controller.model;

import book_me.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {
    private Long customerId;
    // Add other fields here as you add them to your Entity (e.g., private String name;)

    public CustomerData(Customer customer) {
        this.customerId = customer.getCustomerId();
    }
    
    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        return customer;
    }
}