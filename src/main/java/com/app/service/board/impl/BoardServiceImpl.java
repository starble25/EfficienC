package com.app.service.board.impl;

import java.io.File;
import java.io.IOException;

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
        
        if (!file.isEmpty()) {
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
}
