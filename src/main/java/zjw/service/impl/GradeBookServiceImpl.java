package zjw.service.impl;

import zjw.dao.GradeBookDao;
import zjw.dao.impl.GradeBookDaoImpl;
import zjw.entity.QueryPageBean;
import zjw.pojo.GradeBook;
import zjw.pojo.GradeClass;
import zjw.service.GradeBookService;
import zjw.utils.TimeUtils;

import java.util.List;
import java.util.Map;

public class GradeBookServiceImpl implements GradeBookService {
    private GradeBookDao dao = new GradeBookDaoImpl();
    @Override
    public int insert(GradeBook gradeBook) {

        gradeBook.setCreate_time(TimeUtils.getcurrentTime());
        gradeBook.setModified_time(TimeUtils.getcurrentTime());

        int insert = dao.insert(gradeBook);
        if(insert==1){
            return insert;
        }else{
            throw new RuntimeException("添加班级教材失败");
        }

    }

    @Override
    public List<GradeBook> selectAll(QueryPageBean queryPageBean) {
        List<GradeBook> list = dao.selectAll(queryPageBean);
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("没有该班级教材信息!");
        }
    }

    @Override
    public List<GradeBook> getAllGradeBook(Map queryParams) {
        List<GradeBook> list = dao.getAllGradeBook(queryParams);
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("还未有班级教材信息!");
        }
    }

    @Override
    public long getCount(Map queryParams) {
        return dao.getCount(queryParams);
    }

    @Override
    public int deleteByPk_id(Integer pk_id) {
        int delete = dao.deleteByPk_id(pk_id);
        if(delete==1){
            return delete;
        }else {
            throw new RuntimeException("删除班级教材信息失败");
        }
    }

    @Override
    public GradeBook selByPkid(Integer pk_id) {
        GradeBook gradeBook = dao.selByPkid(pk_id);
        if(gradeBook!=null){
            return gradeBook;
        }else{
            throw new RuntimeException("没有存在相关班级教材信息");
        }
    }

    @Override
    public int update(GradeBook gradeBook) {
        gradeBook.setModified_time(TimeUtils.getcurrentTime());
        int update = dao.update(gradeBook);
        if(update==1){
            return update;
        }else {
            throw new RuntimeException("更新班级教材信息失败");
        }
    }
}
