package com.ig.affiliatewebsite.model;

public class BankInfo {
    private Long id;
    private Integer userId;
    private String accountName;
    private String accountNumber;
    private String bankBranchCode;
    private String bankBranchName;
    private String paypalEmailAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getPaypalEmailAddress() {
        return paypalEmailAddress;
    }

    public void setPaypalEmailAddress(String paypalEmailAddress) {
        this.paypalEmailAddress = paypalEmailAddress;
    }
}
