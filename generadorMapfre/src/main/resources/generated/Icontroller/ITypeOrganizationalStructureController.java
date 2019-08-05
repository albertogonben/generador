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
@Api(value = "API type organizational structure")
public interface ITypeOrganizationalStructureController {

	@ApiOperation(value = "Get all type organizational structure", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/type-organizational-structure")
	ResponseEntity<List<TypeOrganizationalStructureBO>> get();

	@ApiOperation(value = "Add a type organizational structure", response = TypeOrganizationalStructureBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created type organizational structure"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/type-organizational-structure")
	ResponseEntity<TypeOrganizationalStructureBO> add(@Valid @RequestBody TypeOrganizationalStructureBO input);

	@ApiOperation(value = "Update a type organizational structure", response = TypeOrganizationalStructureBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated type organizational structure"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/type-organizational-structure/{typeOrganizationalStructureId}")
	ResponseEntity<TypeOrganizationalStructureBO> update(@PathVariable Long typeOrganizationalStructureId, @Valid @RequestBody TypeOrganizationalStructureBO input);

	@ApiOperation(value = "Delete a type organizational structure")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted type organizational structure"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/type-organizational-structure/{typeOrganizationalStructureId}")
	ResponseEntity<TypeOrganizationalStructureBO> delete(@PathVariable Long typeOrganizationalStructureId);

}
