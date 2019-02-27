package com.mayao.laotian;

import java.util.List;

/**
 * @Description:
 * @author: mayao
 * @date: 2018-12-29 10:09
 */
public class Meta {


    /**
     * code : 0
     * data : [{"balance":1000,"accountType":"CM","accountNo":6101970551811228,"dailyLimit":1000000,"orderLimit":100000,"payRank":0,"feeRate":8500,"memberId":227361592,"status":"N"}]
     * success : true
     * message : OK
     */
    private int code;
    private List<DataEntity> data;
    private boolean success;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public class DataEntity {
        /**
         * balance : 1000
         * accountType : CM
         * accountNo : 6101970551811228
         * dailyLimit : 1000000
         * orderLimit : 100000
         * payRank : 0
         * feeRate : 8500
         * memberId : 227361592
         * status : N
         */
        private int balance;
        private String accountType;
        private long accountNo;
        private int dailyLimit;
        private int orderLimit;
        private int payRank;
        private int feeRate;
        private int memberId;
        private String status;

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public void setAccountNo(long accountNo) {
            this.accountNo = accountNo;
        }

        public void setDailyLimit(int dailyLimit) {
            this.dailyLimit = dailyLimit;
        }

        public void setOrderLimit(int orderLimit) {
            this.orderLimit = orderLimit;
        }

        public void setPayRank(int payRank) {
            this.payRank = payRank;
        }

        public void setFeeRate(int feeRate) {
            this.feeRate = feeRate;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getBalance() {
            return balance;
        }

        public String getAccountType() {
            return accountType;
        }

        public long getAccountNo() {
            return accountNo;
        }

        public int getDailyLimit() {
            return dailyLimit;
        }

        public int getOrderLimit() {
            return orderLimit;
        }

        public int getPayRank() {
            return payRank;
        }

        public int getFeeRate() {
            return feeRate;
        }

        public int getMemberId() {
            return memberId;
        }

        public String getStatus() {
            return status;
        }
    }
}
