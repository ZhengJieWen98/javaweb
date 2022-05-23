package zjw.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import zjw.dao.GradeBookDao;
import zjw.entity.QueryPageBean;
import zjw.pojo.GradeBook;
import zjw.pojo.GradeClass;
import zjw.utils.C3P0Utils;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GradeBookDaoImpl implements GradeBookDao {
    @Override
    public int insert(GradeBook gradeBook) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用update方法
        String sql = "insert into grade_book values(?,?,?,?,?,?,?,?,?)";
        int rows = 0;
        try {
            rows = qr.update(sql,
                    null,
                    gradeBook.getGrade(),
                    gradeBook.getMajor(),
                    gradeBook.getG_class(),
                    gradeBook.getBookName(),
                    gradeBook.getBookAuthor(),
                    gradeBook.getBookPrice(),
                    gradeBook.getCreate_time(),
                    gradeBook.getModified_time());
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("添加班级教材异常");
        }
    }

    @Override
    public List<GradeBook> selectAll(QueryPageBean queryPageBean) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        Map queryParams = queryPageBean.getQueryParams();
        List<GradeBook> list = null;
        try {
            if(queryParams==null){
                String sql = "select * from grade_book limit ?,?";
                list = qr.query(sql, new BeanListHandler<>(GradeBook.class),queryPageBean.getOffset(),queryPageBean.getPageSize());
            }else if(queryParams.size()==3){
                String sql="SELECT * FROM grade_book where grade=? and major=? and g_class=? limit ?,?";
                String grade = (String) queryParams.get("grade");
                String major = (String) queryParams.get("major");
                String g_class = (String) queryParams.get("g_class");
                list = qr.query(sql, new BeanListHandler<>(GradeBook.class),grade,major,g_class,queryPageBean.getOffset(),queryPageBean.getPageSize());
            }else if(queryParams.size()==2){
                String sql = "select * from grade_book where ";
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                String[] arr = new String[2];
                int index=0;
                while (iterator.hasNext()){
                    if(index==0){
                        next = (String) iterator.next();
                        arr[index++]=next;
                        sql+=next+" like ? and ";
                    }else{
                        next = (String) iterator.next();
                        arr[index]=next;
                        sql+=next+" like ? limit ?,?";
                    }
                }
                System.out.println(sql);
                list = qr.query(sql, new BeanListHandler<>(GradeBook.class),queryParams.get(arr[0]),queryParams.get(arr[1]),queryPageBean.getOffset(),queryPageBean.getPageSize());
            }else if(queryParams.size()==1){
                String sql = "select * from grade_book where ";
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                while (iterator.hasNext()){
                    next = (String) iterator.next();
                    sql+=next;
                }
                sql+=" like ? limit ?,?";
                list = qr.query(sql, new BeanListHandler<>(GradeBook.class),queryParams.get(next),queryPageBean.getOffset(),queryPageBean.getPageSize());
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询所有班级教材信息异常");
        }
    }

    @Override
    public List<GradeBook> getAllGradeBook(Map queryParams) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        List<GradeBook> list = null;
        try {
            if(queryParams==null){
                String sql = "select * from grade_book";
                list = qr.query(sql, new BeanListHandler<>(GradeBook.class));
            }else if(queryParams.size()==3){
                String sql="SELECT * FROM grade_book where grade=? and major=? and g_class=?";
                String grade = (String) queryParams.get("grade");
                String major = (String) queryParams.get("major");
                String g_class = (String) queryParams.get("g_class");
                list = qr.query(sql, new BeanListHandler<>(GradeBook.class),grade,major,g_class);
            }else if(queryParams.size()==2){
                String sql = "select * from grade_book where ";
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                String[] arr = new String[2];
                int index=0;
                while (iterator.hasNext()){
                    if(index==0){
                        next = (String) iterator.next();
                        arr[index++]=next;
                        sql+=next+" like ? and ";
                    }else{
                        next = (String) iterator.next();
                        arr[index]=next;
                        sql+=next+" like ?";
                    }
                }
                System.out.println(sql);
                list = qr.query(sql, new BeanListHandler<>(GradeBook.class),queryParams.get(arr[0]),queryParams.get(arr[1]));
            }else if(queryParams.size()==1){
                String sql = "select * from grade_book where ";
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                while (iterator.hasNext()){
                    next = (String) iterator.next();
                    sql+=next;
                }
                sql+=" like ?";
                list = qr.query(sql, new BeanListHandler<>(GradeBook.class),queryParams.get(next));
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("根据条件查询班级异常");
        }
    }

    @Override
    public int deleteByPk_id(Integer pk_id) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用update方法
        String sql = "delete from grade_book where pk_id = ?";
        int rows = 0;
        try {
            rows = qr.update(sql, pk_id);
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("删除班级教材信息异常");
        }
    }

    @Override
    public long getCount(Map queryParams) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        try {
            Long count=null;
            if(queryParams==null){
                String sql = "select count(*) from grade_book";
                count = (Long) qr.query(sql, new ScalarHandler());
            }else if(queryParams.size()==3){
                String sql="SELECT count(*) FROM grade_book where grade=? and major=? and g_class=?";
                String grade = (String) queryParams.get("grade");
                String major = (String) queryParams.get("major");
                String g_class = (String) queryParams.get("g_class");
                count = (Long) qr.query(sql, new ScalarHandler(),grade,major,g_class);
            }else if(queryParams.size()==2){
                String sql = "select count(*) from grade_book where ";
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                String[] arr = new String[2];
                int index=0;
                while (iterator.hasNext()){
                    if(index==0){
                        next = (String) iterator.next();
                        arr[index++]=next;
                        sql+=next+" like ? and ";
                    }else{
                        next = (String) iterator.next();
                        arr[index]=next;
                        sql+=next+" like ?";
                    }
                }
                System.out.println(sql);
                System.out.println(queryParams);

                count = (Long) qr.query(sql, new ScalarHandler(),queryParams.get(arr[0]),queryParams.get(arr[1]));
            }else if(queryParams.size()==1){
                String sql = "select count(*) from grade_book where ";
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                while (iterator.hasNext()){
                    next = (String) iterator.next();
                    sql+=next;
                }
                sql+=" like ?";
                count = (Long) qr.query(sql, new ScalarHandler(),queryParams.get(next));
            }

            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询班级教材书籍总条数错误!");
        }
    }

    @Override
    public GradeBook selByPkid(Integer pk_id) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用query(sql,resultSetHandler,args)方法
        String sql = "select * from grade_book where pk_id = ?";
        try {
            GradeBook gradeBook = qr.query(sql, new BeanHandler<GradeBook>(GradeBook.class), pk_id);
            return gradeBook;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("根据pk_id查询班级教材信息失败");
        }
    }

    @Override
    public int update(GradeBook gradeBook) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用update方法
        String sql = "update grade_book set grade = ?,major = ?,g_class = ?,bookName = ?,bookAuthor = ?,bookPrice = ?,modified_time = ? where pk_id = ?";
        int rows = 0;
        try {
            rows = qr.update(sql,gradeBook.getGrade(),
                    gradeBook.getMajor(),
                    gradeBook.getG_class(),
                    gradeBook.getBookName(),
                    gradeBook.getBookAuthor(),
                    gradeBook.getBookPrice(),
                    gradeBook.getModified_time(),
                    gradeBook.getPk_id());
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("修改班级教材信息异常");
        }
    }
}
