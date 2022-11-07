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
			String sql = "select * from address order by num desc";
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
	
	//카운트
	public int getCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		int count = 0 ;
		
		try {
			con = getConnection();
			String sql = "select count(*) from address";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return count;
	}
	//상세보기
	public Address getDetail(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
        Address ad  = null;		
		try {
			con =getConnection();
			String sql = "select * from address where num="+num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				 ad = new Address();
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getInt("num"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return ad;
		
	}
	
	//수정
	public void addrUpdate(Address ad) {
		Connection con = null;
		PreparedStatement ps =null;
		
		try {
			con = getConnection();
			String sql="update  address set name=?,  tel=?,"
					+ " zipcode=?, addr=? where num=?";
			ps= con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getTel());
			ps.setString(3, ad.getZipcode());
			ps.setString(4, ad.getAddr());
			ps.setInt(5, ad.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//삭제
	public void addrDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			con =getConnection();
			String sql = "delete from address where num="+num;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, null);
		}
	}
	//우편번호 검색
	public ArrayList<ZipCode>  zipcodeRead(String dong) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ZipCode> zarr = new ArrayList<ZipCode>();
		
		try {
			//select * from zipcode where dong  like '%부전동%';
			con  =getConnection();
			String sql = "select * from zipcode where dong like '%"+dong+"%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ZipCode zip = new ZipCode();
				zip.setBunji(rs.getString("bunji"));
				zip.setDong(rs.getString("dong"));
				zip.setGugun(rs.getString("gugun"));
				zip.setSeq(rs.getInt("seq"));
				zip.setSido(rs.getString("sido"));
				zip.setZipcode(rs.getString("zipcode"));
				zarr.add(zip);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, null);
		}
		return zarr;
		
	}
	
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
