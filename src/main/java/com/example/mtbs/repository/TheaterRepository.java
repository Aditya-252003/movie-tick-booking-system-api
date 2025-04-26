package com.example.mtbs.repository;

import com.example.mtbs.enitity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,String >{

}
