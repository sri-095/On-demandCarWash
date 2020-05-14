package com.capg.ocw.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ocw.exception.OcwException;
import com.capg.ocw.model.dto.ReviewRatingDto;
import com.capg.ocw.model.dto.WasherDto;
import com.capg.ocw.operation.CutomerOperation;
import com.capg.ocw.operation.WasherOperation;
import com.capg.ocw.util.OCWConstants;

@RestController
@RequestMapping("/api/usermanagement")
public class UserManagementController {
	
	@Autowired
	private WasherOperation washerOperation;
	
	@Autowired
	private CutomerOperation cutomerOperation;
	
	@GetMapping("/washer/getWasher")
	public ResponseEntity<List<WasherDto>> getAllWashers() {
		return new ResponseEntity<>(washerOperation.getAllWashers(),HttpStatus.OK);
	}
	
	@PostMapping("/washer/addUpdateWasher")
	public ResponseEntity<List<WasherDto>> addOrUpdateWasher(@RequestBody List<WasherDto> washerDtos) {
		return new ResponseEntity<>(washerOperation.addOrUpdateWasher(washerDtos),HttpStatus.OK);
	}
	
	@GetMapping("/washer/{status}")
	public ResponseEntity<List<WasherDto>> activeOrInactiveWasher(@PathVariable String status) throws OcwException {
		if(OCWConstants.ACTIVE.equalsIgnoreCase(status) || OCWConstants.INACTIVE.equalsIgnoreCase(status))
			return new ResponseEntity<>(washerOperation.activeOrInactiveWasher(status),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/washer/review-rating/{washerid}")
	public ResponseEntity<List<ReviewRatingDto>> viewWasherRating(@PathVariable String washerid) {
		List<ReviewRatingDto> washerRatings = washerOperation.viewWasherRating(washerid);
		return new ResponseEntity<>(washerRatings, HttpStatus.OK);
	}
	
	@GetMapping("/customer/review-rating/{customerid}")
	public ResponseEntity<List<ReviewRatingDto>> viewCustomerRating(@PathVariable String customerid) {
		List<ReviewRatingDto> washerRatings = cutomerOperation.viewCustomerRating(customerid);
		return new ResponseEntity<>(washerRatings, HttpStatus.OK);
	}
	
	@GetMapping(value = "/washer/download/washerReport.xlsx")
    public ResponseEntity<InputStreamResource> excelWasherReport() throws OcwException {
    ByteArrayInputStream in = washerOperation.washerReportToExcelFile();
   
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=washerReport.xlsx");
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
	
	@GetMapping("/washer/{washerid}")
	public ResponseEntity<WasherDto> getWasherById(@PathVariable String washerid) throws OcwException {
		return new ResponseEntity<>(washerOperation.getWasherById(washerid),HttpStatus.OK);
	}
	
	@GetMapping("/leaderbord")
	public ResponseEntity<List<WasherDto>> leaderboard() {
		return new ResponseEntity<>(washerOperation.leaderboard(),HttpStatus.OK);
	}
}
