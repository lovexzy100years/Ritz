package com.ritz.health.service;

import com.ritz.health.entity.PageResult;
import com.ritz.health.entity.QueryPageBean;

public interface ITcmConstitutionIdentificationService {

    PageResult queryTcmConstitutionIdentificationListByCondition(QueryPageBean queryPageBean);
}
