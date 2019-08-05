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
@Api(value = "API matrix risk")
public interface IMatrixRiskController {

	@ApiOperation(value = "Get all matrix risk", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/matrix-risk")
	ResponseEntity<List<MatrixRiskBO>> get();

	@ApiOperation(value = "Add a matrix risk", response = MatrixRiskBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created matrix risk"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/matrix-risk")
	ResponseEntity<MatrixRiskBO> add(@Valid @RequestBody MatrixRiskBO input);

	@ApiOperation(value = "Update a matrix risk", response = MatrixRiskBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated matrix risk"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/matrix-risk/{matrixRiskId}")
	ResponseEntity<MatrixRiskBO> update(@PathVariable Long matrixRiskId, @Valid @RequestBody MatrixRiskBO input);

	@ApiOperation(value = "Delete a matrix risk")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted matrix risk"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/matrix-risk/{matrixRiskId}")
	ResponseEntity<MatrixRiskBO> delete(@PathVariable Long matrixRiskId);

}
