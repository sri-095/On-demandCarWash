package com.capg.ocw.operation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.ReviewRatings;
import com.capg.ocw.model.Washer;
import com.capg.ocw.model.dto.ReviewRatingDto;
import com.capg.ocw.model.dto.WasherDto;
import com.capg.ocw.repository.ReviewRatingsRepository;
import com.capg.ocw.repository.WasherRepository;
import com.capg.ocw.util.OCWConstants;

@Component
public class WasherOperation {

	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private ReviewRatingsRepository reviewRatingsRepository;
	
	public List<WasherDto> getAllWashers() {
		List<WasherDto> washerDtoList = new ArrayList<>();
		
		List<Washer> washerList = washerRepository.findAll();
		washerList.stream().forEach(washer->{
			WasherDto washerDto = new WasherDto();
			washerDto.setWasherId(washer.getWasherId());
			washerDto.setName(washer.getName());
			washerDto.setOrderList(washer.getOrderList());
			washerDto.setEmailId(washer.getEmailId());
			washerDto.setRatings(washer.getRatings());
			washerDto.setPhoneNumber(washer.getPhoneNumber());
			washerDtoList.add(washerDto);
		});
		return washerDtoList;
	}
	
	@Transactional(rollbackFor = OcwException.class)
	public List<WasherDto> addOrUpdateWasher(List<WasherDto> washerDtoList){
		List<Washer> washerList = new ArrayList<>();
		for(WasherDto washerDto : washerDtoList) {
			Washer washer = washerRepository.findByWasherId(washerDto.getWasherId());
			if(null == washer) {
				washer = new Washer();
				washer.setWasherId(washerDto.getWasherId());
			}
				washer.setEmailId(washerDto.getEmailId());
				washer.setName(washerDto.getName());
				washer.setPhoneNumber(washerDto.getPhoneNumber());
				washer.setLastRevision(OCWConstants.YES_CHAR);
				washer.setRatings(washerDto.getRatings());
				
				washerList.add(washer);
		}
		washerRepository.saveAll(washerList);
		return getAllWashers();
	}
	
	public List<WasherDto> activeOrInactiveWasher(String status) {
		List<Washer> washerList = washerRepository.findByStatus(status);
		List<WasherDto> washerDtoList = new ArrayList<>();
		washerList.stream().forEach(washer->{
			WasherDto washerDto = new WasherDto();
			washerDto.setWasherId(washer.getWasherId());
			washerDto.setName(washer.getName());
			washerDto.setOrderList(washer.getOrderList());
			washerDto.setEmailId(washer.getEmailId());
			washerDtoList.add(washerDto);
		});
		
		return washerDtoList;
	}
	
	public List<ReviewRatingDto> viewWasherRating(String washerid) {
		List<ReviewRatingDto> reviewRatingDto = new ArrayList<>();
		
		List<ReviewRatings> reviews = reviewRatingsRepository.findByWasherId(washerid);
		reviews.stream().forEach(reviewsDb -> {
			ReviewRatingDto ratingDto = new ReviewRatingDto();
			ratingDto.setCustomerId(reviewsDb.getCustomerId());
			ratingDto.setWasherId(reviewsDb.getWasherId());
			ratingDto.setRatings(reviewsDb.getRatings());
			ratingDto.setReviews(reviewsDb.getReviews());
			reviewRatingDto.add(ratingDto);
		});
		return reviewRatingDto;
	}
	
	public ByteArrayInputStream washerReportToExcelFile() throws OcwException{
		
		try(Workbook workbook = new XSSFWorkbook()){
			 List<WasherDto> washers = getAllWashers();
				
			Sheet sheet = workbook.createSheet("Washers");
			
			Row row = sheet.createRow(0);
	     //   CellStyle headerCellStyle = workbook.createCellStyle();
	     //   headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        
	        Cell cell = row.createCell(0);
	        cell.setCellValue("WasherId");
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Name");

	        cell = row.createCell(2);
	        cell.setCellValue("Mobile");
	
	        cell = row.createCell(3);
	        cell.setCellValue("Email");
	        
	        cell = row.createCell(4);
	        cell.setCellValue("Ratings");
	        
	       
			// Creating data rows for each washer
	        for(int i = 0; i < washers.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(washers.get(i).getWasherId());
	        	dataRow.createCell(1).setCellValue(washers.get(i).getName());
	        	dataRow.createCell(2).setCellValue(washers.get(i).getPhoneNumber());
	        	dataRow.createCell(3).setCellValue(washers.get(i).getEmailId());
	        	dataRow.createCell(4).setCellValue(washers.get(i).getRatings());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        
	       /* FileOutputStream outputStream = new FileOutputStream("WasherReport.xlsx");
	            workbook.write(outputStream);
	        */
	        ByteArrayOutputStream outputStreambyte = new ByteArrayOutputStream();
	        workbook.write(outputStreambyte);
	        return new ByteArrayInputStream(outputStreambyte.toByteArray());
		} catch (IOException ex) {
			throw new OcwException("Could not create Excel Sheet" + ex.getMessage());
		}
	}
	
	public WasherDto getWasherById(String washerId) {
		WasherDto washerDto = new WasherDto();
		Washer washer = washerRepository.findByWasherId(washerId);
		if(null != washer) {
			washerDto.setWasherId(washer.getWasherId());
			washerDto.setName(washer.getName());
			washerDto.setOrderList(washer.getOrderList());
			washerDto.setEmailId(washer.getEmailId());
		}
		return washerDto;
	}

	public List<WasherDto> leaderboard(){
		Map<WasherDto,Integer> washerMap = new HashMap<>();
		List<WasherDto> allWashers = getAllWashers();
		allWashers.stream().forEach(washer -> {
			washerMap.put(washer,washer.getOrderList().size());
			
		});
		washerMap.entrySet()
                .stream()
                .sorted((Map.Entry.<WasherDto, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	return washerMap.keySet().stream().collect(Collectors.toList());
    
	}
}
