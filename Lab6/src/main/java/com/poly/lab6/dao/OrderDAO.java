package com.poly.lab6.dao;

import com.poly.lab6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Long> {
}
