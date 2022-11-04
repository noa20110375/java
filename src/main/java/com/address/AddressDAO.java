package com.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AddressDAO {
	private static AddressDAO instance = new AddressDAO();
	public static AddressDAO getInstance() {
		return instance;
	}
	//디비연결
	private  Connection getConnection() throws Exception {
		Context  initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource  ds = (DataSource)envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	
	
	// 추가
	public void addrInsert(Address  ad) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			// 홍길동   111 부산 010-1111-2222
			String sql = "insert into address(num,name, zipcode, addr, tel) "
					+ " values(address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2,ad.getZipcode());
			ps.setString(3,  ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();  
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,ps, null, null);
		}
	}

	//전체보기
	public ArrayList<Address> addressList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Address> arr = new ArrayList<Address>();
		
		try {
			con =getConnection();
			String sql = "select * from address";
			st = con.createStatement();
			 rs = st.executeQuery(sql);
			 while(rs.next()) {
				 Address ad = new Address();
				 ad.setNum(rs.getInt("num"));
				 ad.setAddr(rs.getString("addr"));
				 ad.setName(rs.getString("name"));
				 ad.setTel(rs.getString("tel"));
				 ad.setZipcode(rs.getString("zipcode"));
				 arr.add(ad);
			 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}
	
	//수정
	
	//삭제
	
	private void closeConnection(Connection con, PreparedStatement ps,
			Statement st, ResultSet rs) {
			try {
				if(con !=null) 			con.close();
				if(ps !=null) 				ps.close();
				if(st !=null) 				st.close();
				if(rs !=null) 				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	

}
