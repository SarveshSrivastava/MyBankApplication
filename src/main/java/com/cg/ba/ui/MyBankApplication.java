package com.cg.ba.ui;

import java.util.*;

import com.cg.ba.beans.Account;
import com.cg.ba.service.RegisterLoginService;
import com.cg.ba.service.RegisterLoginServiceImpl;
import com.cg.ba.service.TransactionService;
import com.cg.ba.service.TransactionServiceImpl;

public class MyBankApplication {
	static Scanner sc=new Scanner(System.in);

/*	public static void loginDetails() {
		System.out.println("enter your account no");
		String accNo=sc.next();
		System.out.println("enter your password");
		String password =sc.next();
	}*/
	static Account account=new Account();
	static RegisterLoginService service=new RegisterLoginServiceImpl();
	static TransactionService transaction=new TransactionServiceImpl();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double bal=0;
		
		System.out.println("enter your choice");
		int ch=sc.nextInt();
		switch(ch)
		{
		case 1: 
		{
			System.out.println("enter your first name");
			account.setfName(sc.next());
			System.out.println("enter your last name");
			account.setlName(sc.next());
			System.out.println("enter your email id");
			account.setEmailId(sc.next());
			System.out.println("enter password");
			account.setPassword(sc.next());
			System.out.println("enter your pancard no");
			account.setPancard(sc.next());
			System.out.println("enter your aadhar card no");
			account.setAadhar(sc.nextLong());
			System.out.println("enter your address");
			account.setAddress(sc.next());
			System.out.println("enter your mobile no");
			account.setMobileNo(sc.nextLong());
			int accNo=service.register();
			System.out.println("Successfully registered\nYour Account No: "+accNo);
		}
		case 2:
		{
			System.out.println("enter your account no");
			long ano=sc.nextLong();
			System.out.println("enter your password");
			String password=sc.next();
			double balance=service.login(ano, password);
			System.out.println("enter your choice");
			int c=sc.nextInt();
			switch(c)
			{
			case 1:
			{
				System.out.println("enter the amount you want to withdraw");
				double withdrawAmount=sc.nextDouble();
				bal=transaction.withdraw(withdrawAmount,ano);
				System.out.println("your balance "+bal);
			}
			case 2:
			{
				System.out.println("enter the amount you want to deposit");
				double depositAmount=sc.nextDouble();
				bal=transaction.deposit(depositAmount,ano);
				System.out.println("amount deposited"+bal);
			}
			case 3:
			{
				bal=transaction.showBalance(ano);
				System.out.println("your balance"+bal);
			}
			case 4:
			{
				System.out.println("enter the account no of receiver");
				transaction.fundTransfer();
			}
			}
		}
		case 3:
		{
			System.exit(0);
		}
		}

	}

}
