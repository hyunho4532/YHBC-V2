package com.spring.blog.springblog.main;

import com.spring.blog.springblog.main.entity.Board;
import com.spring.blog.springblog.main.service.ExerciseService;
import com.spring.blog.springblog.main.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private FoodService foodService;

    @GetMapping("/board/index")
    public String index() {
        return "index";
    }

    @GetMapping("/board/exercise/list")
    public String exercise(Model model,
                           @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           String searchKeyword) {

        Page<Board> list;

        if (searchKeyword == null) {
            list = exerciseService.findAll(pageable);
        } else {
            list = exerciseService.exerciseSearchList(searchKeyword, pageable);
        }


        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board-exercise-list";
    }

    @PostMapping("/board/exercise/write")
    public String exerciseWriteForm(Board board, MultipartFile uploadFile) throws Exception {

        exerciseService.exerciseWrite(board, uploadFile);

        return "redirect:/board/exercise/list";
    }

    @GetMapping("/board/exercise/delete")
    public String exerciseDelete(Integer id) {
        exerciseService.exerciseDelete(id);

        return "redirect:/board/exercise/list";
    }

    @GetMapping("/board/exercise/update")
    public String exerciseUpdate(Integer id, Model model) {

        model.addAttribute("exercise", exerciseService.boardView(id));

        return "/mustache/board-exercise-update";
    }

    @PostMapping("/board/exercise/update/success/{id}")
    public String exerciseUpdateSuccess(@PathVariable("id") Integer id, Board board, MultipartFile uploadFile) throws Exception {

        Board boardTemp = exerciseService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardTemp.setName(board.getName());

        exerciseService.exerciseWrite(boardTemp, uploadFile);

        return "redirect:/board/exercise/list";
    }

    @GetMapping("/board/exercise/desc")
    public String exerciseDESC(Model model) {

        model.addAttribute("list", exerciseService.exerciseOrderDesc());

        return "redirect:/board/exercise/list";
    }

    @GetMapping("/board/food/desc")
    public String foodDESC(Model model) {

        model.addAttribute("foodList", foodService.findAll());

        return "redirect:/board/food/desc";
    }
}