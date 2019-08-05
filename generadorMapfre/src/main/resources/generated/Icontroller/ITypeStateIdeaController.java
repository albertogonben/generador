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
@Api(value = "API type state idea")
public interface ITypeStateIdeaController {

	@ApiOperation(value = "Get all type state idea", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/type-state-idea")
	ResponseEntity<List<TypeStateIdeaBO>> get();

	@ApiOperation(value = "Add a type state idea", response = TypeStateIdeaBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created type state idea"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/type-state-idea")
	ResponseEntity<TypeStateIdeaBO> add(@Valid @RequestBody TypeStateIdeaBO input);

	@ApiOperation(value = "Update a type state idea", response = TypeStateIdeaBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated type state idea"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/type-state-idea/{typeStateIdeaId}")
	ResponseEntity<TypeStateIdeaBO> update(@PathVariable Long typeStateIdeaId, @Valid @RequestBody TypeStateIdeaBO input);

	@ApiOperation(value = "Delete a type state idea")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted type state idea"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/type-state-idea/{typeStateIdeaId}")
	ResponseEntity<TypeStateIdeaBO> delete(@PathVariable Long typeStateIdeaId);

}
