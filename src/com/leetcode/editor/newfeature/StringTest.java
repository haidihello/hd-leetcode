package com.leetcode.editor.newfeature;

import java.math.BigDecimal;
import java.util.Date;

public class StringTest {
    public static void main(String[] args) {
        Integer ratio = 51;
        BigDecimal serviceCharge = new BigDecimal(50);
        BigDecimal settlement = new BigDecimal(550);
        BigDecimal transAmt = new BigDecimal(600);

        BigDecimal settlementAmount = settlement.subtract(serviceCharge);
        //（交易金额-结算金额） / 交易金额  >=  超额百分比    不扣费
        BigDecimal ra = (transAmt.subtract(settlementAmount)).divide(transAmt,2,BigDecimal.ROUND_HALF_UP);
        System.out.println(ra);
        if (ra.multiply(new BigDecimal(100)).compareTo(new BigDecimal(ratio))<0) {
            System.out.println("Y");
        }

        Date date = new Date();
        System.out.println(date.toString());
    }

}
