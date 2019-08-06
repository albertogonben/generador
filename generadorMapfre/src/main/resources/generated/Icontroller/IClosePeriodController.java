package com.mapfre.gaia.amap3;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "API close period")
public interface IClosePeriodController {

	@ApiOperation(value = "Get all close period", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/close-period")
	ResponseEntity<List<ClosePeriodBO>> get() throws CustomException;

	@ApiOperation(value = "Add a close period", response = ClosePeriodBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created close period"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/close-period")
	ResponseEntity<ClosePeriodBO> add(@Valid @RequestBody ClosePeriodBO input) throws CustomException;

	@ApiOperation(value = "Update a close period", response = ClosePeriodBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated close period"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/close-period/{closePeriodId}")
	ResponseEntity<ClosePeriodBO> update(@PathVariable Long closePeriodId, @Valid @RequestBody ClosePeriodBO input) throws CustomException;

	@ApiOperation(value = "Delete a close period")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted close period"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/close-period/{closePeriodId}")
	ResponseEntity<ClosePeriodBO> delete(@PathVariable Long closePeriodId) throws CustomException;

}
