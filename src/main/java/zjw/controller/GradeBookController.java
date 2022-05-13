package zjw.controller;

import com.zjw.frameword.Controller;
import com.zjw.frameword.RequestMapping;
import org.apache.commons.io.FileUtils;
import zjw.entity.PageResult;
import zjw.entity.QueryPageBean;
import zjw.entity.Result;
import zjw.pojo.GradeBook;
import zjw.pojo.GradeClass;
import zjw.service.GradeBookService;
import zjw.service.impl.GradeBookServiceImpl;
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
public class GradeBookController {
    private GradeBookService service = new GradeBookServiceImpl();
    //根据该查询条件下载班级教材信息
    private Map queryParams_download=null;

    @RequestMapping("/gradeBook/add")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            GradeBook gradeBook = JsonUtils.parseJSON2Object(request, GradeBook.class);
            int insert = service.insert(gradeBook);
            JsonUtils.printResult(response,new Result(true,"添加班级信息成功",gradeBook));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/gradeBook/selAll")
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request,QueryPageBean.class);
            List<GradeBook> list = service.selectAll(queryPageBean);
            queryParams_download = queryPageBean.getQueryParams();
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

    @RequestMapping("/gradeBook/deleteBy_id")
    public void deleteBy_id(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            GradeBook gradeBook = JsonUtils.parseJSON2Object(request, GradeBook.class);
            service.deleteByPk_id(gradeBook.getPk_id());
            //没有出现异常，删除班级成功
            JsonUtils.printResult(response,new Result(true,"删除班级教材信息成功",gradeBook));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/gradeBook/selByPk_id")
    public void selByPk_id(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数
            GradeBook gradeBook = JsonUtils.parseJSON2Object(request,GradeBook.class);
            GradeBook gradeBook1 = service.selByPkid(gradeBook.getPk_id());
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"根据pk_id查询教材书籍成功",gradeBook1));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/gradeBook/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            GradeBook gradeBook = JsonUtils.parseJSON2Object(request, GradeBook.class);
            service.update(gradeBook);
            //没有出现异常，修改班级成功
            JsonUtils.printResult(response,new Result(true,"修改班级教材信息成功",gradeBook));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/gradeBook/downloadAll")
    public void downloadAll(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String s = TextBookController.class.getClassLoader().getResource("").toString();
        List<GradeBook> list = service.getAllGradeBook(queryParams_download);
        ExcelUtil.fillExcel_GradeBook(list,new File("grade_books.xlsx"));
        response.setHeader("Content-Disposition", "attachment;filename=grade_books.xlsx");
        //把二进制流放入到响应体中.
        ServletOutputStream os = response.getOutputStream();

        File file = new File("grade_books.xlsx");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
    }

}
