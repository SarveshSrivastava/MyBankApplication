package com.cg.ba.ui;

import java.util.*;

import com.cg.ba.beans.Account;
import com.cg.ba.service.RegisterLoginService;
import com.cg.ba.service.RegisterLoginServiceImpl;
import com.cg.ba.service.TransactionService;
import com.cg.ba.service.TransactionServiceImpl;

public class MyBankApplication {
	static Scanner sc=new Scanner(System.in);

	static Account account=new Account();
	//static Account account1=null;
	static RegisterLoginService service=new RegisterLoginServiceImpl();
	static TransactionService transaction=new TransactionServiceImpl();
	static TransactionServiceImpl serviceImpl=new TransactionServiceImpl();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double bal=0;
		
		System.out.println("enter your choice\n1.Register\n2.Login\n3.Exit");
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
			account.setBalance(0);
			serviceImpl.validateMobile(account);
			int accNo=service.register(account);
			System.out.println("Successfully registered\nYour Account No: "+accNo);
			break;
		}
		case 2:
		{
			System.out.println("enter your account no");
			long ano=sc.nextLong();
			System.out.println("enter your password");
			String password=sc.next();
			Account account1=service.login(ano, password);
			
			System.out.println("enter your choice\n1.Withdraw\n2.Deposit\n3.Show Balance\n4.Fund Transfer");
			int c=sc.nextInt();
			switch(c)
			{
			case 1:
			{
				System.out.println("enter the amount you want to withdraw");
				double withdrawAmount=sc.nextDouble();
				bal=transaction.withdraw(withdrawAmount,ano,account1);
				System.out.println("your balance "+bal);
				break;
			}
			case 2:
			{
				System.out.println("enter the amount you want to deposit");
				double depositAmount=sc.nextDouble();
				bal=transaction.deposit(depositAmount,ano,account1);
				System.out.println("amount deposited\n your balance is "+bal);
				break;
			}
			case 3:
			{
				bal=transaction.showBalance(ano);
				System.out.println("your balance is "+bal);
				break;
			}
			case 4:
			{
				System.out.println("enter the account no of receiver");
				long toAcno=sc.nextLong();
				System.out.println("enter amount you want to transfer");
				double amountTransfer=sc.nextDouble();
				bal=transaction.fundTransfer(ano,toAcno,amountTransfer);
				System.out.println("your balance is "+bal);
				break;
			}
			case 5:
			{
				System.exit(0);
				break;
			}
			default:
			{
				System.out.println("invalid input");
			}
			}
			break;
			
		}
		case 3:
		{
			System.exit(0);
			break;
		}
		default:
		{
			System.out.println("invalid input");
		}
		}
	
	}

}
