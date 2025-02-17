package com.app.service.board.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.board.BoardDAO;
import com.app.dto.board.Board;
import com.app.service.board.BoardService;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
    private BoardDAO boardDAO;
    
    private static final String UPLOAD_DIR = "C:/upload/"; // 파일 저장 경로

    @Override
    public void addBoard(Board board) {
        MultipartFile file = board.getUploadFile();
        
//        if (!file.isEmpty()) {
//            String fileName = file.getOriginalFilename();
//            File saveFile = new File(UPLOAD_DIR + fileName);
            
//        null 체크추가
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                File saveFile = new File(UPLOAD_DIR + fileName);
            
            try {
                file.transferTo(saveFile);	//파일을 로컬 폴더에 저장
                board.setFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boardDAO.insertBoard(board);	//DB 저장
    }
    
    //DAO의 deleteBoard 메서드 호출하는 메서드 추가
    @Override
    public void deleteBoard(int id) {
        boardDAO.deleteBoard(id);
    }
    
//    목록 조회를 위해
    @Override
    public List<Board> getAllBoards() { 
        return boardDAO.getAllBoards();
    }
}
