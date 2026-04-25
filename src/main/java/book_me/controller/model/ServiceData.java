package book_me.controller.model;

import java.util.HashSet;
import java.util.Set;
import book_me.entity.Service;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceData {
    
    private Long serviceId;
    private String task;

    
    public ServiceData(Service service) {
        this.serviceId = service.getServiceId();
        this.task = service.getTask();
    }

  
    public Service toService() {
        Service service = new Service();
        service.setServiceId(serviceId);
        service.setTask(task);
        return service;
    }
}