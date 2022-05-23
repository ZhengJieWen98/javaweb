package zjw.service.impl;

import zjw.dao.TextBookDao;
import zjw.dao.impl.TextBookDaoImpl;
import zjw.entity.*;
import zjw.pojo.TextBook;
import zjw.service.TextBookService;
import zjw.utils.TimeUtils;

import java.util.List;
import java.util.Map;

public class TextBookServiceImpl implements TextBookService {
    private TextBookDao dao = new TextBookDaoImpl();

    @Override
    public int insert(TextBook textBook) {
        textBook.setCreate_time(TimeUtils.getcurrentTime());
        textBook.setModified_time(TimeUtils.getcurrentTime());
        int insert = dao.insert(textBook);
        if(insert==1){
            return insert;
        }else {
            throw new RuntimeException("添加教材书籍失败");
        }
    }

    @Override
    public int update(TextBook textBook) {
        textBook.setModified_time(TimeUtils.getcurrentTime());
        int update = dao.update(textBook);
        if(update==1){
            return update;
        }else {
            throw new RuntimeException("更新教材书籍信息失败");
        }
    }

    @Override
    public int delete(Integer pk_id) {
        int delete = dao.delete(pk_id);
        if(delete==1){
            return delete;
        }else {
            throw new RuntimeException("删除教材书籍信息失败");
        }
    }

    @Override
    public List<TextBook> selectAll(QueryPageBean queryPageBean) {
        List<TextBook> list = dao.selectAll(queryPageBean);
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("还未添加教材书籍信息!");
        }
    }

    @Override
    public List<TextBook> getAllTextBook(Map queryParams) {
        List<TextBook> list = dao.getAllTextBook(queryParams);
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("还未添加教材书籍信息!");
        }
    }

    @Override
    public long getCount(Map queryParams) {
        return dao.selectCount(queryParams);
    }

    @Override
    public TextBook selByPk_id(Integer pk_id) {
        TextBook textBook = dao.selByPk_id(pk_id);
        if(textBook!=null){
            return textBook;
        }else{
            throw new RuntimeException("没有存在相关教材书籍");
        }
    }

    @Override
    public List<BookName> getAllBookName() {
        List<BookName> list = dao.getAllBookName();
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("还未添加教材");
        }
    }

    @Override
    public List<BookAuthor> getBookAuthorByBookName(String bookName) {
        List<BookAuthor> list = dao.getBookAuthorByBookName(bookName);
        if(list.size()>0){
            return list;
        }else{
            throw new RuntimeException("没有该书籍的相关作者");
        }
    }

    @Override
    public BookPrice getBookPrice(String bookName, String bookAuthor) {
        BookPrice bookPrice = dao.getBookPrice(bookName, bookAuthor);
        if(bookPrice!=null){
            return bookPrice;
        }else{
            throw new RuntimeException("根据教材名和作者查询无价格");
        }
    }
}
