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
@Api(value = "API factor risk")
public interface IFactorRiskController {

	@ApiOperation(value = "Get all factor risk", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/factor-risk")
	ResponseEntity<List<FactorRiskBO>> get();

	@ApiOperation(value = "Add a factor risk", response = FactorRiskBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created factor risk"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/factor-risk")
	ResponseEntity<FactorRiskBO> add(@Valid @RequestBody FactorRiskBO input);

	@ApiOperation(value = "Update a factor risk", response = FactorRiskBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated factor risk"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/factor-risk/{factorRiskId}")
	ResponseEntity<FactorRiskBO> update(@PathVariable Long factorRiskId, @Valid @RequestBody FactorRiskBO input);

	@ApiOperation(value = "Delete a factor risk")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted factor risk"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/factor-risk/{factorRiskId}")
	ResponseEntity<FactorRiskBO> delete(@PathVariable Long factorRiskId);

}
