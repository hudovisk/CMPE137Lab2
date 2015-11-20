package com.assignment.sjsu.hudoassenco.cmpe137lab2;

/**
 * Created by fernandoabolafio on 11/19/15.
 */
 import java.io.StringWriter;
 import java.math.*;
 import java.util.Calendar;
 import java.util.Date;



public class Mortage {
    private double mHomeValue;
    private double mDownPayment;
    private double mInterestRate;
    private int mTerms;
    private double mPropertyTaxRate;

    public Mortage () {

    }
    public Mortage (double homevalue, double downpayment, double interestrate, int terms, double propertytaxrate) {
        mHomeValue = homevalue;
        mDownPayment = downpayment;
        mInterestRate = interestrate/100;
        mTerms = terms*12;
        mPropertyTaxRate = propertytaxrate/100;
    }
    //a. Monthly payment amount b. Total interest paid c. Total property tax paid d. Pay off date
    public void setHomeValue (double homevalue){
        mHomeValue = homevalue;
    }
    public void setDownPayment (double downpayment){
        mDownPayment = downpayment;
    }
    public void setInterestRate (double interestrate){
        mInterestRate = interestrate;
    }
    public void setTerms (int terms){
        mTerms = terms*12;
    }
    public void setPropertyTaxRate (double propertyTaxRate){
        mPropertyTaxRate = propertyTaxRate;
    }
    public double getMonthlyPayment () {
        double constantA = getTotalA();
        double rate = getTotalRates();
        double monthlyPayment =getAmount()/12;
        return monthlyPayment;
    }
    public double getTotalInterestPaid (){
        double constantA = getInterestRateA();
        double totalInterestePaid =( getInitialAmount()*constantA ) - getInitialAmount();
        return totalInterestePaid;
    }
    public double getTotalPropertyTaxPaid () {

        double totalPropertyTaxPaid = getAmount()-(getInitialAmount()+getTotalInterestPaid());
        return totalPropertyTaxPaid;
    }

    public String getPayOffDate () {

        int month =  Calendar.MONTH;
        int year = Calendar.YEAR;
        int payOffMonth;
        int payOffYear;
        if ((mTerms - month)<=12){
            payOffMonth = mTerms - month;
            payOffYear = year+1;
        }else{
            payOffMonth =( mTerms - month )%12;
            payOffYear = ((mTerms - month)/12)+year;
        }
        return (payOffMonth +"/"+payOffYear);

    }
    private double getAmount (){
        return getInitialAmount()*getTotalA();
    }
    private double getInitialAmount () {
        double principalAmount = mHomeValue-mDownPayment;
        return principalAmount;
    }

    private double getTotalA () {
        double a = Math.pow((1+getTotalRates()),mTerms);
        return a;
    }
    private double getPropertyTaxA () {
        double a = Math.pow((1+mPropertyTaxRate),mTerms);
        return a;
    }
    private double getInterestRateA() {
        double a = Math.pow((1+mInterestRate),mTerms);
        return a;
    }
    private double getTotalRates () {
        double totalRate = mInterestRate + mPropertyTaxRate;
        return totalRate;
    }
    private double getTotalPaid () {
        return getMonthlyPayment()*mTerms;

    }


}
