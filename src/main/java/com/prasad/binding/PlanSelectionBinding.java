package com.prasad.binding;

import java.util.Map;

import lombok.Data;

@Data
public class PlanSelectionBinding {
	private Long caseNum;
	private Integer palnId;
	private Map<Integer, String> planInfo;
	
}
