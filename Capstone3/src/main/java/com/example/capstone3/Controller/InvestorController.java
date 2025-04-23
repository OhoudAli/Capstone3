package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Investor;
import com.example.capstone3.Repository.InvestorRepository;
import com.example.capstone3.Service.InvestorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/investor")
public class InvestorController {

    private final InvestorService investorService;

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Investor investor){
        investorService.addInvestor(investor);
        return ResponseEntity.status(200).body(new ApiResponse("Added successfully"));
    }

    @GetMapping("/getall")
    public ResponseEntity getall(){
        return ResponseEntity.status(200).body(investorService.getAllInvestor());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Investor investor, @PathVariable Integer id){
        investorService.updateInvestor(investor,id);
        return ResponseEntity.status(200).body(new ApiResponse("Updated successfully "));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        investorService.deleteInvestor(id);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted successfully"));
    }
}
