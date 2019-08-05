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
@Api(value = "API prediction personnel")
public interface IPredictionPersonnelController {

	@ApiOperation(value = "Get all prediction personnel", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/prediction-personnel")
	ResponseEntity<List<PredictionPersonnelBO>> get();

	@ApiOperation(value = "Add a prediction personnel", response = PredictionPersonnelBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created prediction personnel"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/prediction-personnel")
	ResponseEntity<PredictionPersonnelBO> add(@Valid @RequestBody PredictionPersonnelBO input);

	@ApiOperation(value = "Update a prediction personnel", response = PredictionPersonnelBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated prediction personnel"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/prediction-personnel/{predictionPersonnelId}")
	ResponseEntity<PredictionPersonnelBO> update(@PathVariable Long predictionPersonnelId, @Valid @RequestBody PredictionPersonnelBO input);

	@ApiOperation(value = "Delete a prediction personnel")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted prediction personnel"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/prediction-personnel/{predictionPersonnelId}")
	ResponseEntity<PredictionPersonnelBO> delete(@PathVariable Long predictionPersonnelId);

}
