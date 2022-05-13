package zjw.dao;

import zjw.entity.QueryPageBean;
import zjw.pojo.GradeBook;
import zjw.pojo.GradeClass;

import java.util.List;
import java.util.Map;

public interface GradeBookDao {

    int insert(GradeBook gradeBook);

    /**
     * 查询所有的班级信息
     * @return
     */
    List<GradeBook> selectAll(QueryPageBean queryPageBean);

    List<GradeBook> getAllGradeBook(Map queryParams);

    int deleteByPk_id(Integer pk_id);

    long getCount(Map queryParams);

    GradeBook selByPkid(Integer pk_id);

    int update(GradeBook gradeBook);

}
