package book_me.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Agent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agentId;
	
	@EqualsAndHashCode.Exclude
	private String agentName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "business_id", nullable = false)
	private MyBusiness mybusiness;
	
	// Downward link to Customers
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private Set<Customer> customers = new HashSet<>();
	
 // Direct Many-to-Many link to Service
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "agent_service", 
        joinColumns = @JoinColumn(name = "agent_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services = new HashSet<>();
	
}
