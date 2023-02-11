package com.prasad.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.binding.EducationBinding;
import com.prasad.binding.IncomeBinding;
import com.prasad.binding.KidsBinding;
import com.prasad.binding.KidsInfoListBinding;
import com.prasad.binding.PlanSelectionBinding;
import com.prasad.binding.Sumarry;
import com.prasad.entity.CitizenAppEntity;
import com.prasad.entity.DCIncomEntity;
import com.prasad.entity.DcCaseEntity;
import com.prasad.entity.DcEducationEntity;
import com.prasad.entity.DcKidEntity;
import com.prasad.entity.PlanEntity;
import com.prasad.repository.CitizenAppRepository;
import com.prasad.repository.DcCaseRepository;
import com.prasad.repository.DcEducationRepository;
import com.prasad.repository.DcIncomeRepository;
import com.prasad.repository.DcKidsRepository;
import com.prasad.repository.DcPlanRepository;

@Service
public class DcServiceImpl implements DcService {
	@Autowired
	CitizenAppRepository citizenApprepository;
	@Autowired
	DcCaseRepository dcCaseRepository;
	@Autowired
	DcPlanRepository planRepository;
	@Autowired
	DcEducationRepository dcEducationrepo;
	@Autowired
	DcIncomeRepository dcIncomeRepo;
	@Autowired
	DcKidsRepository dckidsRepository;

	@Override
	public PlanSelectionBinding createCase(Integer appId) {
		// TODO Auto-generated method stub
		PlanSelectionBinding planselection = new PlanSelectionBinding();
		Optional<CitizenAppEntity> findByid = citizenApprepository.findById(appId);

		if (findByid.isPresent()) {
			// Create Case
			DcCaseEntity dcEntity = new DcCaseEntity();
			dcEntity.setAppId(appId);
			dcEntity = dcCaseRepository.save(dcEntity);
			planselection.setCaseNum(dcEntity.getCaseNum());
			// fetch data from db and disply in UI
			List<PlanEntity> planEntity = planRepository.findAll();
			Map<Integer, String> map = new HashMap<>();
			planEntity.forEach(plans -> {
				map.put(plans.getPlanId(), plans.getPlanName());
			});
			// preparing response data
			planselection.setPlanInfo(map);
			planselection.setCaseNum(dcEntity.getCaseNum());
		}
		return planselection;
	}

	@Override
	public Long UpdateCitizenPaln(PlanSelectionBinding planSelection) {
		// once case is created and view all plans then after update the plan as per the
		// citizen

		Long caseNum = planSelection.getCaseNum();
		Integer planId = planSelection.getPalnId();
		Optional<DcCaseEntity> dccaseEntity = dcCaseRepository.findById(caseNum);
		if (dccaseEntity.isPresent()) {
			DcCaseEntity dcentity = dccaseEntity.get();
			dcentity.setPlanId(planId);
			dcCaseRepository.save(dcentity);
		}
		return caseNum;
	}

	@Override
	public Long saveEducation(EducationBinding education) {
		// TODO Auto-generated method stub
		DcEducationEntity dcEucationEntity = new DcEducationEntity();

		BeanUtils.copyProperties(education, dcEucationEntity);

		dcEducationrepo.save(dcEucationEntity);
		return education.getCaseNum();
	}

	@Override
	public Long saveIncome(IncomeBinding income) {
		// TODO Auto-generated method stub
		DCIncomEntity dcIncome = new DCIncomEntity();
		BeanUtils.copyProperties(income, dcIncome);
		dcIncomeRepo.save(dcIncome);
		return income.getCaseNum();
	}

	/*
	 * @Override public Long saveKids(KidsBinding kids) { // TODO Auto-generated
	 * method stub return null; }
	 */

	@Override
	public Sumarry saveKids(KidsInfoListBinding kidsinfo) {
		// TODO Auto-generated method stub
		Long caseNum = kidsinfo.getCaseNum();
		List<KidsBinding> kids = kidsinfo.getListofKids();
		// now I have kid and to store all kids data in to db so may soure is kisds and
		// target is dckidsentity
		List<DcKidEntity> dckidEntity = new ArrayList<>();// insted of add one kids we can add multiplr kids at a time;
		kids.forEach(kid -> {
			DcKidEntity kidentity = new DcKidEntity();
			BeanUtils.copyProperties(kid, kidentity);
			dckidEntity.add(kidentity);// add multiple in to arrarry
			// noow add case number inti dc kids entity
			kidentity.setCaseNum(caseNum);

		});
		dckidsRepository.saveAll(dckidEntity);

		return getSummary(caseNum);
	}

	private Sumarry getSummary(Long caseNum) {

		Optional<DcCaseEntity> dcCase = dcCaseRepository.findById(caseNum);
		
Integer planId=		dcCase.get().getPlanId();
Integer appId=		dcCase.get().getAppId();

 Optional<PlanEntity> plan= planRepository.findById(planId);
 String plananme=plan.get().getPlanName();
 //Based on appid get full name and SSn
 
 Optional<CitizenAppEntity> appid=  citizenApprepository.findById(appId);
 String fName=appid.get().getFullName();
Integer zzn= appid.get().getSsn();
//then based on plan name can i get plan Name

//Optional<>	dcIncomeRepo.findById(caseNum);
//get income data based in case number and then set summary
		DCIncomEntity dcincome = dcIncomeRepo.findByCaseNum(caseNum);// case number is FK in this table so we need to
																		// writre method in repo interface
		
		DcEducationEntity dcEducation = dcEducationrepo.findByCaseNum(caseNum);
		List<DcKidEntity> dckidsEntity = dckidsRepository.findByCaseNum(caseNum);
///set summary for income details;
		Sumarry summary = new Sumarry();
		IncomeBinding incomebinding = new IncomeBinding();
		incomebinding.setCaseNum(dcincome.getCaseNum());
		incomebinding.setIncomeId(dcincome.getIncomeId());
		incomebinding.setPropertyIncome(dcincome.getPropertyIncome());
		incomebinding.setRentIncome(dcincome.getRentIncome());
		summary.setIncome(incomebinding);
		/// set summary for education details;
		EducationBinding educationbinding = new EducationBinding();
		educationbinding.setCaseNum(dcEducation.getCaseNum());
		educationbinding.setEducationId(dcEducation.getEducationId());
		educationbinding.setGradutionYear(dcEducation.getGradutionYear());
		educationbinding.setHigestDegree(dcEducation.getHigestDegree());
		return summary;

	}

}
