package com.jnh.jsb.answer;

import com.jnh.jsb.question.Question;
import com.jnh.jsb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String create(@PathVariable("id")int id, @RequestParam("content")String content){
        Question question = questionService.getQuestion(id);
        answerService.create(question, content);
        return "redirect:/question/detail/"+id;
    }
}
