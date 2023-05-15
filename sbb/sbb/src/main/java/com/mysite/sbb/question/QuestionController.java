package com.mysite.sbb.question;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.mysite.sbb.answer.AnswerForm;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

//    @GetMapping("/question/list")
//    public String list(Model model) {
//        List<question> questionList = this.questionRepository.findAll();
//        model.addAttribute("questionList", questionList);
//        return "question_list";
//    }

    @GetMapping("/list") // @GetMapping("/question/list") 동일
    public String list(Model model) {
        List<question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

//    @GetMapping(value = "/detail/{id}") // @GetMapping(value = "/question/detail/{id}") 동일
//    public String detail(Model model, @PathVariable("id") Integer id) {
//        question question = this.questionService.getQuestion(id);
//        model.addAttribute("question", question);
//        return "question_detail";
//    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }



    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

//    @PostMapping("/create")
//    public String questionCreate(@RequestParam String title, @RequestParam String contents) {
//        this.questionService.create(title, contents);
//        return "redirect:/question/list";
//    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getTitle(), questionForm.getContents());
        return "redirect:/question/list";
    }


}