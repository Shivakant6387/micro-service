package org.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Schema(
        name = "Customers",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @NotEmpty(message = "Mobile number can not be a null or empty")
    private String mobileNumber;
    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Email address should be valid value")
    private String email;
    private AccountsRequestDto accountsRequestDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountsRequestDto getAccountsRequestDto() {
        return accountsRequestDto;
    }

    public void setAccountsRequestDto(AccountsRequestDto accountsRequestDto) {
        this.accountsRequestDto = accountsRequestDto;
    }
}
