package com.todo1.technicaltest.service;

import java.util.List;

import com.todo1.technicaltest.dto.KardexOutDetailDto;
import com.todo1.technicaltest.model.KardexOut;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

public interface KardexOutDetailService {
	boolean saveKardexOutDetail(KardexOut kardexOut, List<KardexOutDetailDto> kardexOutDetailsDto) throws Todo1CustomException;	
}
