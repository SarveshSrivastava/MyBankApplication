package com.cg.ba.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDAOImpl implements TransactionDAO {
static double bal=0;
	public double withdraw(double withdrawAmount,long accNo) {
		// TODO Auto-generated method stub
		
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle");
				PreparedStatement preparedStatement=con.prepareStatement("Update customer_details set balance=? where accNo=?");
				ResultSet resultSet=preparedStatement.executeQuery();
				/*while(resultSet.next())
				{
					bal=(resultSet.getInt(10)-withdrawAmount);
					
				}*/
				
				preparedStatement.setDouble(1,(resultSet.getInt(10)-withdrawAmount));
				preparedStatement.setLong(2, accNo);
				preparedStatement.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bal;
			
		
	}

	public double deposit(double depositAmount,long accNo) {
		// TODO Auto-generated method stub
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle");
			PreparedStatement preparedStatement=con.prepareStatement("Update customer_details set balance=? where accNo=?");
			ResultSet resultSet=preparedStatement.executeQuery();
			/*while(resultSet.next())
			{
				bal=(resultSet.getInt(10)-withdrawAmount);
				
			}*/
			
			preparedStatement.setDouble(1,(resultSet.getInt(10)+depositAmount));
			preparedStatement.setLong(2, accNo);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bal;
		
	}

	public double showBalance(long accNo) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","new","oracle");
			PreparedStatement preparedStatement= con.prepareStatement("select balance from customer_details where account_no=?");
			preparedStatement.setLong(1, accNo);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				bal=resultSet.getDouble(10);
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bal;
	}

	public double fundTransfer() {
		// TODO Auto-generated method stub
		
		return 0;
	}

}
