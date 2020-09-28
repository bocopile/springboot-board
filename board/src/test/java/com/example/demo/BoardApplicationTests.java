package com.example.demo;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.personal.project.board.Board;
import com.personal.project.board.BoardRepository;
import com.personal.project.user.User;
import com.personal.project.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BoardApplicationTests {
	
	@Autowired
	BoardRepository boardMapper;
	
	@Autowired
	UserRepository userMapper;

	@Test
	public void boardList() throws Exception {
		
		boardMapper.findAll().forEach(item -> log.info("{}", item));
	}
	
	@Test
	public void boardListByPaging() throws Exception{
		
		Pageable pageable = PageRequest.of(0, 10);
		
		boardMapper.findByDelYn(pageable, "N").forEach(item -> log.info("{}", item));
	}
	
	@Test
	public void boardView() throws Exception {
		
		Optional<Board> boardOptional = boardMapper.findById(1);
		boardOptional.orElseThrow(() -> new Exception("해당 정보를 찾을수 없습니다."));
		
	}
	@Test
	public void boardSave() throws Exception {
		
		Optional<User> userOptional = userMapper.findById(1);
		userOptional.orElseThrow(() -> new Exception("해당 유저의 정보를 찾을수 없습니다."));
		
		Board board = new Board();
		
		board.setCreator(userOptional.get());
		board.setDelYn("N");
		board.setTitle("공지사항 제목");
		board.setContents("공지사항에 해당 되는 내용입니다.");
		
		boardMapper.save(board);
		
	}
	
	@Test
	public void boardUpdate() throws Exception {
		Optional<User> userOptional = userMapper.findById(1);
		userOptional.orElseThrow(() -> new Exception("해당 유저의 정보를 찾을수 없습니다."));
		
		Optional<Board> boardOptional = boardMapper.findById(1);
		boardOptional.orElseThrow(() -> new Exception("해당 게시판의 정보를 찾을수 없습니다."));
		
		Board boardResult = boardOptional.get();
		
		boardResult.setModifier(userOptional.get());
		boardResult.setTitle("공지사항 제목2");
		boardResult.setContents("공지사항2에 해당 되는 내용입니다.");
		boardResult.setNo(1);
		
		boardMapper.save(boardResult);
		
	}
	

	

}
