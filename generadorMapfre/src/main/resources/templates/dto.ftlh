package ${package};

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * The bussiness class for the ${name} database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${name}BO implements Serializable {

<#list parameters as param>
	${param.modifier} ${param.type} ${param.name};
</#list>

}