package com.spring.blog.springblog.main.service;

import com.spring.blog.springblog.main.entity.Board;
import com.spring.blog.springblog.main.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public void exerciseWrite(Board board, MultipartFile uploadFile) throws Exception {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\webapp\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + uploadFile.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        uploadFile.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

        exerciseRepository.save(board);
    }

    public Page<Board> findAll(Pageable pageable) {

        return exerciseRepository.findAll(pageable);
    }

    public Page<Board> exerciseSearchList(String searchKeyword, Pageable pageable) {

        return exerciseRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public void exerciseDelete(Integer id) {
        exerciseRepository.deleteById(id);
    }

    public void exerciseUpdate(Integer id, Board board) {
        exerciseRepository.save(board).setId(id);
    }

    public Board boardView(Integer id) {
        return exerciseRepository.findById(id).get();
    }

    public String exerciseOrderDesc() {
        return exerciseRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).toString();
    }
}