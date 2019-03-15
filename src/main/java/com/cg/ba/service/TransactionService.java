package com.cg.ba.service;

public interface TransactionService {
	double withdraw(double withdrawAmount,long accNo);
	double deposit(double depositAmount,long accNo);
	double showBalance(long accNo);
	double fundTransfer(long accNo,long toAccNo,double amountTransfer);
	
}
