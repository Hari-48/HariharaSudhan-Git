package com.example.springBootJPA.Repository;

import com.example.springBootJPA.Entity.HumanBeing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanBeingRepo extends JpaRepository<HumanBeing,Long> {
}
