package com.ritz.health.pojo;

public class TcmConstitutionIdentification {
    private Integer id;
    private String constitutionName;
    private String constitutionDesc;
    private String suitableFood;
    private String noSuitableFood;
    private String suitableCrowd;
    private String dosing;
    private String efficacy;
    private String chineseMedicineName;

    public TcmConstitutionIdentification() {
    }

    public TcmConstitutionIdentification(Integer id, String constitutionName, String constitutionDesc, String suitableFood, String noSuitableFood, String suitableCrowd, String dosing, String efficacy, String chineseMedicineName) {
        this.id = id;
        this.constitutionName = constitutionName;
        this.constitutionDesc = constitutionDesc;
        this.suitableFood = suitableFood;
        this.noSuitableFood = noSuitableFood;
        this.suitableCrowd = suitableCrowd;
        this.dosing = dosing;
        this.efficacy = efficacy;
        this.chineseMedicineName = chineseMedicineName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConstitutionName() {
        return constitutionName;
    }

    public void setConstitutionName(String constitutionName) {
        this.constitutionName = constitutionName;
    }

    public String getConstitutionDesc() {
        return constitutionDesc;
    }

    public void setConstitutionDesc(String constitutionDesc) {
        this.constitutionDesc = constitutionDesc;
    }

    public String getSuitableFood() {
        return suitableFood;
    }

    public void setSuitableFood(String suitableFood) {
        this.suitableFood = suitableFood;
    }

    public String getNoSuitableFood() {
        return noSuitableFood;
    }

    public void setNoSuitableFood(String noSuitableFood) {
        this.noSuitableFood = noSuitableFood;
    }

    public String getSuitableCrowd() {
        return suitableCrowd;
    }

    public void setSuitableCrowd(String suitableCrowd) {
        this.suitableCrowd = suitableCrowd;
    }

    public String getDosing() {
        return dosing;
    }

    public void setDosing(String dosing) {
        this.dosing = dosing;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getChineseMedicineName() {
        return chineseMedicineName;
    }

    public void setChineseMedicineName(String chineseMedicineName) {
        this.chineseMedicineName = chineseMedicineName;
    }
}
