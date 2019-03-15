package com.cg.ba.service;

import com.cg.ba.beans.Account;

public interface TransactionService {
	double withdraw(double withdrawAmount,long accNo,Account account1);
	double deposit(double depositAmount,long accNo,Account account1);
	double showBalance(long accNo);
	double fundTransfer(long accNo,long toAccNo,double amountTransfer);
	
}
