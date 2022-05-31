package com.travel.board;

import com.travel.domain.Board;
import com.travel.user.UserService;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/list")
  String communityMapping(Model model,Pageable pageable) {
    Page<Board> p = boardService.findBoardList(pageable, "idx");
    model.addAttribute("boardList", p);
    return "board/board";
  }

  //후기 작성페이지 이동
  @GetMapping("/write")
  public String communityWriteMapping() {
    return "board/board-write";
  }

  //게시판 작성
  @ResponseBody
  @PostMapping("/create")
  public Board boardCreate(@RequestBody Board board) {
    boardService.boardCreate(board);
    return board;
  }


}