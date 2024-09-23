package com.company.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.Company;
import com.company.CompanyRepository;
import com.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
       return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company,Long id) {

        Optional<Company> companyOptional=companyRepository.findById(id);
        
            if(companyOptional.isPresent()){
                Company companytoUpdate=companyOptional.get();
                company.setDescription(company.getDescription());
                company.setName(company.getName());
                company.setJobs(company.getJobs());
                companyRepository.save(companytoUpdate);
                return true;
        }
       return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
       return false;
    }

    @Override
    public Company getCompanyById(Long id) {
      return companyRepository.findById(id).orElse(null);
    }

}
