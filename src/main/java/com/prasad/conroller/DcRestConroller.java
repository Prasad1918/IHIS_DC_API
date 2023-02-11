package com.prasad.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.binding.EducationBinding;
import com.prasad.binding.IncomeBinding;
import com.prasad.binding.KidsInfoListBinding;
import com.prasad.binding.PlanSelectionBinding;
import com.prasad.binding.Sumarry;
import com.prasad.service.DcService;

@RestController
public class DcRestConroller {
	@Autowired
	DcService dcService;
	@GetMapping("/createcasecitizen/{appid}")
	public ResponseEntity<PlanSelectionBinding> createCase(@PathVariable Integer appid){
	PlanSelectionBinding planselection=	dcService.createCase(appid);
		return new ResponseEntity<>(planselection,HttpStatus.OK);
	}
	//apply Plan
	@PostMapping("/applyplancitizen")
	public ResponseEntity<Long> applyPlan(@RequestBody PlanSelectionBinding planselection){
	Long caseNum=	dcService.UpdateCitizenPaln(planselection);
		return new ResponseEntity<>(caseNum,HttpStatus.OK);
		
	}
	//save income data
	@PostMapping("/saveincome")
	public ResponseEntity<Long> saveincome(@RequestBody IncomeBinding income){
		
		Long caseNum=dcService.saveIncome(income);
		return new ResponseEntity<>(caseNum,HttpStatus.OK);
	}
	//save Education
	@PostMapping("/saveeducation")
	public ResponseEntity<Long> saveEducation(@RequestBody EducationBinding education){
	Long caseNum=	dcService.saveEducation(education);
		
		return new ResponseEntity<>(caseNum,HttpStatus.OK);
		
	}
	@PostMapping("/savekids")
	public ResponseEntity<Sumarry> savekids(@RequestBody KidsInfoListBinding kids){
	Sumarry  summary=	dcService.saveKids(kids);
		
		return new ResponseEntity<>(summary,HttpStatus.OK);
		
	}
}
