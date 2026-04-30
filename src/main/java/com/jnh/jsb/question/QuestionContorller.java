package com.jnh.jsb.question;

import com.jnh.jsb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    public String showDetail(@PathVariable("id") Integer id, Model model, AnswerForm answerForm){
        Question question = questionService.getQuestion(id);
        model.addAttribute(question);

        return "question_detail";
    }

    @GetMapping("/create")
    public String showWirte(QuestionForm questionForm){
        return "question_write";
    }

    @PostMapping("create")
    public String doWrite(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_write";
        }
        Question question = questionService.create(questionForm.getSubject(), questionForm.getContent());

        return "redirect:/question/detail/"+question.getId();
    }
}
