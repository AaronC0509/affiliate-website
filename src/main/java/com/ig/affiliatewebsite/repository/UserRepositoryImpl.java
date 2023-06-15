package com.ig.affiliatewebsite.repository;

import com.ig.affiliatewebsite.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_ALL_USERS = "SELECT * FROM dbo.users";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM dbo.users WHERE id = ?";
    private static final String INSERT_BANK_INFO = "INSERT INTO Bank_Info (user_id, account_name, account_number, bank_branch_code, bank_branch_name, paypal_email_address) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET name = ?, email = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?";
    private static final String SELECT_EARNING_INFO_BY_USERID = "SELECT * FROM Earning_Info WHERE user_id = ? ";
    private static final String SELECT_POST_INFO_BY_USERID = "SELECT * FROM Post_Info WHERE user_id = ? ";
    private static final String SELECT_CUST_INFO_BY_USERID = "SELECT * FROM Cust_Info WHERE user_id = ? ";
    private static final String SELECT_BANK_INFO_BY_USERID = "SELECT * FROM Bank_Info WHERE user_id = ? ";
    private static final String SELECT_SALARY_INFO_BY_USERID = "SELECT * FROM Salary_Info WHERE user_id = ? ";
    private static final String SELECT_PRODUCTS_BY_USERID = "SELECT * FROM Products WHERE user_id = ? ";
    private static final String EDIT_CURRENT_DEPOSIT_BY_USERID = "UPDATE Earning_Info SET deposit = ? WHERE user_id = ?";

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new Object[]{id}, new UserRowMapper());
    }

    @Override
    public void saveBankInfo(BankInfo bankInfo) {
        jdbcTemplate.update(INSERT_BANK_INFO, bankInfo.getUserId(), bankInfo.getAccountName(), bankInfo.getAccountNumber(), bankInfo.getBankBranchCode(), bankInfo.getBankBranchName(), bankInfo.getPaypalEmailAddress());
    }

    @Override
    public void editCurrentDeposit(BigDecimal amount, Integer userId) {
        jdbcTemplate.update(EDIT_CURRENT_DEPOSIT_BY_USERID, new Object[]{amount, userId});
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_USER_BY_ID, id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            return jdbcTemplate.queryForObject(SELECT_USER_BY_USERNAME_AND_PASSWORD, new Object[]{username, password}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public EarningInfo findByUserId(Integer userId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_EARNING_INFO_BY_USERID, new Object[]{userId}, new EarningInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public PostInfo findPostInfoByUserId(Integer userId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_POST_INFO_BY_USERID, new Object[]{userId}, new PostInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public CustInfo findCustInfoByUserId(Integer userId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CUST_INFO_BY_USERID, new Object[]{userId}, new CustInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public BankInfo findBankInfoByUserId(Integer userId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BANK_INFO_BY_USERID, new Object[]{userId}, new BankInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<SalaryInfo> findSalaryInfoByUserId(Integer userId) {
        try {
            return jdbcTemplate.query(SELECT_SALARY_INFO_BY_USERID, new Object[]{userId}, new SalaryInfoRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Products> findProductsByUserId(Integer userId) {
        try {
            return jdbcTemplate.query(SELECT_PRODUCTS_BY_USERID, new Object[]{userId}, new ProductsRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }

    private static final class EarningInfoRowMapper implements RowMapper<EarningInfo> {
        public EarningInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            EarningInfo earningInfo = new EarningInfo();
            earningInfo.setId(rs.getLong("id"));
            earningInfo.setUserId(rs.getInt("user_id"));
            earningInfo.setCommissionEarn(rs.getBigDecimal("commission_earn"));
            earningInfo.setDeduction(rs.getBigDecimal("deduction"));
            earningInfo.setDeposit(rs.getBigDecimal("deposit"));
            earningInfo.setDepositIncome(rs.getBigDecimal("income_deposit"));
            earningInfo.setExpectedRevenuePercentage(rs.getBigDecimal("expected_revenue"));
            return earningInfo;
        }
    }

    private static final class PostInfoRowMapper implements RowMapper<PostInfo> {
        public PostInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            PostInfo postInfo = new PostInfo();
            postInfo.setId(rs.getLong("id"));
            postInfo.setUserId(rs.getInt("user_id"));
            postInfo.setAffiliateNum(rs.getInt("affiliate_num"));
            postInfo.setPendingNum(rs.getInt("pending_num"));
            postInfo.setLinuxNum(rs.getInt("linux_num"));
            return postInfo;
        }
    }

    private static final class CustInfoRowMapper implements RowMapper<CustInfo> {
        public CustInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustInfo custInfo = new CustInfo();
            custInfo.setId(rs.getLong("id"));
            custInfo.setUserId(rs.getInt("user_id"));
            custInfo.setLabelNotification(rs.getString("label_notification"));
            custInfo.setLabelInvest(rs.getString("label_invest"));
            custInfo.setLabelReleaseNotes(rs.getString("label_releasenotes"));
            custInfo.setTextInvest(rs.getString("text_invest"));
            custInfo.setTextNotification(rs.getString("text_notification"));
            custInfo.setTextReleaseNotes(rs.getString("text_releasenotes"));
            custInfo.setTextNotificationCn(rs.getString("text_notification_cn"));
            custInfo.setTextReleaseNotesCn(rs.getString("text_releasenotes_cn"));
            custInfo.setTextInvestCn(rs.getString("text_invest_cn"));
            return custInfo;
        }
    }

    private static final class BankInfoRowMapper implements RowMapper<BankInfo> {
        public BankInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            BankInfo bankInfo = new BankInfo();
            bankInfo.setId(rs.getLong("id"));
            bankInfo.setUserId(rs.getInt("user_id"));
            bankInfo.setAccountName(rs.getString("account_name"));
            bankInfo.setAccountNumber(rs.getString("account_number"));
            bankInfo.setBankBranchCode(rs.getString("bank_branch_code"));
            bankInfo.setBankBranchName(rs.getString("bank_branch_name"));
            bankInfo.setPaypalEmailAddress(rs.getString("paypal_email_address"));
            return bankInfo;
        }
    }

    private static final class SalaryInfoRowMapper implements RowMapper<SalaryInfo> {
        public SalaryInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            SalaryInfo salaryInfo = new SalaryInfo();
            salaryInfo.setId(rs.getLong("id"));
            salaryInfo.setUserId(rs.getInt("user_id"));
            salaryInfo.setAccountTitle(rs.getString("account_title"));
            salaryInfo.setSalary(rs.getBigDecimal("salary"));
            salaryInfo.setPaymentMethod(rs.getString("payment_method"));
            salaryInfo.setReleaseDate(rs.getDate("release_date"));
            return salaryInfo;
        }
    }

    private static final class ProductsRowMapper implements RowMapper<Products> {
        public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
            Products products = new Products();
            products.setId(rs.getLong("id"));
            products.setUserId(rs.getInt("user_id"));
            products.setProductName(rs.getString("product_name"));
            products.setPrice(rs.getBigDecimal("price"));
            products.setOrders(rs.getInt("orders"));
            products.setStockStatus(rs.getString("stock_status"));
            products.setAmount(rs.getBigDecimal("amount"));
            return products;
        }
    }
}
