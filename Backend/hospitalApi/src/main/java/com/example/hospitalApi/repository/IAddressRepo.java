package com.example.hospitalApi.repository;

import com.example.hospitalApi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepo extends JpaRepository<Address,String> {
}
