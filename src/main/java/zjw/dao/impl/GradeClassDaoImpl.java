package zjw.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import zjw.dao.GradeClassDao;
import zjw.entity.G_Class;
import zjw.entity.Grade;
import zjw.entity.Major;
import zjw.entity.QueryPageBean;
import zjw.pojo.GradeClass;
import zjw.utils.C3P0Utils;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GradeClassDaoImpl implements GradeClassDao {
    @Override
    public int insert(GradeClass grade) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用update方法
        String sql = "insert into grade_class(grade,major,g_class,g_count,instructor,create_time,modified_time,instructor_phone) values(?,?,?,?,?,?,?,?)";
        int rows = 0;
        try {
            rows = qr.update(sql,grade.getGrade(),grade.getMajor(),grade.getG_class(),grade.getG_count(),grade.getInstructor(),grade.getCreate_time(),grade.getModified_time(),grade.getInstructor_phone());
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("添加班级信息异常");
        }
    }

    @Override
    public int update(GradeClass grade) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用update方法
        String sql = "update grade_class set grade = ?,major = ?,g_class = ?,g_count = ?,instructor = ?,modified_time = ?,instructor_phone = ? where pk_id = ?";
        int rows = 0;
        try {
            rows = qr.update(sql,grade.getGrade(),grade.getMajor(),grade.getG_class(),grade.getG_count(),grade.getInstructor(),grade.getModified_time(),grade.getInstructor_phone(),grade.getPk_id());
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("修改班级信息异常");
        }
    }

    @Override
    public int delete(Integer pk_id) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        // 2.调用update方法
        String sql = "delete from grade_class where pk_id = ?";
        int rows = 0;
        try {
            rows = qr.update(sql, pk_id);
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("删除班级信息异常");
        }
    }

    @Override
    public List<GradeClass> selectAll(QueryPageBean queryPageBean) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        Map queryParams = queryPageBean.getQueryParams();
        List<GradeClass> list = null;
        try {
            if(queryParams==null){
                String sql = "select * from grade_class limit ?,?";
                list = qr.query(sql, new BeanListHandler<>(GradeClass.class),queryPageBean.getOffset(),queryPageBean.getPageSize());
            }else if(queryParams.size()==2){
                String sql = "select * from grade_class where major like ? and instructor like ? limit ?,?";
                String major = (String) queryParams.get("major");
                String instructor = (String) queryParams.get("instructor");
                list = qr.query(sql, new BeanListHandler<>(GradeClass.class),"%"+major+"%","%"+instructor+"%",queryPageBean.getOffset(),queryPageBean.getPageSize());
            }else if(queryParams.size()==1){
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                while (iterator.hasNext()){
                    next = (String) iterator.next();
                }
                if(next.equals("major")){
                    String sql = "select * from grade_class where major like ? limit ?,?";
                    String major = (String) queryParams.get("major");
                    list = qr.query(sql, new BeanListHandler<>(GradeClass.class),"%"+major+"%",queryPageBean.getOffset(),queryPageBean.getPageSize());
                }else{
                    String sql = "select * from grade_class where instructor like ? limit ?,?";
                    String instructor = (String) queryParams.get("instructor");
                    list = qr.query(sql, new BeanListHandler<>(GradeClass.class),"%"+instructor+"%",queryPageBean.getOffset(),queryPageBean.getPageSize());
                }
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询所有教材信息异常");
        }
    }

    @Override
    public List<GradeClass> getAllGradeClass(Map queryParams) {
        // 1.创建QueryRunner对象,传入连接池
//        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
//        String sql = "select * from grade_class";
//        try {
//            List<GradeClass> list = qr.query(sql, new BeanListHandler<>(GradeClass.class));
//            return list;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            throw new RuntimeException("查询所有班级异常");
//        }

        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        List<GradeClass> list = null;
        try {
            if(queryParams==null){
                String sql = "select * from grade_class";
                list = qr.query(sql, new BeanListHandler<>(GradeClass.class));
            }else if(queryParams.size()==2){
                String sql = "select * from grade_class where major like ? and instructor";
                String major = (String) queryParams.get("major");
                String instructor = (String) queryParams.get("instructor");
                list = qr.query(sql, new BeanListHandler<>(GradeClass.class),"%"+major+"%","%"+instructor+"%");
            }else if(queryParams.size()==1){
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                while (iterator.hasNext()){
                    next = (String) iterator.next();
                }
                if(next.equals("major")){
                    String sql = "select * from grade_class where major like ?";
                    String major = (String) queryParams.get("major");
                    list = qr.query(sql, new BeanListHandler<>(GradeClass.class),"%"+major+"%");
                }else{
                    String sql = "select * from grade_class where instructor like ?";
                    String instructor = (String) queryParams.get("instructor");
                    list = qr.query(sql, new BeanListHandler<>(GradeClass.class),"%"+instructor+"%");
                }
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询所有教材信息异常");
        }

    }

    @Override
    public long getCount(Map queryParams) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        try {
            Long count=null;
            if(queryParams==null){
                String sql = "select count(*) from grade_class";
                count = (Long) qr.query(sql, new ScalarHandler());
            }else if(queryParams.size()==2){
                String sql = "select count(*) from grade_class where major like ? and instructor like ?";
                String major = (String) queryParams.get("major");
                String instructor = (String) queryParams.get("instructor");
                count = (Long) qr.query(sql, new ScalarHandler(),"%"+major+"%","%"+instructor+"%");
            }else if(queryParams.size()==1){
                String sql = "select count(*) from grade_class where ";
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                while (iterator.hasNext()){
                    next = (String) iterator.next();
                    sql+=next;
                }
                sql+=" like ?";
                count = (Long) qr.query(sql, new ScalarHandler(),"%"+queryParams.get(next)+"%");
            }

            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询教材书籍总条数错误!");
        }
    }

    @Override
    public GradeClass selByPk_id(Integer pk_id) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        // 2.调用query(sql,resultSetHandler,args)方法
        String sql = "select * from grade_class where pk_id = ?";
        try {
            GradeClass gradeClass = qr.query(sql, new BeanHandler<GradeClass>(GradeClass.class), pk_id);
            return gradeClass;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("根据pk_id查询班级信息失败");
        }

    }

    @Override
    public List<Grade> selAllGrade() {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用query方法
        String sql = "select grade from grade_class GROUP BY grade";
        try {
            List<Grade> list = qr.query(sql, new BeanListHandler<>(Grade.class));
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询年级分组异常");
        }
    }

    @Override
    public List<Major> selAllMajor(String grade) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select major from grade_class where grade=? GROUP BY major";
        try {
            List<Major> list = qr.query(sql, new BeanListHandler<>(Major.class),grade);
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询专业异常");
        }
    }

    @Override
    public List<G_Class> selByGradeAndMajor(String grade, String major) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select g_class from grade_class where grade = ? and major = ? GROUP BY g_class";
        try {
            List<G_Class> list = qr.query(sql, new BeanListHandler<>(G_Class.class),grade,major);
            System.out.println(list);
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("根据年级和专业查询班级异常");
        }
    }

}
