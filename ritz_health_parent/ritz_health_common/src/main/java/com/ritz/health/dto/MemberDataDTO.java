package com.ritz.health.dto;

public class MemberDataDTO {

    private Integer[] memberCount;
    private String[] months;

    public Integer[] getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer[] memberCount) {
        this.memberCount = memberCount;
    }

    public String[] getMonths() {
        return months;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }
}
