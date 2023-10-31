package com.ritz.health.mapper;

import com.ritz.health.dto.MemberDTO;

import java.util.HashMap;
import java.util.List;

public interface IMemberMapper {

    List<MemberDTO> findDateNumber(HashMap<String, Object> dateMap);
}
