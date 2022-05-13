package zjw.dao;

import zjw.entity.G_Class;
import zjw.entity.Grade;
import zjw.entity.Major;
import zjw.entity.QueryPageBean;
import zjw.pojo.GradeClass;
import zjw.pojo.TextBook;

import java.util.List;
import java.util.Map;

public interface GradeClassDao {

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

    List<GradeClass> getAllGradeClass();

    /**
     * 查询班级总数
     * @return
     */
    long getCount(Map queryParams);

    /**
     * 根据pk_id查询班级信息
     * @param pk_id
     * @return
     */
    GradeClass selByPk_id(Integer pk_id);

    /**
     * 查询年级种数
     * @return
     */
    List<Grade> selAllGrade();

    /**
     * 根据年级来查询专业
     * @param grade
     * @return
     */
    List<Major> selAllMajor(String grade);

    /**
     * 根据年级和专业来查找班级
     * @param grade
     * @param major
     * @return
     */
    List<G_Class> selByGradeAndMajor(String grade,String major);
}
