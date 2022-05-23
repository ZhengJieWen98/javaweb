package zjw.service;

import zjw.entity.G_Class;
import zjw.entity.Grade;
import zjw.entity.Major;
import zjw.entity.QueryPageBean;
import zjw.pojo.GradeClass;

import java.util.List;
import java.util.Map;

public interface GradeClassService {

    /**
     * 添加班级信息
     * @param gradeClass
     * @return
     */
    int insert(GradeClass gradeClass);

    /**
     * 修改班级信息
     * @param gradeClass
     * @return
     */
    int update(GradeClass gradeClass);

    /**
     * 根据班级信息主键id删除书籍
     * @param pk_id
     * @return
     */
    int delete(Integer pk_id);

    /**
     * 查询所有的班级信息
     * @return
     */
    List<GradeClass> selectAll(QueryPageBean queryPageBean);

    List<GradeClass> getAllGradeClass(Map queryParams);

    /**
     * 查询班级总数
     * @return
     */
    long getCount(Map queryParams);

    GradeClass selByPk_id(Integer pk_id);

    List<Grade> selAllGrade();

    List<Major> selAllMajor(String grade);

    List<G_Class> selByGradeAndMajor(String grade, String major);
}
