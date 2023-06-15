package com.ig.affiliatewebsite.controller;

import com.ig.affiliatewebsite.model.*;
import com.ig.affiliatewebsite.response.CheckoutSessionResponse;
import com.ig.affiliatewebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        User authenticatedUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/users/{userId}/earningInfo")
    public EarningInfo findByUserId(@PathVariable("userId") Integer userId) {
        return userService.findByUserId(userId);
    }

    @GetMapping("/users/{userId}/postInfo")
    public PostInfo findPostInfoByUserId(@PathVariable("userId") Integer userId) {
        return userService.findPostInfoByUserId(userId);
    }

    @GetMapping("/users/{userId}/custInfo")
    public CustInfo findCustInfoByUserId(@PathVariable("userId") Integer userId) {
        return userService.findCustInfoByUserId(userId);
    }

    @GetMapping("/users/{userId}/bankInfo")
    public BankInfo findBankInfoByUserId(@PathVariable("userId") Integer userId) {
        return userService.findBankInfoByUserId(userId);
    }

    @GetMapping("/users/{userId}/salaryInfo")
    public List<SalaryInfo> findSalaryInfoByUserId(@PathVariable("userId") Integer userId) {
        return userService.findSalaryInfoByUserId(userId);
    }

    @GetMapping("/users/{userId}/products")
    public List<Products> findProductsByUserId(@PathVariable("userId") Integer userId) {
        return userService.findProductsByUserId(userId);
    }

    @PostMapping("/users/bankInfo")
    public void save(@RequestBody BankInfo bankInfo) {
        userService.saveBankInfo(bankInfo);
    }

    @PostMapping("/users/create-checkout-session")
    public ResponseEntity<?> createCheckoutSession(@RequestBody PaymentRequest paymentRequest) {
        String checkoutSessionId = userService.createCheckoutSession(paymentRequest);
        if (checkoutSessionId != null) {
            return ResponseEntity.ok(new CheckoutSessionResponse(checkoutSessionId));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Checkout session creation failed");
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
}
