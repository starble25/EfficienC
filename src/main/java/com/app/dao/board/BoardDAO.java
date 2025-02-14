package com.app.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	}