package sgsits.cse.dis.academics.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sgsits.cse.dis.academics.service.ExpertLectureService;
import sgsits.cse.dis.academics.model.ExpertDetails;
import sgsits.cse.dis.academics.model.ExpertLectureDetails;
import sgsits.cse.dis.academics.model.ExpertNamesAndDesignations;
import sgsits.cse.dis.academics.repo.ExpertLectureRepository;
import sgsits.cse.dis.academics.repo.ExpertRepository;
import sgsits.cse.dis.academics.request.ExpertForm;
import sgsits.cse.dis.academics.request.AddExpertLectureForm;
import sgsits.cse.dis.academics.response.ExpertLecturesResponse;
import sgsits.cse.dis.academics.response.ExpertNamesAndDesignationsResponse;

@Component
public class ExpertLectureServiceImpl implements ExpertLectureService {

	@Autowired
	private ExpertRepository expertRepository;
	
	@Autowired
	private ExpertLectureRepository expertLectureRepository;
	
	//EXPERT SERVICES
	
	@Override
	public String addExpert(ExpertForm addExpertForm) {
		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ExpertDetails expertDetails = new ExpertDetails();
		expertDetails.setName(addExpertForm.getName());
		expertDetails.setDesignation(addExpertForm.getDesignation());
		expertDetails.setEmail(addExpertForm.getEmail());
		expertDetails.setMobileNo(addExpertForm.getMobileNo());
		expertDetails.setDob(addExpertForm.getDob());
		expertDetails.setFathersName(addExpertForm.getFathersName());
		expertDetails.setOfficeAddress(addExpertForm.getOfficeAddress());
		expertDetails.setPinCode(addExpertForm.getPinCode());
		expertDetails.setCity(addExpertForm.getCity());
		expertDetails.setState(addExpertForm.getState());
		expertDetails.setCountry(addExpertForm.getCountry());
		expertDetails.setAadhaarNo(addExpertForm.getAadhaarNo());
		expertDetails.setPanNo(addExpertForm.getPanNo());
		expertDetails.setGstNo(addExpertForm.getGstNo());
		expertDetails.setBankName(addExpertForm.getBankName());
		expertDetails.setAccountNo(addExpertForm.getAccountNo());
		expertDetails.setIfsc(addExpertForm.getIfsc());
		expertDetails.setUniqueTeqipId(addExpertForm.getUniqueTeqipId());
		expertDetails.setType(addExpertForm.getType());
		
		ExpertDetails test = expertRepository.save(expertDetails);
		
		if(test!=null)
			return test.getName()+" sucsessfully added to experts.";
		else
			return null;
	}
	
	@Override
	public ExpertDetails findExpert(String name, String designation) {
		return expertRepository.findByNameAndDesignation(name, designation);
	}
	
	@Override
	public String editExpert(ExpertForm editExpertForm) 
	{
		ExpertDetails expert = expertRepository.findByNameAndDesignation(editExpertForm.getName(), editExpertForm.getDesignation());
		expert.setDob(editExpertForm.getDob());
		expert.setFathersName(editExpertForm.getFathersName());
		expert.setOfficeAddress(editExpertForm.getOfficeAddress());
		expert.setPinCode(editExpertForm.getPinCode());
		expert.setCity(editExpertForm.getCity());
		expert.setState(editExpertForm.getState());
		expert.setCountry(editExpertForm.getCountry());
		expert.setAadhaarNo(editExpertForm.getAadhaarNo());
		expert.setPanNo(editExpertForm.getPanNo());
		expert.setGstNo(editExpertForm.getGstNo());
		expert.setBankName(editExpertForm.getBankName());
		expert.setAccountNo(editExpertForm.getAccountNo());
		expert.setIfsc(editExpertForm.getIfsc());
		expert.setUniqueTeqipId(editExpertForm.getUniqueTeqipId());
		expert.setType(editExpertForm.getType());
		
		ExpertDetails test = expertRepository.save(expert);
		
		if(test!=null)
			return "Expert details updated successfully!";
		else
			return "Error updating expert details, please try again.";
		
	}
	
	@Override
	public List<ExpertNamesAndDesignationsResponse> getExpertNamesAndDesignations()
	{
		List<ExpertNamesAndDesignations> fetch = expertLectureRepository.fetchExperts();
//		System.out.println(fetch);
		List<ExpertNamesAndDesignationsResponse> output = new ArrayList<ExpertNamesAndDesignationsResponse>();
		for(ExpertNamesAndDesignations data : fetch)
		{
			ExpertNamesAndDesignationsResponse record = new ExpertNamesAndDesignationsResponse();
			record.setExpertName(data.getName());
			record.setExpertDesignation(data.getDesignation());
			output.add(record);
		}
		return output;
	}
	
