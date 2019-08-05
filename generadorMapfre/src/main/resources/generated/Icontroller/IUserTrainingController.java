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
@Api(value = "API user training")
public interface IUserTrainingController {

	@ApiOperation(value = "Get all user training", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/user-training")
	ResponseEntity<List<UserTrainingBO>> get();

	@ApiOperation(value = "Add a user training", response = UserTrainingBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created user training"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/user-training")
	ResponseEntity<UserTrainingBO> add(@Valid @RequestBody UserTrainingBO input);

	@ApiOperation(value = "Update a user training", response = UserTrainingBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated user training"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/user-training/{userTrainingId}")
	ResponseEntity<UserTrainingBO> update(@PathVariable Long userTrainingId, @Valid @RequestBody UserTrainingBO input);

	@ApiOperation(value = "Delete a user training")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted user training"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/user-training/{userTrainingId}")
	ResponseEntity<UserTrainingBO> delete(@PathVariable Long userTrainingId);

}
