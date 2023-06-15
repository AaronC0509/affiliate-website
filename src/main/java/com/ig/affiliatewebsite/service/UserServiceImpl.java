package com.ig.affiliatewebsite.service;

import com.ig.affiliatewebsite.model.*;
import com.ig.affiliatewebsite.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void saveBankInfo(BankInfo bankInfo) {
        userRepository.saveBankInfo(bankInfo);
    }
//
//    @Override
//    public void update(User user) {
//        userRepository.update(user);
//    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public EarningInfo findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public PostInfo findPostInfoByUserId(Integer userId) {
        return userRepository.findPostInfoByUserId(userId);
    }

    @Override
    public CustInfo findCustInfoByUserId(Integer userId) {
        return userRepository.findCustInfoByUserId(userId);
    }

    @Override
    public BankInfo findBankInfoByUserId(Integer userId) {
        return userRepository.findBankInfoByUserId(userId);
    }

    @Override
    public List<SalaryInfo> findSalaryInfoByUserId(Integer userId) {
        return userRepository.findSalaryInfoByUserId(userId);
    }

    @Override
    public List<Products> findProductsByUserId(Integer userId) {
        return userRepository.findProductsByUserId(userId);
    }

    @Override
    public void editCurrentDepositByUserId(BigDecimal amount, Integer userId) {
        userRepository.editCurrentDeposit(amount, userId);
    }

    public String createCheckoutSession(PaymentRequest paymentRequest) {
        Stripe.apiKey = "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";
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", Collections.singletonList("card"));
        params.put("line_items", Collections.singletonList(Map.of(
                "price_data", Map.of(
                        "currency", paymentRequest.getCurrency(),
                        "unit_amount", paymentRequest.getAmount() * 100,
                        "product_data", Map.of(
                                "name", "Invest With IG"
                        )
                ),
                "quantity", 1
        )));
        params.put("mode", "payment");
        params.put("success_url", "http://igaffiliate.site/invest-ig.html?payment_status=success");
        params.put("cancel_url", "http://igaffiliate.site/invest-ig.html?payment_status=cancel");
        params.put("metadata", paymentRequest.getMetadata());
        try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
