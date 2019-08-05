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
@Api(value = "API matrix risk valuation")
public interface IMatrixRiskValuationController {

	@ApiOperation(value = "Get all matrix risk valuation", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/matrix-risk-valuation")
	ResponseEntity<List<MatrixRiskValuationBO>> get();

	@ApiOperation(value = "Add a matrix risk valuation", response = MatrixRiskValuationBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created matrix risk valuation"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/matrix-risk-valuation")
	ResponseEntity<MatrixRiskValuationBO> add(@Valid @RequestBody MatrixRiskValuationBO input);

	@ApiOperation(value = "Update a matrix risk valuation", response = MatrixRiskValuationBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated matrix risk valuation"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/matrix-risk-valuation/{matrixRiskValuationId}")
	ResponseEntity<MatrixRiskValuationBO> update(@PathVariable Long matrixRiskValuationId, @Valid @RequestBody MatrixRiskValuationBO input);

	@ApiOperation(value = "Delete a matrix risk valuation")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted matrix risk valuation"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/matrix-risk-valuation/{matrixRiskValuationId}")
	ResponseEntity<MatrixRiskValuationBO> delete(@PathVariable Long matrixRiskValuationId);

}
