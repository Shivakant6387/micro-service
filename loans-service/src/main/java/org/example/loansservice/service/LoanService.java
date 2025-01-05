package org.example.loansservice.service;

import org.example.loansservice.dto.LoanDto;

public interface LoanService {
    void createLoan(String mobileNumber);

    LoanDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoanDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
