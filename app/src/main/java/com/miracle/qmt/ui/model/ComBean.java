package com.miracle.qmt.ui.model;

/**
 * 佣金的
 * Created by ZhouChao on 2017/9/6.
 */

public class ComBean {

    /**
     * result : 0
     * info : {"rebateSum":0,"withdrawSum":0,"balance":0}
     */

    private int result;
    private InfoBean info;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * rebateSum : 0
         * withdrawSum : 0
         * balance : 0
         */

        private int rebateSum;
        private int withdrawSum;
        private int balance;

        public int getRebateSum() {
            return rebateSum;
        }

        public void setRebateSum(int rebateSum) {
            this.rebateSum = rebateSum;
        }

        public int getWithdrawSum() {
            return withdrawSum;
        }

        public void setWithdrawSum(int withdrawSum) {
            this.withdrawSum = withdrawSum;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}
