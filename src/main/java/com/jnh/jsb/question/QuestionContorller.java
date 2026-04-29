package com.jnh.jsb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionContorller {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String showList(Model model){
        List<Question> questionList = questionService.getList();
        model.addAttribute(questionList);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Integer id, Model model){
        Question question = questionService.getQuestion(id);
        model.addAttribute(question);

        return "question_detail";
    }
}
