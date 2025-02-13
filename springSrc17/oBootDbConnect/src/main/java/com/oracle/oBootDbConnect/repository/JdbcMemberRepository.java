package com.oracle.oBootDbConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import com.oracle.oBootDbConnect.domain.Member7;

//@Repository
public class JdbcMemberRepository implements MemberRepository {
	//JDBC 사용
	private final DataSource dataSource;
	
	@Autowired
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Connection getConnection() {
		//DataSourceUtil에 의해서 (dataSource -> application.properties에서 가져온 dataSource)
		//DataSourceUtils는 spring framework에서 제공해주는!@
		//메모리를 알아서 최적화 시켜주는!! -> 
		return DataSourceUtils.getConnection(dataSource);
	}
	
	//DB에 insert
	@Override
	public Member7 save(Member7 member7) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO member7(id, name) VALUES(member_seq.nextval,?)";
		System.out.println("JdbcMemberRepository sql->"+sql);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member7.getName());
			
			pstmt.executeUpdate();
			System.out.println("JdbcMemberRepository pstmt.executeUpdate After");
			return member7;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}
	
	
	//DB의 것을 ArrayList를 담아오는 것
	@Override
	public List<Member7> findAll() {
		List<Member7> memList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member7";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member7 m7 = new Member7();
				m7.setId(rs.getLong("id"));
				m7.setName(rs.getString("name"));
				memList.add(m7);
			}
			return memList;
			
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}
	//해제시키는 방법
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (pstmt != null) pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (conn != null) close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//conn close 를 따로 뺀 이유 -> DataSourceUtils을 써서 종료해야하므로!!
	//JDBC 쓸 때 얘를 쓰면, memory 최적화되게 종료된다.
	//지금까지 했던 대로 해도 된다.
	private void close(Connection conn) throws SQLException {
		DataSourceUtils.releaseConnection(conn, dataSource);
	}
	
}
