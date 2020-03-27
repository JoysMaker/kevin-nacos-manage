package com.kevin.usc.test.mvc;

import com.kevin.usc.base.bean.WResponse;
import com.kevin.usc.test.persistent.po.Teacher;
import com.kevin.usc.test.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

     @Autowired
     private TeacherService teacherService;



    @GetMapping("getTeacher/page")
    public WResponse<Page<Teacher>> findTeacherByPage(@RequestParam(required = true, defaultValue = "0") Integer page){

        return WResponse.offSuccess(teacherService.findTeacherPage(page));
    }
}
