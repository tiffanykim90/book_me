package book_me.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import book_me.controller.model.ServiceData;
import book_me.service.MyBusinessService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/service")
@Slf4j
public class ServiceController {

    @Autowired
    private MyBusinessService myBusinessService;

    // CREATE - Add a new type of service
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceData createService(@RequestBody ServiceData serviceData) {
        log.info("Creating new service: {}", serviceData);
        return myBusinessService.saveService(serviceData);
    }

    // READ ALL - Get all available services
    @GetMapping
    public List<ServiceData> getAllServices() {
        log.info("Retrieving all services");
        return myBusinessService.retrieveAllServices();
        
    }

    //READ ONE - Get service details by ID
    @GetMapping("/{serviceId}")
    public ServiceData getServiceById(@PathVariable Long serviceId) {
        log.info("Retrieving service with ID={}", serviceId);
        return myBusinessService.retrieveServiceById(serviceId);
    }
    //UPDATE
    @PutMapping("/service/{serviceId}")
    public ServiceData updateService(@PathVariable Long serviceId, @RequestBody ServiceData serviceData) {
    	serviceData.setServiceId(serviceId);
    	log.info("Updating a service with ID {}: {}",serviceId, serviceData);
    	return myBusinessService.saveService(serviceData);
   
    }
}