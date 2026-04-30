package book_me.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import book_me.controller.model.CustomerData;
import book_me.service.MyBusinessService;

@RestController
@RequestMapping("/my_business/{businessId}/agent/{agentId}/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private MyBusinessService myBusinessService;

    // 1. CREATE - Add a customer to a specific agent
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerData addCustomer(@PathVariable Long businessId,
                                    @PathVariable Long agentId,
                                    @RequestBody CustomerData customerData) {
        log.info("Adding customer for agent ID={} in business ID={}", agentId, businessId);
        return myBusinessService.saveCustomer(businessId, agentId, customerData);
    }

    // 2. READ ALL - Get all customers for an agent
    @GetMapping
    public List<CustomerData> getCustomersByAgent(@PathVariable Long businessId,
                                                  @PathVariable Long agentId) {
        log.info("Retrieving customers for agent ID={}", agentId);
        return myBusinessService.retrieveCustomersByAgentId(businessId, agentId);
    }
    //3. UPDATE -updating customer's data
    @PutMapping("/{customerId}")
    public CustomerData updateCustomer(@PathVariable Long businessId,
    									@PathVariable Long agentId,
    									@PathVariable Long customerId,
    									@RequestBody CustomerData customerData) {
    	customerData.setCustomerId(customerId);
    	log.info("Updating customer ID ={} for agent ID={}", customerId, agentId);
    	return myBusinessService.saveCustomer(businessId, agentId, customerData);
    }
    //4. DELETE
    @DeleteMapping("/{customerId}")
    public void deleteCustomerById(@PathVariable Long businessId,
    								@PathVariable Long agentId,
    								@PathVariable Long customerId) {
    	log.info("Deleting customer ID ={} from agent ID={}", customerId, agentId);
    	myBusinessService.deletecustomerById(customerId);
    	
    }
}