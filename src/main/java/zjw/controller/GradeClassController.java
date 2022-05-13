package zjw.controller;

import com.zjw.frameword.Controller;
import com.zjw.frameword.RequestMapping;
import org.apache.commons.io.FileUtils;
import zjw.entity.*;
import zjw.pojo.GradeClass;
import zjw.pojo.TextBook;
import zjw.service.GradeClassService;
import zjw.service.impl.GradeClassServiceImpl;
import zjw.utils.ExcelUtil;
import zjw.utils.JsonUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class GradeClassController {
    private GradeClassService service = new GradeClassServiceImpl();

    @RequestMapping("/grade/add")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            GradeClass gradeClass = JsonUtils.parseJSON2Object(request, GradeClass.class);
            service.insert(gradeClass);
            //没有出现异常，添加书籍成功
            JsonUtils.printResult(response,new Result(true,"添加班级信息成功",gradeClass));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }


    @RequestMapping("/grade/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            GradeClass gradeClass = JsonUtils.parseJSON2Object(request, GradeClass.class);
            service.update(gradeClass);
            //没有出现异常，修改班级成功
            JsonUtils.printResult(response,new Result(true,"修改班级信息成功",gradeClass));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/grade/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            GradeClass gradeClass = JsonUtils.parseJSON2Object(request, GradeClass.class);
            service.delete(gradeClass.getPk_id());
            //没有出现异常，删除班级成功
            JsonUtils.printResult(response,new Result(true,"删除班级信息成功",gradeClass));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/grade/selAll")
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request,QueryPageBean.class);
            List<GradeClass> list = service.selectAll(queryPageBean);
            PageResult pageResult = new PageResult();
            pageResult.setRows(list);
            long count = service.getCount(queryPageBean.getQueryParams());
            pageResult.setTotal(count);
            //没有出现异常，查询所有班级信息
            JsonUtils.printResult(response,new Result(true,"查询所有班级成功",pageResult));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/grade/selByPk_id")
    public void selByPk_id(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数
            GradeClass gradeClass = JsonUtils.parseJSON2Object(request,GradeClass.class);
            GradeClass gradeClass1 = service.selByPk_id(gradeClass.getPk_id());
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"根据pk_id查询教材书籍成功",gradeClass1));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/grade/selAllGrade")
    public void selAllGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Grade> list = service.selAllGrade();
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"查询所有书籍成功",list));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/grade/selAllMajor")
    public void selAllMajor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Grade grade = JsonUtils.parseJSON2Object(request, Grade.class);
            List<Major> list = service.selAllMajor(grade.getGrade());
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"根据年级查询专业成功",list));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/grade/selByGradeAndMajor")
    public void selByGradeAndMajor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map map = JsonUtils.parseJSON2Object(request, Map.class);
            String grade = (String) map.get("grade");
            String major = (String) map.get("major");
            System.out.println(grade);
            System.out.println(major);
            List<G_Class> list = service.selByGradeAndMajor(grade, major);
            JsonUtils.printResult(response,new Result(true,"根据年级查询专业成功",list));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/grade/downloadAll")
    public void downloadAll(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String s = TextBookController.class.getClassLoader().getResource("").toString();
        List<GradeClass> list = service.getAllGradeClass();
        ExcelUtil.fillExcel_GradeClass(list,new File("grade_class.xlsx"));
        response.setHeader("Content-Disposition", "attachment;filename=grade_class.xlsx");
        //把二进制流放入到响应体中.
        ServletOutputStream os = response.getOutputStream();

        File file = new File("grade_class.xlsx");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
    }
}
