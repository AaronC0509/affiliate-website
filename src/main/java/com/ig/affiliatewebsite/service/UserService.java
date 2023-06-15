package com.ig.affiliatewebsite.service;

import com.ig.affiliatewebsite.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByUsernameAndPassword(String username, String password);
    EarningInfo findByUserId(Integer userId);
    PostInfo findPostInfoByUserId(Integer userId);
    CustInfo findCustInfoByUserId(Integer userId);
    BankInfo findBankInfoByUserId(Integer userId);
    List<SalaryInfo> findSalaryInfoByUserId(Integer userId);
    List<Products> findProductsByUserId(Integer userId);
    String createCheckoutSession(PaymentRequest paymentRequest);
    void editCurrentDepositByUserId(BigDecimal amount, Integer userId);
    void saveBankInfo(BankInfo bankInfo);
//    void update(User user);
    void deleteById(Long id);
}