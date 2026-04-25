package book_me.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import myBusiness.controller.model.AgentData;
import book_me.entity.MyBusiness;
import book_me.entity.Agent;
import book_me.dao.BusinessDao;
import book_me.dao.AgentDao;




@Service
public class MyBusinessService {
	@Autowired
	private BusinessDao businessDao;
	
	@Autowired
	private AgentDao agentDao;

	@Transactional
	public AgentData saveAgent(Long businessId, AgentData agentData ) {
		MyBusiness mybusiness = MybusinessDao.findById(businessId)
				.orElseThrow(() -> new NoSuchElementException ("Business with ID" + businessID + "not found"));
	
	Agent agent = new Agent();
	agent.setAgentName(agentData.getAgentName());
	
	agent.setMybusiness(mybusiness);
	mybusiness.getAgents().add(agent);
	
	Agent dbAgent = agentDao.save(agent);
	
	return new AgentData(dbAgent);
		}	

	@Transactional(readOnly = true)
	public List<AgentData> retrieveAllAgents(Long businessId) {
		MyBusiness mybusiness = mybusinessDao.findById(businessId)
				.orElseThrow(() -> new NoSuchElementException(
				"Business with ID" + businessId + "not found"));
		}

	@Transactional(readOnly = true)
	public AgentData retrieveAgentById(Long businessId, Long agentId) {
		Agent agent = agentDao.findById(agentId)
				.orElseThrow(() -> new NoSuchElementException(
						"Agent with ID" + agentId + "was not found."));
		
		if (!agent.getMybusiness().getBusinessId().equals(businessId)) {
			throw new IllegalArgumentException(
					"Agent with ID" + agentId + "does not belong to business with ID" + businessId);
			return new AgentData(agent);
		}
	}
	@Transactional
	public void deleteAgentById(Long businessId, Long agentId) {
		Agent agent = agentDao.findById(agentId)
				orElseThrow(() -> new NoSuchElementException(
						"Agent with ID" + agentId + "was not found."));
		
		if (!agent.getMybusiness().getBusinessId().equals(businessId)) {
			throw new IllegalArgumentException("Agent ID" + agentId + "does not belong to Business ID" + businessId);
			}
		agentDao.delete(agent);
	}
	
}