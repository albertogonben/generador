package ${package};

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface I${name}BL {

	/**
	 * Get all ${descripcion}
	 */
	List<${name}BO> getAll();
	
	/**
	 * Add a ${descripcion}
	 */
	${name}BO add(${name}BO ${nameMIN}Bo);

	/**
	 * Update a ${descripcion}
	 */
	${name}BO update(Long ${nameMIN}Id, ${name}BO ${nameMIN}Bo);

    /**
     * Delete a ${descripcion}
     */
    boolean delete(Long ${nameMIN}Id);

}
