package org.example.loansservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.example.loansservice.constants.LoansConstants;
import org.example.loansservice.dto.LoanDto;
import org.example.loansservice.dto.ResponseDto;
import org.example.loansservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
        loanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
        LoanDto loanDto = loanService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@RequestBody @Valid LoanDto loanDto) {
        boolean updatedLoan = loanService.updateLoan(loanDto);
        if (updatedLoan) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
        boolean isDelete = loanService.deleteLoan(mobileNumber);
        if (isDelete) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }
}