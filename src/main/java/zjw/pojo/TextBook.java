package zjw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class TextBook {
    private int pk_id;
    private String name;
    private String author;
    private double price;
    private String create_time;
    private String modified_time;
}
