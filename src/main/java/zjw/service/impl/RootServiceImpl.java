package zjw.service.impl;

import zjw.dao.RootDao;
import zjw.dao.impl.RootDaoImpl;
import zjw.pojo.Root;
import zjw.service.RootService;
import zjw.utils.AESCBCUtils;

public class RootServiceImpl implements RootService {
    private RootDao rootDao = new RootDaoImpl();
    @Override
    public Root login(Root login) throws Exception {
        Root root = rootDao.login(login.getAccount());
        if(root!=null){
            //对密码进行解密操作
            String password = AESCBCUtils.decryptByCBC(AESCBCUtils.DEFAULT_KEY, root.getPassword(), AESCBCUtils.DEFAULT_IV).trim();
            if(password.trim().equals(login.getPassword())){
                root.setPassword(password);
                return root;
            }else{
                throw new RuntimeException("管理员密码错误!");
            }
        }else{
            throw new RuntimeException("管理员账号不存在!");
        }
    }

    @Override
    public int updata(Root root) throws Exception {
        //管理员密码进行加密
        String password = AESCBCUtils.encryptByCBC(AESCBCUtils.DEFAULT_KEY, root.getPassword(), AESCBCUtils.DEFAULT_IV).trim();
        root.setPassword(password);
        System.out.println("管理员修改密码"+root);
        int row = rootDao.updata(root);
        if(row==1){
            return row;
        }else {
            throw new RuntimeException("管理员修改密码失败!");
        }
    }
}
