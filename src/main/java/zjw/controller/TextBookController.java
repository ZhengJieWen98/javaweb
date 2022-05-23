package zjw.controller;

import com.zjw.frameword.Controller;
import com.zjw.frameword.RequestMapping;
import org.apache.commons.io.FileUtils;
import zjw.entity.*;
import zjw.pojo.TextBook;
import zjw.service.TextBookService;
import zjw.service.impl.TextBookServiceImpl;
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
public class TextBookController {
    private TextBookService service = new TextBookServiceImpl();
    private Map queryParams=null;
    @RequestMapping("/book/add")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            TextBook book = JsonUtils.parseJSON2Object(request, TextBook.class);
            service.insert(book);
            //没有出现异常，添加书籍成功
            JsonUtils.printResult(response,new Result(true,"添加教材书籍成功",book));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/book/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            TextBook book = JsonUtils.parseJSON2Object(request, TextBook.class);
            service.update(book);
            //没有出现异常，修改书籍成功
            JsonUtils.printResult(response,new Result(true,"修改教材书籍成功",book));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/book/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数
            TextBook book = JsonUtils.parseJSON2Object(request, TextBook.class);
            service.delete(book.getPk_id());
            //没有出现异常，修改书籍成功
            JsonUtils.printResult(response,new Result(true,"删除教材书籍成功",book));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/book/selAll")
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request,QueryPageBean.class);
            List<TextBook> list = service.selectAll(queryPageBean);
            queryParams=queryPageBean.getQueryParams();
            long count = service.getCount(queryPageBean.getQueryParams());

            PageResult pageResult = new PageResult();
            pageResult.setRows(list);
            pageResult.setTotal(count);
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"查询所有教材书籍成功",pageResult));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }


    @RequestMapping("/book/selByPk_id")
    public void selByPk_id(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数
            TextBook textBook = JsonUtils.parseJSON2Object(request,TextBook.class);
            TextBook textBook1 = service.selByPk_id(textBook.getPk_id());
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"根据pk_id查询教材书籍成功",textBook1));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/book/selAllBookName")
    public void selAllBookName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<BookName> allBookName = service.getAllBookName();
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"查询所有教材名成功",allBookName));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/book/getBookAuthorByBookName")
    public void getBookAuthorByBookName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BookName bookName = JsonUtils.parseJSON2Object(request, BookName.class);
            List<BookAuthor> list = service.getBookAuthorByBookName(bookName.getName());
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"根据教材书籍名查询相关作者成功",list));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/book/getBookPrice")
    public void getBookPrice(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map map = JsonUtils.parseJSON2Object(request, Map.class);
            String bookName = (String) map.get("name");
            String bookAuthor = (String) map.get("author");
            BookPrice bookPrice = service.getBookPrice(bookName, bookAuthor);
            //没有出现异常，查询所有书籍成功
            JsonUtils.printResult(response,new Result(true,"根据教材书籍名查询相关作者成功",bookPrice));
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    @RequestMapping("/book/downloadAll")
    public void downloadAll(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //String s = TextBookController.class.getClassLoader().getResource("").toString();
        List<TextBook> allTextBook = service.getAllTextBook(queryParams);
        ExcelUtil.fillExcel_TextBook(allTextBook,new File("textBook.xlsx"));
        response.setHeader("Content-Disposition", "attachment;filename=books_.xlsx");
        //把二进制流放入到响应体中.
        ServletOutputStream os = response.getOutputStream();

        File file = new File("textBook.xlsx");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
    }
}
