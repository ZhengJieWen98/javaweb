package zjw.dao;

import zjw.entity.BookAuthor;
import zjw.entity.BookName;
import zjw.entity.BookPrice;
import zjw.entity.QueryPageBean;
import zjw.pojo.TextBook;

import java.util.List;
import java.util.Map;

public interface TextBookDao {

    /**
     * 添加教材书籍
     * @param textBook
     * @return
     */
    int insert(TextBook textBook);

    /**
     * 修改教材书籍
     * @param textBook
     * @return
     */
    int update(TextBook textBook);

    /**
     * 根据教材书籍主键id删除书籍
     * @param pk_id
     * @return
     */
    int delete(Integer pk_id);

    /**
     * 查询分页所有的书籍
     * @return
     */
    List<TextBook> selectAll(QueryPageBean queryPageBean);

    /**
     * 查询所有的书籍
     * @return
     */
    List<TextBook> getAllTextBook();

    /**
     * 查询总数据
     * @return
     */
    long selectCount(Map queryParams);

    /**
     * 根据pk_id查询教材书籍
     * @param pk_id
     * @return
     */
    TextBook selByPk_id(Integer pk_id);

    List<BookName> getAllBookName();

    List<BookAuthor> getBookAuthorByBookName(String bookName);

    BookPrice getBookPrice(String bookName, String bookAuthor);
}
