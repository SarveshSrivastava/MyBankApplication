package com.cg.ba.dao;

public interface TransactionDAO {
	double withdraw(double withdrawAmount,long accNo);
	double deposit(double depositAmount,long accNo);
	double showBalance(long accNo);
	double fundTransfer();
	
}
