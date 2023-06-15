package com.ig.affiliatewebsite.model;

import java.math.BigDecimal;

public class EarningInfo {
    private Long id;
    private Integer userId;
    private BigDecimal commissionEarn;
    private BigDecimal deduction;
    private BigDecimal deposit;
    private BigDecimal depositIncome;
    private BigDecimal expectedRevenuePercentage;

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

    public BigDecimal getCommissionEarn() {
        return commissionEarn;
    }

    public void setCommissionEarn(BigDecimal commissionEarn) {
        this.commissionEarn = commissionEarn;
    }

    public BigDecimal getDeduction() {
        return deduction;
    }

    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getDepositIncome() {
        return depositIncome;
    }

    public void setDepositIncome(BigDecimal depositIncome) {
        this.depositIncome = depositIncome;
    }

    public BigDecimal getExpectedRevenuePercentage() {
        return expectedRevenuePercentage;
    }

    public void setExpectedRevenuePercentage(BigDecimal expectedRevenuePercentage) {
        this.expectedRevenuePercentage = expectedRevenuePercentage;
    }
}
