package ${package};

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
@Api(value = "API ${descripcion}")
public interface I${name}Controller {

	@ApiOperation(value = "Get all ${descripcion}", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@GetMapping("/${path}")
	ResponseEntity<List<${beanBO}>> get();

	@ApiOperation(value = "Add a ${descripcion}", response = ${beanBO}.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created ${descripcion}"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 409, message = "The request could not be completed") ,
			@ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/${path}")
	ResponseEntity<${beanBO}> add(@Valid @RequestBody ${beanBO} input);

	@ApiOperation(value = "Update a ${descripcion}", response = ${beanBO}.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully updated ${descripcion}"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")})

	@PutMapping("/${path}/{${id}}")
	ResponseEntity<${beanBO}> update(@PathVariable Long ${id}, @Valid @RequestBody ${beanBO} input);

	@ApiOperation(value = "Delete a ${descripcion}")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully deleted ${descripcion}"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") ,
			@ApiResponse(code = 500, message = "Internal server error")})

	@DeleteMapping("/${path}/{${id}}")
	ResponseEntity<${beanBO}> delete(@PathVariable Long ${id});

}
