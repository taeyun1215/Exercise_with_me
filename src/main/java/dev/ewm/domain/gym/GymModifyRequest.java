package dev.ewm.domain.gym;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import dev.ewm.global.valid.SelfValidating;

public class GymModifyRequest extends SelfValidating<GymModifyRequest> {
	@Min(1)
    private Long id;
	
    private String gymName;
	private String address;
	
	@Min(0)
	private Integer priceOfMonth;
	
	//private String description;

	public GymModifyRequest() {
		super();
	}
	
	public GymModifyRequest(@Min(1) Long id, String gymName, String address, @Min(0) Integer priceOfMonth) {
		super();
		this.id = id;
		this.gymName = gymName;
		this.address = address;
		this.priceOfMonth = priceOfMonth;
		this.validateSelf();
	}

}
