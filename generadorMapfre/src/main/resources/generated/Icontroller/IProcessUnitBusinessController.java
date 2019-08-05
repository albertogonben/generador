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
@Api(value = "API process unit business")
public interface IProcessUnitBusinessController {

	@ApiOperation(value = "Get all process unit business", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/process-unit-business")
	ResponseEntity<List<ProcessUnitBusinessBO>> get();

	@ApiOperation(value = "Add a process unit business", response = ProcessUnitBusinessBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created process unit business"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/process-unit-business")
	ResponseEntity<ProcessUnitBusinessBO> add(@Valid @RequestBody ProcessUnitBusinessBO input);

	@ApiOperation(value = "Update a process unit business", response = ProcessUnitBusinessBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated process unit business"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/process-unit-business/{processUnitBusinessId}")
	ResponseEntity<ProcessUnitBusinessBO> update(@PathVariable Long processUnitBusinessId, @Valid @RequestBody ProcessUnitBusinessBO input);

	@ApiOperation(value = "Delete a process unit business")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted process unit business"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/process-unit-business/{processUnitBusinessId}")
	ResponseEntity<ProcessUnitBusinessBO> delete(@PathVariable Long processUnitBusinessId);

}
