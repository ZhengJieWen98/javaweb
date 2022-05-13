package zjw.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import zjw.pojo.GradeBook;
import zjw.pojo.GradeClass;
import zjw.pojo.TextBook;
import zjw.service.GradeBookService;
import zjw.service.GradeClassService;
import zjw.service.TextBookService;
import zjw.service.impl.GradeBookServiceImpl;
import zjw.service.impl.GradeClassServiceImpl;
import zjw.service.impl.TextBookServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 读入excel表格数据和数据写入excle表格
 */
public class ExcelUtil {
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * 存储数据
     * @param data 存储数据的集合
     * @param destFile 存储目标文件
     * @throws IOException
     */
    public static void fillExcel_TextBook(List<TextBook> data, File destFile) throws IOException {

        destFile.createNewFile();

        //创建一个工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //在工作簿里创建一张表
        XSSFSheet sheet = xssfWorkbook.createSheet("用户");
        //
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("教材姓名");
        row.createCell(2).setCellValue("教材作者");
        row.createCell(3).setCellValue("教材价格");
        row.createCell(4).setCellValue("添加教材时间");
        row.createCell(5).setCellValue("最近修改教材时间");
        //行中添加数据
        for(int i=0;i<data.size();i++){
            TextBook textBook = data.get(i);
            int rc=i+1;
            XSSFRow r = sheet.createRow(rc);
            //工号
            r.createCell(0).setCellValue(rc);
            r.createCell(1).setCellValue(textBook.getName());
            r.createCell(2).setCellValue(textBook.getAuthor());
            r.createCell(3).setCellValue(textBook.getPrice());
            r.createCell(4).setCellValue(textBook.getCreate_time());
            r.createCell(5).setCellValue(textBook.getModified_time());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        xssfWorkbook.close();
    }


    /**
     * 存储数据
     * @param data 存储数据的集合
     * @param destFile 存储目标文件
     * @throws IOException
     */
    public static void fillExcel_GradeClass(List<GradeClass> data, File destFile) throws IOException {

        destFile.createNewFile();

        //创建一个工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //在工作簿里创建一张表
        XSSFSheet sheet = xssfWorkbook.createSheet("用户");
        //
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("年级");
        row.createCell(2).setCellValue("专业");
        row.createCell(3).setCellValue("班级");
        row.createCell(4).setCellValue("班级人数");
        row.createCell(5).setCellValue("辅导员");
        row.createCell(6).setCellValue("辅导员电话");
        row.createCell(7).setCellValue("添加班级时间");
        row.createCell(8).setCellValue("最近修改班级信息时间");
        //行中添加数据
        for(int i=0;i<data.size();i++){
            GradeClass gradeClass = data.get(i);
            int rc=i+1;
            XSSFRow r = sheet.createRow(rc);
            //工号
            r.createCell(0).setCellValue(rc);
            r.createCell(1).setCellValue(gradeClass.getGrade());
            r.createCell(2).setCellValue(gradeClass.getMajor());
            r.createCell(3).setCellValue(gradeClass.getG_class());
            r.createCell(4).setCellValue(gradeClass.getG_count());
            r.createCell(5).setCellValue(gradeClass.getInstructor());
            r.createCell(6).setCellValue(gradeClass.getInstructor_phone());
            r.createCell(7).setCellValue(gradeClass.getCreate_time());
            r.createCell(8).setCellValue(gradeClass.getModified_time());

        }
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        xssfWorkbook.close();
    }

    /**
     * 存储数据
     * @param data 存储数据的集合
     * @param destFile 存储目标文件
     * @throws IOException
     */
    public static void fillExcel_GradeBook(List<GradeBook> data, File destFile) throws IOException {

        destFile.createNewFile();

        //创建一个工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //在工作簿里创建一张表
        XSSFSheet sheet = xssfWorkbook.createSheet("用户");
        //
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("年级");
        row.createCell(2).setCellValue("专业");
        row.createCell(3).setCellValue("班级");
        row.createCell(4).setCellValue("教材名称");
        row.createCell(5).setCellValue("教材作者");
        row.createCell(6).setCellValue("教材价格");
        row.createCell(7).setCellValue("添加班级教材时间");
        row.createCell(8).setCellValue("最近修改班级教材信息时间");
        //行中添加数据
        for(int i=0;i<data.size();i++){
            GradeBook g = data.get(i);
            int rc=i+1;
            XSSFRow r = sheet.createRow(rc);

            r.createCell(0).setCellValue(rc);
            r.createCell(1).setCellValue(g.getGrade());
            r.createCell(2).setCellValue(g.getMajor());
            r.createCell(3).setCellValue(g.getG_class());
            r.createCell(4).setCellValue(g.getBookName());
            r.createCell(5).setCellValue(g.getBookAuthor());
            r.createCell(6).setCellValue(g.getBookPrice());
            r.createCell(7).setCellValue(g.getCreate_time());
            r.createCell(8).setCellValue(g.getModified_time());
        }
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        xssfWorkbook.close();
    }


    /**
     * 解析单元格中的值
     * @param cell 单元格
     * @return 单元格内的值
     */
    private static Object getValue(Cell cell) {
        if (null == cell) {
            return null;
        }
        Object value = null;
        switch (cell.getCellType()) {
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case NUMERIC:
                // 日期类型，转换为日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                }
                // 数值类型
                else {

                    // 默认返回double，创建BigDecimal返回准确值
                    value = new BigDecimal(cell.getNumericCellValue());
                }
                break;
            default:
                value = cell.toString();
                break;
        }
        return value;
    }

    /**
     * 设置单元格值
     *
     * @param cell  单元格
     * @param value 值
     */
    private static void setValue(Cell cell, Object value) {
        if (null == cell) {
            return;
        }
        if (null == value) {
            cell.setCellValue((String) null);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue(FORMAT.format((Date) value));
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue(value.toString());
        }
    }
}
