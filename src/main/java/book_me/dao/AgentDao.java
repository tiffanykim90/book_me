package book_me.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import book_me.entity.Agent;

public interface AgentDao extends JpaRepository<Agent, Long> {
    
}
