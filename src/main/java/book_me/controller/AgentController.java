package book_me.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import book_me.controller.model.AgentData;
import book_me.service.MyBusinessService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/my_business/{businessId}/agent")
@Slf4j
public class AgentController {

    @Autowired
    private MyBusinessService myBusinessService;

    // 1. CREATE - Add an agent to a specific business
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgentData addAgent(@PathVariable Long businessId, 
                              @RequestBody AgentData agentData) {
        log.info("Adding agent {} to business ID={}", agentData.getAgentName(), businessId);
        return myBusinessService.saveAgent(businessId, agentData);
    }

    // 2. READ ALL - Get all agents for a business
    @GetMapping
    public List<AgentData> getAllAgents(@PathVariable Long businessId) {
        log.info("Retrieving all agents for business ID={}", businessId);
        return myBusinessService.retrieveAllAgents(businessId);
    }

    // 3. READ ONE - Get a specific agent by ID
    @GetMapping("/{agentId}")
    public AgentData getAgentById(@PathVariable Long businessId, 
                                  @PathVariable Long agentId) {
        log.info("Retrieving agent ID={} for business ID={}", agentId, businessId);
        return myBusinessService.retrieveAgentById(businessId, agentId);
    }

    // 4. UPDATE - Update an existing agent's info
    @PutMapping("/{agentId}")
    public AgentData updateAgent(@PathVariable Long businessId,
                                 @PathVariable Long agentId,
                                 @RequestBody AgentData agentData) {
        agentData.setAgentId(agentId);
        log.info("Updating agent ID={} for business ID={}", agentId, businessId);
        return myBusinessService.saveAgent(businessId, agentData);
    }

    // 5. DELETE - Remove an agent
    @DeleteMapping("/{agentId}")
    public Map<String, String> deleteAgentById(@PathVariable Long businessId, 
                                               @PathVariable Long agentId) {
        log.info("Deleting agent ID={} from business ID={}", agentId, businessId);
        myBusinessService.deleteAgentById(businessId, agentId);
        return Map.of("message", "Agent with ID=" + agentId + " deleted successfully.");
    }
}