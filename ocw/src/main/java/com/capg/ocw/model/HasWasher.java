package com.capg.ocw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HasWasher extends CWObject{

	private Washer washer;
	private CWOrders orders;
}
