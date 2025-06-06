package com.example.capstone3.Repository;

import com.example.capstone3.Model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor,Integer> {

    Investor findInvestorById(Integer id);
}
