package book_me.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import book_me.controller.model.MyBusinessData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class MyBusiness {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_id")
	private Long businessId;
	
	private String businessName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	@OneToMany(mappedBy = "mybusiness", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Agent> agents = new HashSet<>();

	public static MyBusinessData saveAllBusinesses() {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<MyBusinessData> retrieveAllBusinesses() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
