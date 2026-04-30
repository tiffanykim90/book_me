package book_me.controller.model;

import book_me.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;

    // Entity to DTO Constructor
    public CustomerData(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerFirstName = customer.getCustomerFirstName();
        this.customerLastName = customer.getCustomerLastName();
        this.customerEmail = customer.getCustomerEmail();
    }
    
    // DTO to Entity Converter
    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerFirstName(customerFirstName);
        customer.setCustomerLastName(customerLastName);
        customer.setCustomerEmail(customerEmail);
        return customer;
    }
}