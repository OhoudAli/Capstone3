package com.example.capstone3.Service;


import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner getById(Integer id) {
        if(id == null){
            throw new ApiException("Owner not found");
        }
        return ownerRepository.findOwnerById(id);
    }

    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void updateOwner(Integer id, Owner owner) {
        Owner oldOwner = ownerRepository.findOwnerById(id);
        if(oldOwner == null){
            throw new ApiException("Owner not found");
        }
        oldOwner.setName(owner.getName());
        oldOwner.setEmail(owner.getEmail());
        oldOwner.setPassword(owner.getPassword());
        oldOwner.setPhone_number(owner.getPhone_number());

        ownerRepository.save(owner);
    }

    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.findOwnerById(id);
        if (owner == null) {
             new ApiResponse("Owner not found");
        }
        ownerRepository.delete(owner);
    }
}
