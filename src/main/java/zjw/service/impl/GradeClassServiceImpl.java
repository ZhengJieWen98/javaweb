package zjw.service.impl;

import zjw.dao.GradeClassDao;
import zjw.dao.impl.GradeClassDaoImpl;
import zjw.entity.G_Class;
import zjw.entity.Grade;
import zjw.entity.Major;
import zjw.entity.QueryPageBean;
import zjw.pojo.GradeClass;
import zjw.pojo.TextBook;
import zjw.service.GradeClassService;
import zjw.utils.TimeUtils;

import java.util.List;
import java.util.Map;

public class GradeClassServiceImpl implements GradeClassService {
    private GradeClassDao dao = new GradeClassDaoImpl();

    @Override
    public int insert(GradeClass gradeClass) {
        gradeClass.setCreate_time(TimeUtils.getcurrentTime());
        gradeClass.setModified_time(TimeUtils.getcurrentTime());
        int insert = dao.insert(gradeClass);
        if(insert==1){
            return insert;
        }else {
            throw new RuntimeException("添加班级信息失败");
        }
    }

    @Override
    public int update(GradeClass gradeClass) {
        gradeClass.setModified_time(TimeUtils.getcurrentTime());
        int update = dao.update(gradeClass);
        if(update==1){
            return update;
        }else {
            throw new RuntimeException("更新班级信息失败");
        }
    }

    @Override
    public int delete(Integer pk_id) {
        int delete = dao.delete(pk_id);
        if(delete==1){
            return delete;
        }else {
            throw new RuntimeException("删除班级信息失败");
        }
    }

    @Override
    public List<GradeClass> selectAll(QueryPageBean queryPageBean) {
        List<GradeClass> list = dao.selectAll(queryPageBean);
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("还未添加班级信息!");
        }
    }

    @Override
    public List<GradeClass> getAllGradeClass() {
        List<GradeClass> list = dao.getAllGradeClass();
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("还未有教材书籍信息!");
        }
    }

    @Override
    public long getCount(Map queryParams) {
        return dao.getCount(queryParams);
    }

    @Override
    public GradeClass selByPk_id(Integer pk_id) {
        GradeClass gradeClass = dao.selByPk_id(pk_id);
        if(gradeClass!=null){
            return gradeClass;
        }else{
            throw new RuntimeException("没有存在相关教材书籍");
        }
    }

    @Override
    public List<Grade> selAllGrade() {
        List<Grade> list = dao.selAllGrade();
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("还未添加年级");
        }
    }

    @Override
    public List<Major> selAllMajor(String grade) {
        List<Major> list = dao.selAllMajor(grade);
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("还未添加专业");
        }
    }

    @Override
    public List<G_Class> selByGradeAndMajor(String grade, String major) {
        List<G_Class> list = dao.selByGradeAndMajor(grade,major);
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("没有相关班级");
        }
    }
}
