package InFo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter 
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class InfoDTO {
   
      private int no;
      private int money;
      private String content;
      private String sort;
      private Date day;
      private String id;
      
   
}

