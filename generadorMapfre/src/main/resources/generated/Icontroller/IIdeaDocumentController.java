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
@Api(value = "API idea document")
public interface IIdeaDocumentController {

	@ApiOperation(value = "Get all idea document", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/idea-document")
	ResponseEntity<List<IdeaDocumentBO>> get();

	@ApiOperation(value = "Add a idea document", response = IdeaDocumentBO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created idea document"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/idea-document")
	ResponseEntity<IdeaDocumentBO> add(@Valid @RequestBody IdeaDocumentBO input);

	@ApiOperation(value = "Update a idea document", response = IdeaDocumentBO.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated idea document"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/idea-document/{ideaDocumentId}")
	ResponseEntity<IdeaDocumentBO> update(@PathVariable Long ideaDocumentId, @Valid @RequestBody IdeaDocumentBO input);

	@ApiOperation(value = "Delete a idea document")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted idea document"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/idea-document/{ideaDocumentId}")
	ResponseEntity<IdeaDocumentBO> delete(@PathVariable Long ideaDocumentId);

}
