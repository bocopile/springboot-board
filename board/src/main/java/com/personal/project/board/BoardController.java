package com.personal.project.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.project.user.User;
import com.personal.project.user.UserSingleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardController {
	
	@Autowired
	BoardRepository boardMapper;
	
	@PutMapping("/board/save")
	public ResponseEntity<Board> save(@RequestBody Board board) throws Exception{
		
		User user = UserSingleton.getInstance();		
		
		board.setCreator(user);

		boardMapper.save(board);
		
		return new ResponseEntity<>(board, HttpStatus.OK);
		
	}
	@PutMapping("/board/update")
	public ResponseEntity<Board> update(@RequestBody Board board) throws Exception{
		
		User user = UserSingleton.getInstance();
		
		Optional<Board> boardOptional = boardMapper.findById(board.getNo());
		boardOptional.orElseThrow(() ->new Exception("해당 정보를 조회할수 없습니다."));				
		Board boardResult = boardOptional.get();
		
		boardResult.setModifier(user);
		boardResult.setTitle(board.getTitle());
		boardResult.setContents(board.getContents());
		
		boardMapper.save(boardResult);
		
		return new ResponseEntity<>(boardResult, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/board/delete")
	public ResponseEntity<Board> delete(@RequestBody Integer no) throws Exception{
		
		User user = UserSingleton.getInstance();
	
		Optional<Board> boardOptional = boardMapper.findById(no);
		boardOptional.orElseThrow(() ->new Exception("해당 정보를 조회할수 없습니다."));
				
		Board boardResult = boardOptional.get();
		boardResult.setDelYn("Y");
		boardResult.setModifier(user);
		
		boardMapper.save(boardResult);
		
		return new ResponseEntity<>(boardResult, HttpStatus.OK);		
		
	}
	
	


}
