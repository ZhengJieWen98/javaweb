package zjw.controller;

import com.zjw.frameword.Controller;
import com.zjw.frameword.RequestMapping;
import zjw.entity.Result;
import zjw.pojo.Root;
import zjw.service.RootService;
import zjw.service.impl.RootServiceImpl;
import zjw.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class RootController {
    private RootService userService = new RootServiceImpl();
    @RequestMapping("/root/checkLogin")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Root root = (Root) request.getSession().getAttribute("root");
        try {
            if(root!=null){
                JsonUtils.printResult(response,new Result(true,"管理员已登入过",root));
            }else{
                JsonUtils.printResult(response,new Result(false,"管理员登入过期,请重新登入"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"查询管理员登入状况异常"));
        }

    }

    @RequestMapping("/root/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            Root root = JsonUtils.parseJSON2Object(request, Root.class);
            //2. 调用业务层的方法，校验用户名和密码
            Root checkRoot = userService.login(root);

            //保存登录状态!!!!将loginUser存储到session中
            request.getSession().setAttribute("root",checkRoot);

            //没有出现异常，说明登录成功
            JsonUtils.printResult(response,new Result(true,"登录成功",checkRoot));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常说明登录失败
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/root/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            Root root = JsonUtils.parseJSON2Object(request, Root.class);
            String password = root.getPassword();
            //2. 调用业务层的方法，修改密码
            userService.updata(root);
            //没有出现异常，说明修改密码成功
            Root sesionRoot = (Root) request.getSession().getAttribute("root");
            sesionRoot.setPassword(password);
            JsonUtils.printResult(response,new Result(true,"修改密码成功",sesionRoot));

        } catch (Exception e) {
            e.printStackTrace();
            //出现异常说明登录失败
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }
    @RequestMapping(value = "/root/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            //清除登录状态
            request.getSession().setAttribute("root",null);

            JsonUtils.printResult(response,new Result(true,"退出登录成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"退出登录失败"));
        }
    }

}
