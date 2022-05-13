package zjw.dao;

import lombok.Data;
import org.junit.Test;
import zjw.dao.impl.GradeClassDaoImpl;
import zjw.pojo.GradeClass;
import zjw.utils.TimeUtils;

import java.util.List;

public class GradeClassDaoImplTest {

//    @Test
//    public void insert(){
//        GradeClassDao dao = new GradeClassDaoImpl();
//        GradeClass gradeClass = new GradeClass();
//        gradeClass.setGrade("2019级");
//        gradeClass.setMajor("软件工程");
//        gradeClass.setG_class("3班");
//        gradeClass.setG_count(55);
//        gradeClass.setInstructor("郑洁文");
//        gradeClass.setCreate_time(TimeUtils.getcurrentTime());
//        gradeClass.setModified_time(TimeUtils.getcurrentTime());
//        gradeClass.setInstructor_phone("18782623892");
//        System.out.println(gradeClass);
//        int insert = dao.insert(gradeClass);
//    }


//    @Test
//    public void update(){
//        GradeClassDao dao = new GradeClassDaoImpl();
//        GradeClass gradeClass = new GradeClass();
//        gradeClass.setPk_id(6);
//        gradeClass.setGrade("2019级");
//        gradeClass.setMajor("软件工程");
//        gradeClass.setG_class("1班");
//        gradeClass.setG_count(50);
//        gradeClass.setInstructor("尹强");
//        gradeClass.setModified_time(TimeUtils.getcurrentTime());
//        gradeClass.setInstructor_phone("18782623892");
//        System.out.println(gradeClass);
//        dao.update(gradeClass);
//    }

//    @Test
//    public void delete(){
//        GradeClassDao dao = new GradeClassDaoImpl();
//        dao.delete(11);
//    }

//    @Test
//    public void selectAll(){
//        GradeClassDao dao = new GradeClassDaoImpl();
//        List<GradeClass> gradeClasses = dao.selectAll();
//        for(GradeClass gradeClass:gradeClasses) System.out.println(gradeClass);
//    }


}
