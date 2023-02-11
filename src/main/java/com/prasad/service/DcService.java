package com.prasad.service;

import com.prasad.binding.EducationBinding;
import com.prasad.binding.IncomeBinding;
import com.prasad.binding.KidsBinding;
import com.prasad.binding.KidsInfoListBinding;
import com.prasad.binding.PlanSelectionBinding;
import com.prasad.binding.Sumarry;

public interface DcService  {

	
	//for plan selection handleing request and response
	public PlanSelectionBinding createCase(Integer appId);
	//
	public Long UpdateCitizenPaln(PlanSelectionBinding planSelection);
	public Long saveEducation(EducationBinding education);
	
	public Long saveIncome(IncomeBinding income);
	//public Long saveKids(KidsBinding kids);
	//public Sumarry saveAlldetals(Sumarry summary);
	public Sumarry saveKids(KidsInfoListBinding kids);
	
}
