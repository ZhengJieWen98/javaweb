package zjw.pojo;

import lombok.Data;

@Data
public class GradeBook {
    private int pk_id;

    private String grade;
    private String major;
    private String g_class;
    private String bookName;
    private String bookAuthor;
    private String bookPrice;

    private String create_time;
    private String modified_time;
}
