package com.ritz.health.service.impl;

import com.ritz.health.dto.MemberDTO;
import com.ritz.health.dto.MemberDataDTO;
import com.ritz.health.mapper.IMemberMapper;
import com.ritz.health.service.IMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MemberServiceImpl implements IMemberService {

    @Resource
    private IMemberMapper memberMapper;

    @Override
    public MemberDataDTO getMemberTotal() {
        //1.组装查询条件
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        int nowMonth = calendar.get(Calendar.MONTH) + 1;
        int nowYear = calendar.get(Calendar.YEAR);
        System.out.println("nowDate:" + nowYear+"-"+nowMonth);
        System.out.println("======================================================>");
        //month-12
        //calendar.set(Calendar.MONTH,-3);
        calendar.add(Calendar.YEAR,-1);
        int pastMonth = calendar.get(Calendar.MONTH) + 1;//过去
        int pastYear = calendar.get(Calendar.YEAR);
        System.out.println("pastDate:" + pastYear+"-"+pastMonth);

        //2.封装查询条件
        HashMap<String, Object> dateMap = new HashMap<>();
        dateMap.put("endDate",nowYear+"-"+nowMonth+"-"+1);
        dateMap.put("startDate",pastYear+"-"+pastMonth+"-"+1);

        List<MemberDTO> memberDTOList = memberMapper.findDateNumber(dateMap);
        //2.组装查询的结果集
        MemberDataDTO memberDataDTO = new MemberDataDTO();

        Integer[] memeberCount = new Integer[memberDTOList.size()];
        String[] months = new String[memberDTOList.size()];
        for (int i = 0; i < memberDTOList.size(); i++) {
            MemberDTO memberDTO = memberDTOList.get(i);
            memeberCount[i] = memberDTO.getNumber();
            months[i] = memberDTO.getMonth();
        }
        memberDataDTO.setMonths(months);
        memberDataDTO.setMemberCount(memeberCount);
        //每月的数据还有单独的封装

        return memberDataDTO;
    }
}
