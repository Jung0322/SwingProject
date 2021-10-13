package InFo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class InfoDTO {
	
	@Setter
	@Getter 
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString

	public class EmpDTO {
		private int no;
		private int money;
		private int content;
		private int sort;
		private Date day;
		private int id;
		
	
	

}
}
