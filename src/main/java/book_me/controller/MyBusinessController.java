package book_me.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import MyBusiness.service.myBusinessService;
import book_me.controller.model.MyBusinessData;
import book_me.entity.MyBusiness;
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
	public MyBusinessData createdBusiness(@RequestBody MyBusinessData myBusiness) {
		log.info("Creating a new business: {}", myBusinessData);
		return MyBusiness.saveAllBusinesses();
		
	}
	//READ
	@GetMapping
    public List<MyBusinessData> getAllBusinesses() {
        log.info("Retrieving all businesses");
        return MyBusiness.retrieveAllBusinesses();

}
	//READ ONE - Get specific business details by ID
    @GetMapping("/{businessId}")
    public MyBusinessData getBusinessById(@PathVariable Long businessId) {
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
}
    