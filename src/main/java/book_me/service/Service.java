package book_me.service;

public @interface Service {


	@Transactional(readOnly = true)
	public List<CustomerData> retrieveCustomersByAgentId(Long businessId, Long agentId) {
	    Agent agent = agentDao.findById(agentId)
	            .orElseThrow(() -> new NoSuchElementException("Agent with ID " + agentId + " not found"));

	    if (!agent.getMyBusiness().getBusinessId().equals(businessId)) {
	        throw new IllegalArgumentException("Agent ID " + agentId + " does not belong to Business ID " + businessId);
	    }

	    return agent.getCustomers().stream()
	            .map(CustomerData::new)
	            .toList();
	}
}
