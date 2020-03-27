package com.kevin.usc.test.persistent.jpa;

import com.kevin.usc.test.persistent.po.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherRepository extends JpaRepository<Teacher,Long>, JpaSpecificationExecutor<Teacher> {


}
