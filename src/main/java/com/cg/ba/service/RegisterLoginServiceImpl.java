package com.cg.ba.service;



import com.cg.ba.beans.Account;
import com.cg.ba.dao.RegisterLoginDAO;
import com.cg.ba.dao.RegisterLoginDAOImpl;



public class RegisterLoginServiceImpl implements RegisterLoginService {
	RegisterLoginDAO dao=new RegisterLoginDAOImpl();
	

	public int register(Account account) {
		return dao.register(account);
		// TODO Auto-generated method stub
			}

	public Account login(long accNo,String password) {
		// TODO Auto-generated method stub
		return dao.login(accNo, password);
	}

}
