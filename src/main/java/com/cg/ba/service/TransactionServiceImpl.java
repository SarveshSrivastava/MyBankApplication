package com.cg.ba.service;

import com.cg.ba.dao.TransactionDAO;
import com.cg.ba.dao.TransactionDAOImpl;

public class TransactionServiceImpl implements TransactionService {
	TransactionDAO dao=new TransactionDAOImpl();

	public double withdraw(double withdrawAmount,long accNo) {
		// TODO Auto-generated method stub
		return dao.withdraw(withdrawAmount,accNo);
	}

	public double deposit(double depositAmount,long accNo) {
		// TODO Auto-generated method stub
		return dao.deposit(depositAmount,accNo);
	}

	public double showBalance(long accNo) {
		// TODO Auto-generated method stub
		return dao.showBalance(accNo);
	}

	public double fundTransfer() {
		// TODO Auto-generated method stub
		return dao.fundTransfer();
	}

}
