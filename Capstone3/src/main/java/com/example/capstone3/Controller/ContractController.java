package com.example.capstone3.Controller;


import com.example.capstone3.Model.Contract;
import com.example.capstone3.Service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contract")
public class ContractController {


    private final ContractService contractService;


    @PostMapping("/add/{ownerId}/{offerId}")
    public ResponseEntity save(@PathVariable Integer ownerId, @PathVariable Integer offerId, @RequestBody @Valid Contract contract) {
        contractService.addContract(ownerId,offerId,contract);
        return ResponseEntity.ok().body("Contract added successfully");

    }

    @GetMapping("/get-all")
    public ResponseEntity getContract() {
        List<Contract> c=contractService.getContract();
        if(c.isEmpty()){
            return ResponseEntity.ok().body("Contract list is empty");
        }
        return ResponseEntity.ok().body(c);
    }

    @PutMapping("update/{contractId}")
    public ResponseEntity updateContract(@PathVariable Integer contractId, @RequestBody @Valid Contract contract ) {
        contractService.updateContract(contractId,contract);
        return ResponseEntity.ok().body("Contract updated");
    }

    @DeleteMapping("delete/{contractId}")
    public ResponseEntity deleteContract(@PathVariable Integer contractId) {
        contractService.deleteContract(contractId);
        return ResponseEntity.ok().body("Contract deleted");
    }
}
