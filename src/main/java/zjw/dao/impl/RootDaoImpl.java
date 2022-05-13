package zjw.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import zjw.dao.RootDao;
import zjw.pojo.Root;
import zjw.utils.C3P0Utils;

import java.sql.SQLException;

public class RootDaoImpl implements RootDao {
    public Root login(String account){
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用query(sql,resultSetHandler,args)方法
        String sql = "select * from root where account = ?";
        Root root = null;
        try {
            root = qr.query(sql, new BeanHandler<Root>(Root.class), account);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("管理员登入异常错误！");
        }
        return root;
    }

    @Override
    public int updata(Root root) {
        // 1.创建QueryRunner对象,传入连接池
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        // 2.调用update方法
        String sql = "update root set password = ? where account = ?";
        int rows = 0;
        try {
            rows = qr.update(sql,root.getPassword(),root.getAccount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("管理员修改密码异常！");
        }
        if(rows==1){
            return rows;
        }else{
            throw new RuntimeException("管理员修改密码失败！");
        }
    }
}
