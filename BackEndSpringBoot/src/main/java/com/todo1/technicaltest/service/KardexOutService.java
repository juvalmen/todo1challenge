package com.todo1.technicaltest.service;

import com.todo1.technicaltest.dto.KardexOutDto;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

public interface KardexOutService {
	boolean saveKardexOut(KardexOutDto kardexOutDto) throws Todo1CustomException;
	Long getLastSequence() throws Todo1CustomException;
}
