package zjw.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import zjw.pojo.Root;
import zjw.utils.C3P0Utils;

public interface RootDao {

    /**
     * 管理员登入
     * @param account
     * @return
     */
    Root login(String account);

    /**
     * 管理员修改密码信息
     * @param root
     * @return
     */
    int updata(Root root);
}
