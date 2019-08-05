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
@Api(value = "API area territorial")
public interface IAreaTerritorialController {

	@ApiOperation(value = "Get all area territorial", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/area-territorial")
	ResponseEntity<List<AreaTerritorialBO>> get();

	@ApiOperation(value = "Add a area territorial", response = AreaTerritorialBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created area territorial"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/area-territorial")
	ResponseEntity<AreaTerritorialBO> add(@Valid @RequestBody AreaTerritorialBO input);

	@ApiOperation(value = "Update a area territorial", response = AreaTerritorialBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated area territorial"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/area-territorial/{areaTerritorialId}")
	ResponseEntity<AreaTerritorialBO> update(@PathVariable Long areaTerritorialId, @Valid @RequestBody AreaTerritorialBO input);

	@ApiOperation(value = "Delete a area territorial")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted area territorial"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/area-territorial/{areaTerritorialId}")
	ResponseEntity<AreaTerritorialBO> delete(@PathVariable Long areaTerritorialId);

}
