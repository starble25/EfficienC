package com.app.dao.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dto.board.Board;
@Repository
public class BoardDAO {
	@Autowired
	  private DataSource dataSource;

	    public void setDataSource(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }

	    public void insertBoard(Board board) {
	        String sql = "INSERT INTO board (title, content, file_name, created_at) VALUES (?, ?, ?, SYSDATE)";
	        
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	             
	            ps.setString(1, board.getTitle());
	            ps.setString(2, board.getContent());
	            ps.setString(3, board.getFileName());

	            ps.executeUpdate();	//SQL 실행(데이터 삽입)
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    //게시글 삭제 메서드
	    public void deleteBoard(int id) {
	        String sql = "DELETE FROM board WHERE id = ?";
	        
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            
	            ps.setInt(1, id);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
//	    목록 보여지는 페이지 위해 추가
	    public List<Board> getAllBoards() { // 추가: 게시글 목록 조회 기능
	        List<Board> boardList = new ArrayList<>();
	        String sql = "SELECT id, title, created_at FROM board ORDER BY id DESC";
	        
	        try (Connection conn = dataSource.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Board board = new Board();
	                board.setId(rs.getInt("id"));
	                board.setTitle(rs.getString("title"));
	                board.setCreatedAt(rs.getDate("created_at"));
	                boardList.add(board);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return boardList;
	    }
	    
	}