package book_me.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import book_me.entity.MyBusiness;
import book_me.entity.Agent;
import book_me.entity.Customer;
import book_me.dao.MyBusinessDao;
import book_me.dao.ServiceDao;
import book_me.controller.model.AgentData;
import book_me.controller.model.CustomerData;
import book_me.controller.model.MyBusinessData;
import book_me.controller.model.ServiceData;
import book_me.dao.AgentDao;
import book_me.dao.CustomerDao;




@Service
public class MyBusinessService {
	
	@Autowired
	private AgentDao agentDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private MyBusinessDao businessDao;
	
	
	@Autowired
	private ServiceDao serviceDao;

	
	@Transactional
	public AgentData saveAgent(Long businessId, AgentData agentData ) {
		MyBusiness mybusiness = businessDao.findById(businessId)
				.orElseThrow(() -> new NoSuchElementException ("Business with ID " + businessId + " not found"));
	
	Agent agent = findOrCreateAgent(agentData.getAgentId());
	
	agent.setAgentName(agentData.getAgentName());
	
	agent.setMybusiness(mybusiness);
	mybusiness.getAgents().add(agent);
	
	Agent dbAgent = agentDao.save(agent);
	
	return new AgentData(dbAgent);
		}	
	
	private Agent findOrCreateAgent(Long agentId) {
		if (agentId == null) {
			return new Agent();
		}
		return agentDao.findById(agentId)
				.orElseThrow(() -> new NoSuchElementException ("Agent with ID" + agentId + "not found"));
				
		
	}

	@Transactional(readOnly = true)
	public List<AgentData> retrieveAllAgents(Long BusinessId) {
		List<Agent> agents = agentDao.findAll();
			List<AgentData>	result = new LinkedList<>();
			for (Agent agent : agents) {
				if(agent.getMybusiness().getBusinessId().equals(BusinessId)) {
				result.add(new AgentData(agent));
				
				}
			}
			return result;
		}

	@Transactional(readOnly = true)
	public AgentData retrieveAgentById(Long businessId, Long agentId) {
		Agent agent = agentDao.findById(agentId)
				.orElseThrow(() -> new NoSuchElementException(
						"Agent with ID" + agentId + "was not found."));
		
		if (!agent.getMybusiness().getBusinessId().equals(businessId)) {
			throw new IllegalArgumentException(
					"Agent with ID" + agentId + "does not belong to business with ID" + businessId);
		}
		return new AgentData(agent);

	}
	@Transactional
	public void deleteAgentById(Long businessId, Long agentId) {
		Agent agent = agentDao.findById(agentId)
				.orElseThrow(() -> new NoSuchElementException(
						"Agent with ID" + agentId + "was not found."));
		
		if (!agent.getMybusiness().getBusinessId().equals(businessId)) {
			throw new IllegalArgumentException("Agent ID" + agentId + "does not belong to Business ID" + businessId);
			}
		agentDao.delete(agent);
	}
	
	@Transactional
	public CustomerData saveCustomer(Long businessId, Long agentId, CustomerData customerData) {
		Agent agent = agentDao.findById(agentId)
				.orElseThrow(() -> new NoSuchElementException("Agent with ID" + agentId + "not found"));
		
		if (!agent.getMybusiness().getBusinessId().equals(businessId)) {
	        throw new IllegalArgumentException("Agent ID " + agentId + " does not belong to Business ID " + businessId);
	    }

	    // 2. Convert DTO to Entity
	    Customer customer = customerData.toCustomer();
	    
	    // 3. Set the relationship
	    customer.setAgent(agent);
	    agent.getCustomers().add(customer);

	    // 4. Save and return DTO
	    Customer dbCustomer = customerDao.save(customer);
	    return new CustomerData(dbCustomer);
	}

	@Transactional(readOnly = true)
	public List<CustomerData> retrieveCustomersByAgentId(Long businessId, Long agentId) {
	    // Verify agent ownership first for security
	    Agent agent = agentDao.findById(agentId)
	            .orElseThrow(() -> new NoSuchElementException("Agent with ID " + agentId + " not found"));

	    if (!agent.getMybusiness().getBusinessId().equals(businessId)) {
	        throw new IllegalArgumentException("Agent ID " + agentId + " does not belong to Business ID " + businessId);
	    }

	    return agent.getCustomers().stream()
	            .map(CustomerData::new)
	            .toList();
	}
	@Transactional
	public MyBusinessData saveBusiness(MyBusinessData myBusinessData) {
		Long businessId = myBusinessData.getBusinessId();
		MyBusiness myBusiness = findOrCreateBusiness(businessId);
		
		copyBusinessFields(myBusiness, myBusinessData);
		
		MyBusiness dbBusiness = businessDao.save(myBusiness);
		return new MyBusinessData(dbBusiness);
	}
	@Transactional(readOnly =true)
	public List<MyBusinessData> retrieveAllBusinesses() {
		List<MyBusiness> businesses = businessDao.findAll();
		return businesses.stream()
				.map(MyBusinessData::new)
				.toList();
	}
	
