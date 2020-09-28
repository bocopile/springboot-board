package com.personal.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personal.project.board.Board;
import com.personal.project.board.BoardRepository;
import com.personal.project.user.User;
import com.personal.project.user.UserRepository;

@SpringBootApplication
public class BoardApplication implements CommandLineRunner {

	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	UserRepository userRepository;
	
   public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {

		User user = new User("admin","$2a$10$wHwGLfY9.GAetCJM7252i.l4kllJKdYs7wLJEy5vQ481B5VBbAAVS","사용자");
		
		userRepository.save(user);

		
		List<Board> boardList = new ArrayList<>();
		
		boardList.add(new Board("제목1","게시판1",user));
		boardList.add(new Board("제목2","게시판2",user));
		boardList.add(new Board("제목3","게시판3",user));
		boardList.add(new Board("제목4","게시판4",user));
		boardList.add(new Board("제목1","게시판1",user));
		boardList.add(new Board("제목2","게시판2",user));
		boardList.add(new Board("제목3","게시판3",user));
		boardList.add(new Board("제목4","게시판4",user));
		boardList.add(new Board("제목1","게시판1",user));
		boardList.add(new Board("제목2","게시판2",user));
		boardList.add(new Board("제목3","게시판3",user));
		boardList.add(new Board("제목4","게시판4",user));
		boardList.add(new Board("제목1","게시판1",user));
		boardList.add(new Board("제목2","게시판2",user));
		boardList.add(new Board("제목3","게시판3",user));
		boardList.add(new Board("제목4","게시판4",user));
		

		boardRepository.saveAll(boardList);

		
	}


	

}
