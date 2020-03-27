package com.kevin.usc.test.service;

import com.kevin.usc.test.persistent.jpa.TeacherRepository;
import com.kevin.usc.test.persistent.po.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    private static final Integer pageSize = 10;

    public Page<Teacher> findTeacherPage(Integer current){

        return teacherRepository.findAll(PageRequest.of(current, pageSize));
    }
}
