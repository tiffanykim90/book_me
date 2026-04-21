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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Agent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agent_id;
	
	@EqualsAndHashCode.Exclude
	private Long business_id;

	@EqualsAndHashCode.Exclude
	private String agent_name;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "business_id", nullable = false)
	private MyBusiness mybusiness;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "service",
			joinColumns = @JoinColumn( name = "customer_id"),
			inverseJoinColumns = @JoinColumn(name = "agent_id")
			)
	private Set<Service> services = new HashSet<>();
	
	
	
}
