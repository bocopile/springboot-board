package com.personal.project.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardViewController {
	
	@Autowired
	BoardRepository boardMapper;
	
	@GetMapping("/board/list")
	public String boardList(Model model,@PageableDefault(sort = "no", direction = Sort.Direction.DESC, size = 10)Pageable pageable) {
				
		Page<Board> boardPage = boardMapper.findByDelYn(pageable, "N");  
		model.addAttribute("list", boardPage);
		
		return "board/list";
	}
	
	@GetMapping("/board/view")
	public String boardView(Model model, Board board) throws Exception {
		
		Optional<Board> boardOptional = boardMapper.findById(board.getNo());
		boardOptional.orElseThrow(()-> new Exception("해당 정보를 찾을수 없습니다."));
		
		model.addAttribute("board", boardOptional.get());
		
		return "board/view";
			
	}
	
	@GetMapping("/board/edit")
	public String boardEdit(Model model, Board board) throws Exception{
		
		Optional<Board> boardOptional = boardMapper.findById(board.getNo());
		boardOptional.orElseThrow(()-> new Exception("해당 정보를 찾을수 없습니다."));
		
		model.addAttribute("board", boardOptional.get());
		
		return "board/edit";
	}
	
	@GetMapping("/board/write")
	public String boardEdit(Model model){
		
		return "board/write";
	}

}
