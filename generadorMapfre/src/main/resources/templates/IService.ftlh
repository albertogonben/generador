package ${package};

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface I${name}BL {

	/**
	 * Get all ${descripion}
	 */
	List<${name}BO> getAll();
	
	/**
	 * Add a ${descripion}
	 */
	${name}BO add(${name}BO ${nameMIN}Bo);

	/**
	 * Update a ${descripion}
	 */
	${name}BO update(Long closePeriodId, ${name}BO ${nameMIN}Bo);

    /**
     * Delete a ${descripion}
     */
    boolean delete(Long ${nameMIN}Id);

}
