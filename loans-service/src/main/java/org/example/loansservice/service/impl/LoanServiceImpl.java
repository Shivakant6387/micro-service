package org.example.loansservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.loansservice.constants.LoansConstants;
import org.example.loansservice.dto.LoanDto;
import org.example.loansservice.exception.LoanAlreadyExistsException;
import org.example.loansservice.exception.ResourceNotFoundException;
import org.example.loansservice.mapper.LoansMapper;
import org.example.loansservice.model.Loans;
import org.example.loansservice.repository.LoanRepository;
import org.example.loansservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loanRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber" + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans loans = new Loans();
        long randomNumber = 100000000000L + new Random().nextInt(900000000);
        loans.setLoanNumber(Long.toString(randomNumber));
        loans.setMobileNumber(mobileNumber);
        loans.setLoanType(LoansConstants.HOME_LOAN);
        loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loans.setAmountPaid(0);
        loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loans;
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        return LoansMapper.mapToLoansDto(loans, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loans loans = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loanDto.getLoanNumber()));
        LoansMapper.mapToLoans(loanDto, loans);
        loanRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        loanRepository.deleteById(loans.getLoanId());
        return true;
    }
}
