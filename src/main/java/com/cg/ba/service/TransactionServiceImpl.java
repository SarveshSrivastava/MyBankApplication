package com.cg.ba.service;

import com.cg.ba.beans.Account;
import com.cg.ba.dao.TransactionDAO;
import com.cg.ba.dao.TransactionDAOImpl;

public class TransactionServiceImpl implements TransactionService {
	TransactionDAO dao=new TransactionDAOImpl();
	public void validateMobile(Account account)
	{
		long mobile=account.getMobileNo();
		String s=String.valueOf(mobile);
		if(s.length()!=10)
		{
			System.out.println("invalid mobile no");
		}
	}

	public double withdraw(double withdrawAmount,long accNo,Account account1) {
		// TODO Auto-generated method stub
		return dao.withdraw(withdrawAmount,accNo,account1);
	}

	public double deposit(double depositAmount,long accNo,Account account1) {
		// TODO Auto-generated method stub
		return dao.deposit(depositAmount,accNo,account1);
	}

	public double showBalance(long accNo) {
		// TODO Auto-generated method stub
		return dao.showBalance(accNo);
	}

	public double fundTransfer(long accNo,long toAccNo,double amountTransfer) {
		// TODO Auto-generated method stub
		return dao.fundTransfer(accNo,toAccNo,amountTransfer);
	}

}
