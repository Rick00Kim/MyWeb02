package org.waxing.dao;
import static org.waxing.db.JdbcUtils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.waxing.bean.Board;
public class BoardDAO {
	private BoardDAO() {}
	private static BoardDAO instance=new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	public ArrayList<Board> getAllBoard(){
		String sql="select * from board order by board_num desc";
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board temp=new Board();
				temp.setBoard_num(Integer.parseInt(rs.getString("board_num")));
				temp.setUserid(rs.getString("userid"));
				temp.setTitle(rs.getString("title"));
				temp.setWriter(rs.getString("writer"));
				temp.setContent(rs.getString("content"));
				temp.setWriteday(rs.getDate("writeday"));
				list.add(temp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
			if(conn!=null)close(conn);
		}
		return list;
	}
	
	public Board getOneBoard(int board_num) {
		String sql="select * from board where board_num=?";
		Board oneBoard=null;
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board temp=new Board();
				temp.setBoard_num(Integer.parseInt(rs.getString("board_num")));
				temp.setUserid(rs.getString("userid"));
				temp.setTitle(rs.getString("title"));
				temp.setWriter(rs.getString("writer"));
				temp.setContent(rs.getString("content"));
				temp.setWriteday(rs.getDate("writeday"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)close(rs);
			if(pstmt!=null)close(pstmt);
			if(conn!=null)close(conn);
		}
		return oneBoard;
	}
	
	public void insertBoard(Board temp) {
		String sql="insert into board values(board_num_seq,?,?,?,?,sysdate)";
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, temp.getUserid());
			pstmt.setString(2, temp.getTitle());
			pstmt.setString(3, temp.getWriter());
			pstmt.setString(4, temp.getContent());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)close(pstmt);
			if(conn!=null)close(conn);
		}
	}
	
	public void modifyBoard(Board temp) {
		String sql="update board set title=? content=? where board_num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, temp.getTitle());
			pstmt.setString(2, temp.getContent());
			pstmt.setInt(3, temp.getBoard_num());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)close(pstmt);
			if(conn!=null)close(conn);
		}
	}
	public void deleteBoard(int board_num) {
		String sql="delete from board where board_num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)close(pstmt);
			if(conn!=null)close(conn);
		}
	}
	
}