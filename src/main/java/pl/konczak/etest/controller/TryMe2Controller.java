package pl.konczak.etest.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.konczak.etest.dto.TryMe2Question;

@Controller
public class TryMe2Controller {

    @RequestMapping("tryme2")
    public String tryMe(ModelMap model) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        model.addAttribute("obj", list);

        return "tryme2";
    }

    @RequestMapping(value = "tryme2/tryQuestion",
                    produces = "application/json",
                    method = RequestMethod.GET)
    @ResponseBody
    public TryMe2Question get(@RequestParam("folder") String folder) {
        System.out.println("read folder <" + folder + ">");
        TryMe2Question tryMe2Question = new TryMe2Question(Integer.parseInt(folder), "Question [" + folder + "]", null);

        for (int i = 0; i < 5; i++) {
            tryMe2Question.addAnswer(i, "Answer [" + i + "]", null);
        }


        return tryMe2Question;
    }

    @RequestMapping(value = "tryme2/tryQuestion",
                    consumes = "application/json",
                    method = RequestMethod.POST)
    @ResponseBody
    public void submit(@RequestBody TryMe2Question tryMe2Question) {
        System.out.println("submitted!");
        System.out.println("tryMe2Question.id <" + tryMe2Question.getId() + ">");
        System.out.println("tryMe2Question.question <" + tryMe2Question.getQuestion() + ">");
        System.out.println("tryMe2Question.imageId <" + tryMe2Question.getImageId() + ">");

        for (TryMe2Question.Answer answer : tryMe2Question.getAnswers()) {
            System.out.println("answer.id <" + answer.getId() + ">");
            System.out.println("answer.answer <" + answer.getAnswer() + ">");
            System.out.println("answer.imageid <" + answer.getImageId() + ">");
            System.out.println("answer.correct <" + answer.isCorrect() + ">");
        }
    }
}
