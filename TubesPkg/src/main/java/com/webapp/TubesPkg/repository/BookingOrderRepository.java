package com.webapp.TubesPkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.TubesPkg.models.BookingOrder;

public interface BookingOrderRepository extends JpaRepository<BookingOrder, Integer>{    
}