	public MyBusinessData retrieveBusinessById(Long businessId) {
		MyBusiness myBusiness = businessDao.findById(businessId)
				.orElseThrow(() -> new NoSuchElementException(
						"Business with ID" + businessId + "was not found."));
						
		return new MyBusinessData(myBusiness);

	
	}
	private MyBusiness findOrCreateBusiness(Long businessId) {
		if (businessId == null) {
			return new MyBusiness();
			}else {
				return businessDao.findById(businessId)
						.orElseThrow(() -> new NoSuchElementException(
								"Business with ID" + businessId + "was not found."));
			}
	}//helper method to synchronize data between MyBusinessData and MyBusiness
	private void copyBusinessFields(MyBusiness myBusiness, MyBusinessData myBusinessData) {
		myBusiness.setBusinessName(myBusinessData.getBusinessName());
		myBusiness.setStreetAddress(myBusinessData.getStreetAddress());
		myBusiness.setCity(myBusinessData.getCity());
		myBusiness.setState(myBusinessData.getState());
		myBusiness.setZip(myBusinessData.getZip());
		myBusiness.setPhone(myBusinessData.getPhone());
									
			}
	@Transactional(readOnly = false)
	public ServiceData saveService(ServiceData serviceData) {
		book_me.entity.Service service = findOrCreateService(serviceData.getServiceId());
		copyServiceFields(service, serviceData);
		book_me.entity.Service dbService = serviceDao.save(service);
		
		return new ServiceData(dbService);
	
	}
	@Transactional(readOnly = true)
	public ServiceData retrieveServiceById(Long serviceId) {
		book_me.entity.Service service = findServiceById(serviceId);
		return new ServiceData(service);
		
	}
	
	private book_me.entity.Service findServiceById(Long serviceId) {
		return serviceDao.findById(serviceId)
				.orElseThrow(() -> new NoSuchElementException(
						"Service with ID=" + serviceId + "was not found."));
		
	}
	
	
	private book_me.entity.Service findOrCreateService(Long serviceId) {
		if (serviceId == null) {
			return new book_me.entity.Service();
		}
		return findServiceById(serviceId);
	}
	
	private void copyServiceFields(book_me.entity.Service service, ServiceData serviceData) {
		service.setTask(serviceData.getTask());
		
	}
	@Transactional(readOnly = true)
	public List<ServiceData> retrieveAllServices(){
		List<book_me.entity.Service> services = serviceDao.findAll();
		return services.stream()
				.map(ServiceData::new)
				.toList();
		
	}
	@Transactional
	public void deleteServiceById(Long serviceId) {
		book_me.entity.Service service = findServiceById(serviceId);
		serviceDao.delete(service);
	}
	@Transactional
	public void deleteBusinessById(Long businessId) {
		MyBusiness myBusiness = findOrCreateBusiness(businessId);
		businessDao.delete(myBusiness);
	}
	
	@Transactional
	public void deletecustomerById(Long CustomerId) {
		Customer customer = customerDao.findById(CustomerId)
				.orElseThrow(() -> new NoSuchElementException("Customer with ID =" + CustomerId + " not found."));
		customerDao.delete(customer);
	}
	@Transactional(readOnly = false)
	public AgentData addServiceToAgent(Long businessId, Long agentId, Long serviceId) {
	    Agent agent = findAgentById(businessId, agentId);
	    book_me.entity.Service service = findServiceById(serviceId);
	    
	    agent.getServices().add(service);
	    return new AgentData(agentDao.save(agent));
	    
	    
	}

	private Agent findAgentById(Long businessId, Long agentId) {
		Agent agent = agentDao.findById(agentId)
				.orElseThrow(() -> new NoSuchElementException(
						"Agent with ID=" + agentId + " was not found."));
		if(!agent.getMybusiness().getBusinessId().equals(businessId)) {
			throw new IllegalArgumentException(
					"Agent with ID=" + agentId + " does not belong to business with ID" + businessId);
			
		}
		return agent;
	}

	@Transactional(readOnly = false)
	public void removeServiceFromAgent(Long businessId, Long agentId, Long serviceId) {
	    Agent agent = findAgentById(businessId, agentId);
	    book_me.entity.Service service = findServiceById(serviceId);
	    
	    agent.getServices().remove(service);
	    agentDao.save(agent);
	}
}

