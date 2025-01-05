package org.example.loansservice.repository;

import org.example.loansservice.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loans,Long> {

    Optional<Loans> findByMobileNumber(String mobileNumber);
    Optional<Loans> findByLoanNumber(String loanNumber);
}
