package aforo255.com.msazaccount.model.api;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class CustomerResponse {

	private Integer IdCustomer;
	private String FullName;

}

