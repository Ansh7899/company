package com.jobapp.company.controllers;

import com.jobapp.company.model.Company;
import com.jobapp.company.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.findAll(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findByCompanyId(@PathVariable Long id){
        Company company = companyService.findCompanyById(id);
        if(company != null)
            return new ResponseEntity<>(company, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createJob(company);
        return new ResponseEntity<>("Company added successfully"
                , HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateCompany(@RequestBody Company company,
                                                @PathVariable Long id) {
        Boolean updated = companyService.updateCompany(id, company);
        if(updated)
            return new ResponseEntity<>("Company Updated Successfully"
                    , HttpStatus.OK);
        return new ResponseEntity<>("Company Not Found"
                , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        Boolean deleted = companyService.deleteCompany(id);
        if(deleted)
            return new ResponseEntity<>("Company Deleted Successfully"
                    , HttpStatus.OK);
        return new ResponseEntity<>("Company Not Found"
                , HttpStatus.NOT_FOUND);
    }
}
