package zjw.service;

import org.junit.Test;
import zjw.pojo.Root;
import zjw.service.impl.RootServiceImpl;

public class RootServiceImplTest {

    //@Test
    public void login(){
        RootService rootService = new RootServiceImpl();
        Root root = new Root();
        root.setAccount("admin");
        root.setPassword("123456");
        Root login = null;
        try {
            login = rootService.login(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(login);
    }

    //@Test
    public void updata() throws Exception {
        RootService rootService = new RootServiceImpl();
        Root root = new Root();
        root.setAccount("admin");
        root.setPassword("123456");
        rootService.updata(root);
    }
}
