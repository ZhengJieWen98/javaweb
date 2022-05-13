package zjw.service;

import zjw.pojo.Root;

public interface RootService {
    /**
     * 管理员登入
     * @param root
     * @return
     * @throws Exception
     */
    Root login(Root root) throws Exception;

    /**
     * 管理员修改密码
     * @param root
     * @return
     */
    int updata(Root root) throws Exception;
}
