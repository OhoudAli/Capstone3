package com.example.capstone3.Repository;

import com.example.capstone3.Model.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgreementRepository extends JpaRepository<Agreement,Integer> {

    Agreement findAgreementById(Integer id);
}
