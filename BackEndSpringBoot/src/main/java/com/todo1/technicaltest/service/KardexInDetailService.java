package com.todo1.technicaltest.service;

import java.util.List;

import com.todo1.technicaltest.dto.KardexInDetailDto;
import com.todo1.technicaltest.model.KardexIn;
import com.todo1.technicaltest.utils.exception.Todo1CustomException;

public interface KardexInDetailService {
	boolean saveKardexInDetail(KardexIn kardexIn, List<KardexInDetailDto> kardexInDetailsDto) throws Todo1CustomException;	
}
