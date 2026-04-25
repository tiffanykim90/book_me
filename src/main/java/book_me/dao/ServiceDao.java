package book_me.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book_me.entity.Service;

public interface ServiceDao extends JpaRepository<Service, Long> {

}
