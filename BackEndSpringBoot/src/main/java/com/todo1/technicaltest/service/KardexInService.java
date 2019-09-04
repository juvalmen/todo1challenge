package com.todo1.technicaltest.service;

import com.todo1.technicaltest.dto.KardexInDto;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

public interface KardexInService {
	boolean saveKardexIn(KardexInDto kardexInDto) throws Todo1CustomException;
	Long getLastSequence() throws Todo1CustomException;
}
