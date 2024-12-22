package org.example.accounts.mapper;

import org.example.accounts.dto.AccountsRequestDto;
import org.example.accounts.model.Accounts;

public class AccountMapper {
    public static AccountsRequestDto mapToAccountsDto(Accounts accounts, AccountsRequestDto accountsRequestDto) {
      accountsRequestDto.setAccountNumber(accounts.getAccountNumber());
      accountsRequestDto.setAccountType(accounts.getAccountType());
      accountsRequestDto.setBranchAddress(accounts.getBranchAddress());
        return accountsRequestDto;
    }
    public static Accounts mapToAccounts(AccountsRequestDto accountsRequestDto, Accounts accounts) {
        accounts.setAccountNumber(accountsRequestDto.getAccountNumber());
        accounts.setAccountType(accountsRequestDto.getAccountType());
        accounts.setBranchAddress(accountsRequestDto.getBranchAddress());
        return accounts;
    }
}
