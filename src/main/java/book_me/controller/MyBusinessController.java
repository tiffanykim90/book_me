package book_me.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import book_me.service.MyBusinessService;
import book_me.controller.model.MyBusinessData;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/my_business")
@Slf4j
public class MyBusinessController {
	
	@Autowired
	private MyBusinessService myBusinessService;
	
	//CREATE
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MyBusinessData createdBusiness(@RequestBody MyBusinessData myBusinessData) {
		log.info("Creating a new business: {}", myBusinessData);
		return myBusinessService.saveBusiness(myBusinessData);
	}
	//READ ALL
	@GetMapping
	public List<MyBusinessData> retrieveAllBusinesses(){
		log.info("Retrieving all businesses");
		return myBusinessService.retrieveAllBusinesses();
	}
	
	//READ ONE - this id to get specific business details by ID
    @GetMapping("/{businessId}")
    public MyBusinessData retrieveBusinessById(@PathVariable Long businessId) {
        log.info("Retrieving business with ID={}", businessId);
        return myBusinessService.retrieveBusinessById(businessId);
    }

    //UPDATE
    @PutMapping("/{businessId}")
    public MyBusinessData updateBusiness(@PathVariable Long businessId, 
                                          @RequestBody MyBusinessData myBusinessData) {
        myBusinessData.setBusinessId(businessId);
        log.info("Updating business with ID={}", businessId);
        return myBusinessService.saveBusiness(myBusinessData);
    }
    //DELETE
    @DeleteMapping("/{businessId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Map<String, String> deleteBusinessById(@PathVariable Long businessId) {
    	log.info("Deleting business with ID={}", businessId);
    	myBusinessService.deleteBusinessById(businessId);
    	
    	return Map.of("message", "Business with ID=" + businessId + "was deleted.");
    
    }
}
    