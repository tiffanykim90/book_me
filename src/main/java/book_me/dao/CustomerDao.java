package book_me.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import book_me.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
}