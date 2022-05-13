package zjw.pojo;

import lombok.Data;

@Data
public class GradeClass {
    private int pk_id;
    private String grade;
    private String major;
    private String g_class;
    private int g_count;
    private String instructor;
    private String create_time;
    private String modified_time;
    private String instructor_phone;
}
