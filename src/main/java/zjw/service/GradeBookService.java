package zjw.service;

import zjw.entity.QueryPageBean;
import zjw.pojo.GradeBook;

import java.util.List;
import java.util.Map;

public interface GradeBookService {
    int insert(GradeBook gradeBook);

    List<GradeBook> selectAll(QueryPageBean queryPageBean);
    List<GradeBook> getAllGradeBook(Map queryParams);
    long getCount(Map queryParams);
    int deleteByPk_id(Integer pk_id);
    GradeBook selByPkid(Integer pk_id);
    int update(GradeBook gradeBook);
}
