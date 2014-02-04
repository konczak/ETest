package pl.konczak.etest.controller.teacher.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.konczak.etest.bo.IExamBO;

@Controller
@RequestMapping("teacher/exam/{id}/check")
public class ExamCheckController {

    @Autowired
    private IExamBO examBO;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void check(ModelMap model, @PathVariable("id") Integer id) {

        examBO.check(id);
    }
}
