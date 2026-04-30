package book_me.controller.model;

import book_me.entity.Service;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceData {
    
    private Long serviceId;
    private String task;

    
    public ServiceData(book_me.entity.Service service) {
        this.serviceId = service.getServiceId();
        this.task = service.getTask();
    }

  
    public book_me.entity.Service toService() {
        Service service = new Service();
        service.setServiceId(serviceId);
        service.setTask(task);
        return service;
    }
}