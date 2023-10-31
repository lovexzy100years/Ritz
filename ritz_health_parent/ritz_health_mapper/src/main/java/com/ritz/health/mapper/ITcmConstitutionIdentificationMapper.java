package com.ritz.health.mapper;

import com.ritz.health.pojo.TcmConstitutionIdentification;

import java.util.List;

public interface ITcmConstitutionIdentificationMapper {

    List<TcmConstitutionIdentification> findTcmConstitutionIdentificationListByCondition();
}
