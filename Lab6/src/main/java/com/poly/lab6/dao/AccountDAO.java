package com.poly.lab6.dao;

import com.poly.lab6.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository<Account,String> {
}
