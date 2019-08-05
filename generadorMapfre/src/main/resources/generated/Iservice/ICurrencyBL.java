package com.mapfre.gaia.amap3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ICurrencyBL {

	/**
	 * Get all currency
	 */
	List<CurrencyBO> getAll();
	
	/**
	 * Add a currency
	 */
	CurrencyBO add(CurrencyBO currencyBo);

	/**
	 * Update a currency
	 */
	CurrencyBO update(Long closePeriodId, CurrencyBO currencyBo);

    /**
     * Delete a currency
     */
    boolean delete(Long currencyId);

}
