package book_me.controller.model;

import java.util.HashSet;
import java.util.Set;
import book_me.entity.Agent;
import book_me.entity.MyBusiness;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyBusinessData {

    private Long businessId;
    private String businessName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String phone;
    
    private Set<AgentData> agents = new HashSet<>();

    public MyBusinessData(MyBusiness myBusiness) {
        this.businessId = myBusiness.getBusinessId();
        this.businessName = myBusiness.getBusinessName();
        this.streetAddress = myBusiness.getStreetAddress();
        this.city = myBusiness.getCity();
        this.state = myBusiness.getState();
        this.zip = myBusiness.getZip();
        this.phone = myBusiness.getPhone();

        if (myBusiness.getAgents() != null) {
            for (Agent agent : myBusiness.getAgents()) {
                this.agents.add(new AgentData(agent));
            }
        }
    }
    

    public MyBusiness toMyBusiness() {
        MyBusiness myBusiness = new MyBusiness();
        myBusiness.setBusinessId(businessId);
        myBusiness.setBusinessName(businessName);
        myBusiness.setStreetAddress(streetAddress);
        myBusiness.setCity(city);
        myBusiness.setState(state);
        myBusiness.setZip(zip);
        myBusiness.setPhone(phone);
        
        
        return myBusiness;
    }
}