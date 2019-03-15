package com.cg.ba.dao;

import com.cg.ba.beans.Account;

public interface TransactionDAO {
	double withdraw(double withdrawAmount,long accNo,Account account1);
	double deposit(double depositAmount,long accNo,Account account1);
	double showBalance(long accNo);
	double fundTransfer(long accNo,long toAccNo,double amountTransfer);
	
}
