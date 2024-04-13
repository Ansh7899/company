package com.jobapp.company.service.impl;

import com.jobapp.company.model.Company;
import com.jobapp.company.repository.CompanyRepository;
import com.jobapp.company.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createJob(Company company) {
        companyRepository.saveAndFlush(company);
    }

    @Override
    public Boolean deleteCompany(Long id) {
        try {
            Optional<Company> companyOptional = companyRepository.findById(id);
            if (companyOptional.isPresent()) {
                companyRepository.deleteById(id);
                return true; // Deletion successful
            } else {
                return false; // Entity with given ID doesn't exist
            }
        } catch (Exception e) {
            return false; // Deletion unsuccessful due to exception
        }
    }


    @Override
    public Boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.saveAndFlush(company);
            return true;
        }
        return false;
    }


}
