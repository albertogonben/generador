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
@Api(value = "API user amap")
public interface IUserAmapController {

	@ApiOperation(value = "Get all user amap", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/user-amap")
	ResponseEntity<List<UserAmapBO>> get();

	@ApiOperation(value = "Add a user amap", response = UserAmapBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created user amap"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/user-amap")
	ResponseEntity<UserAmapBO> add(@Valid @RequestBody UserAmapBO input);

	@ApiOperation(value = "Update a user amap", response = UserAmapBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated user amap"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/user-amap/{userAmapId}")
	ResponseEntity<UserAmapBO> update(@PathVariable Long userAmapId, @Valid @RequestBody UserAmapBO input);

	@ApiOperation(value = "Delete a user amap")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted user amap"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/user-amap/{userAmapId}")
	ResponseEntity<UserAmapBO> delete(@PathVariable Long userAmapId);

}
