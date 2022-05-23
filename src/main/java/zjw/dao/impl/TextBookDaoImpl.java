package zjw.dao.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import zjw.dao.TextBookDao;
import zjw.entity.*;
import zjw.pojo.Root;
import zjw.pojo.TextBook;
import zjw.utils.C3P0Utils;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TextBookDaoImpl implements TextBookDao {
    @Override
    public int insert(TextBook textBook) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用update方法
        String sql = "insert into text_book(name,author,price,create_time,modified_time) values(?,?,?,?,?)";
        int rows = 0;
        try {
            rows = qr.update(sql, textBook.getName(),textBook.getAuthor(),textBook.getPrice(),textBook.getCreate_time(),textBook.getModified_time());
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("添加教材信息异常");
        }
    }

    @Override
    public int update(TextBook textBook) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        // 2.调用update方法
        String sql = "update text_book set name = ?,author = ?,price = ?,modified_time = ? where pk_id = ?";
        int rows = 0;
        try {
            rows = qr.update(sql,textBook.getName(),textBook.getAuthor(),textBook.getPrice(),textBook.getModified_time(),textBook.getPk_id());
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("更新教材异常");
        }
    }

    @Override
    public int delete(Integer pk_id) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        // 2.调用update方法
        String sql = "delete from text_book where pk_id = ?";
        int rows = 0;
        try {
            rows = qr.update(sql, pk_id);
            return rows;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("删除教材异常");
        }

    }

    @Override
    public List<TextBook> selectAll(QueryPageBean queryPageBean) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        Map queryParams = queryPageBean.getQueryParams();
        List<TextBook> list = null;
        try {
            if(queryParams==null){
                String sql = "select * from text_book limit ?,?";
                list = qr.query(sql, new BeanListHandler<>(TextBook.class),queryPageBean.getOffset(),queryPageBean.getPageSize());
            }else if(queryParams.size()==2){
                String sql = "select * from `text_book` where name like ? and author like ? limit ?,?";
                String name = (String) queryParams.get("name");
                String author = (String) queryParams.get("author");
                list = qr.query(sql, new BeanListHandler<>(TextBook.class),"%"+name+"%","%"+author+"%",queryPageBean.getOffset(),queryPageBean.getPageSize());
            }else if(queryParams.size()==1){
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                while (iterator.hasNext()){
                    next = (String) iterator.next();
                }
                if(next.equals("name")){
                    String sql = "select * from `text_book` where name like ? limit ?,?";
                    String name = (String) queryParams.get("name");
                    list = qr.query(sql, new BeanListHandler<>(TextBook.class),"%"+name+"%",queryPageBean.getOffset(),queryPageBean.getPageSize());
                }else{
                    String sql = "select * from `text_book` where author like ? limit ?,?";
                    String author = (String) queryParams.get("author");
                    list = qr.query(sql, new BeanListHandler<>(TextBook.class),"%"+author+"%",queryPageBean.getOffset(),queryPageBean.getPageSize());
                }
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询所有教材信息异常");
        }
    }

    @Override
    public List<TextBook> getAllTextBook(Map queryParams) {
        // 1.创建QueryRunner对象,传入连接池
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        List<TextBook> list = null;
        try {
            if(queryParams==null){
                String sql = "select * from text_book";
                list = qr.query(sql, new BeanListHandler<>(TextBook.class));
            }else if(queryParams.size()==2){
                String sql = "select * from `text_book` where name like ? and author like ?";
                String name = (String) queryParams.get("name");
                String author = (String) queryParams.get("author");
                list = qr.query(sql, new BeanListHandler<>(TextBook.class),"%"+name+"%","%"+author+"%");
            }else if(queryParams.size()==1){
                Set key = queryParams.keySet();
                Iterator iterator = key.iterator();
                String next="";
                while (iterator.hasNext()){
                    next = (String) iterator.next();
                }
                if(next.equals("name")){
                    String sql = "select * from `text_book` where name like ?";
                    String name = (String) queryParams.get("name");
                    list = qr.query(sql, new BeanListHandler<>(TextBook.class),"%"+name+"%");
                }else{
                    String sql = "select * from `text_book` where author like ?";
                    String author = (String) queryParams.get("author");
                    list = qr.query(sql, new BeanListHandler<>(TextBook.class),"%"+author+"%");
                }
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询所有教材信息异常");
        }
    }

    @Override
    public long selectCount(Map queryParams) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        try {
            Long count=null;
            if(queryParams==null){
                String sql = "select count(*) from text_book";
                count = (Long) qr.query(sql, new ScalarHandler());
            }else if(queryParams.size()==2){
                String sql = "select count(*) from `text_book` where name like ? and author like ?";
                String name = (String) queryParams.get("name");
                String author = (String) queryParams.get("author");
                count = (Long) qr.query(sql, new ScalarHandler(),"%"+name+"%","%"+author+"%");
            }else if(queryParams.size()==1){
                String sql = "select count(*) from text_book where ";
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
    public TextBook selByPk_id(Integer pk_id) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());

        // 2.调用query(sql,resultSetHandler,args)方法
        String sql = "select * from text_book where pk_id = ?";
        try {
            TextBook textBook = qr.query(sql, new BeanHandler<TextBook>(TextBook.class), pk_id);
            return textBook;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("根据pk_id查询教材书籍失败");
        }
    }

    @Override
    public List<BookName> getAllBookName() {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用query方法
        String sql = "select name from text_book GROUP BY name";
        try {
            List<BookName> list = qr.query(sql, new BeanListHandler<>(BookName.class));
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询教材分组异常");
        }

    }

    @Override
    public List<BookAuthor> getBookAuthorByBookName(String bookName) {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select author from text_book where name = ? GROUP BY author";
        try {
            List<BookAuthor> list = qr.query(sql, new BeanListHandler<>(BookAuthor.class),bookName);
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("根据教材书名查询作者");
        }
    }

    @Override
    public BookPrice getBookPrice(String bookName, String bookAuthor) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用query(sql,resultSetHandler,args)方法
        String sql = "select price from text_book where name=? and author=? GROUP BY price";

        try {
            BookPrice bookPrice = qr.query(sql, new BeanHandler<BookPrice>(BookPrice.class), bookName, bookAuthor);
            return bookPrice;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("根据教材名和作者查询价格！");
        }
    }

}