	//EXPERT LECTURE SERVICES
	
	@Override
	public String addExpertLecture(AddExpertLectureForm addExpertLectureForm)
	{
		ExpertLectureDetails expertLectureDetails = new ExpertLectureDetails();
		expertLectureDetails.setTopic(addExpertLectureForm.getTopic());
		expertLectureDetails.setExpertName(addExpertLectureForm.getExpertName());
		expertLectureDetails.setExpertDesignation(addExpertLectureForm.getExpertDesignation());
		expertLectureDetails.setDate(addExpertLectureForm.getDate());
		expertLectureDetails.setTime(addExpertLectureForm.getTime());
		expertLectureDetails.setVenue(addExpertLectureForm.getVenue());
		expertLectureDetails.setCourse(addExpertLectureForm.getCourse());
		expertLectureDetails.setYear(addExpertLectureForm.getYear());
		expertLectureDetails.setConveyance(addExpertLectureForm.getConveyance());
		expertLectureDetails.setHonorarium(addExpertLectureForm.getHonorarium());
		expertLectureDetails.setTotalAmount(addExpertLectureForm.getHonorarium()+addExpertLectureForm.getConveyance());
		expertLectureDetails.setStatus("Pending");
		expertLectureDetails.setPaymentStatus("Pending");
		
		ExpertLectureDetails test = expertLectureRepository.save(expertLectureDetails);
		
		if(test!=null)
			return "Expert lecture on the topic '"+test.getTopic()+"' on Date: "+test.getDate()+", Time: "+test.getTime()+" has been added";
		else
			return null;
	}
	
	@Override
	public List<ExpertLecturesResponse> getExpertLecturesByStatus (String status)
	{
		System.out.println(status);
		List<ExpertLectureDetails> expertLectures = expertLectureRepository.findByStatusIgnoreCase(status);
		List<ExpertLecturesResponse> output = new ArrayList<ExpertLecturesResponse>();
		for(ExpertLectureDetails data : expertLectures)
		{
			ExpertLecturesResponse test = new ExpertLecturesResponse();
			test.setExpertLectureId(data.getExpertLectureId());
			test.setTopic(data.getTopic());
			test.setExpertName(data.getExpertName());
			test.setExpertDesignation(data.getExpertDesignation());
			test.setDate(data.getDate());
			test.setTime(data.getTime());
			test.setAudience(data.getCourse()+", "+data.getYear());
			test.setStatus(data.getStatus());
			
			output.add(test);
		}
		return output;
	}
	
	@Override
	public ExpertLectureDetails viewExpertLectureDetails (String expertLectureId)
	{
		return expertLectureRepository.findByExpertLectureId(expertLectureId);
	}

	@Override
	public List<ExpertLecturesResponse> searchExpertLectures(String keyword) {
		List<ExpertLectureDetails> expertLectures = expertLectureRepository.findByTopicContainingIgnoreCase(keyword);
		List<ExpertLecturesResponse> output = new ArrayList<ExpertLecturesResponse>();
		for(ExpertLectureDetails data : expertLectures)
		{
			ExpertLecturesResponse test = new ExpertLecturesResponse();
			test.setExpertLectureId(data.getExpertLectureId());
			test.setTopic(data.getTopic());
			test.setExpertName(data.getExpertName());
			test.setExpertDesignation(data.getExpertDesignation());
			test.setDate(data.getDate());
			test.setTime(data.getTime());
			test.setAudience(data.getCourse()+", "+data.getYear());
			test.setStatus(data.getStatus());
			
			output.add(test);
		}
		return output;
	}

	@Override
	public String updateExpertLectureStatus(String expertLectureId) {
		ExpertLectureDetails expertLecture = expertLectureRepository.findByExpertLectureId(expertLectureId);
		String currentStatus = expertLecture.getStatus();
		if(currentStatus.equals("Pending"))
			expertLecture.setStatus("Upcoming");
		else if(currentStatus.equals("Upcoming"))
			expertLecture.setStatus("Completed");
		ExpertLectureDetails test = expertLectureRepository.save(expertLecture);
		
		if(test!=null)
			return "Status updated successfully!";
		else
			return "Error updating status, please try again.";
	}
	
	
}