package com.cg.ba.dao;

import com.cg.ba.beans.Account;

public interface RegisterLoginDAO {
	int register();
	double login(long accNo,String password);

}
