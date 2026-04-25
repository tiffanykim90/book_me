package book_me.controller.model;

import java.util.HashSet;
import java.util.Set;
import book_me.entity.Agent;
import book_me.entity.Customer;
import book_me.entity.Service;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgentData {
    private Long agentId;
    private String agentName;
    
    private Set<String> services = new HashSet<>();
    private Set<CustomerResponse> customers = new HashSet<>();

    public AgentData(Agent agent) {
        this.agentId = agent.getAgentId();
        this.agentName = agent.getAgentName();

        for (Service service : agent.getServices()) {
            this.services.add(service.getTask());
        }

        for (Customer customer : agent.getCustomers()) {
            this.customers.add(new CustomerResponse(customer));
        }
    }

    public Agent toAgent() {
        Agent agent = new Agent();
        agent.setAgentId(agentId);
        agent.setAgentName(agentName);
        return agent;
    }

    @Data
    @NoArgsConstructor
    public static class CustomerResponse {
        private Long customerId;

        public CustomerResponse(Customer customer) {
            this.customerId = customer.getCustomerId();
        }
    }
}