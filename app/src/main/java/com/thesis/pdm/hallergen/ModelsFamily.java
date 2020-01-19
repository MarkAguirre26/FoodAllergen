package com.thesis.pdm.hallergen;

import java.util.ArrayList;
import java.util.Date;

public class ModelsFamily {

    private String familyUID;
    private String family_GUID;
    private String accountUID;


    private int imageResourcesId;
    private String name;
    private String relation;
    private String birthday;
    private double height;
    private double weight;
    private boolean gender;
    private boolean pregnant;

    private ArrayList<ModelsAllergy> allergylist;

    public ArrayList<ModelsAllergy> getAllergylist() {
        return allergylist;
    }

    public void setAllergylist(ArrayList<ModelsAllergy> allergylist) {
        this.allergylist = allergylist;
    }

    public String getFamily_GUID() {
        return family_GUID;
    }

    public void setFamily_GUID(String family_GUID) {
        this.family_GUID = family_GUID;
    }

    public String getAccountUID() {
        return accountUID;
    }

    public void setAccountUID(String accountUID) {
        this.accountUID = accountUID;
    }





    public String getFamilyUID() { return familyUID; }

    public void setFamilyUID(String familyUID) { this.familyUID = familyUID; }

    public int getImageResourcesId() {
        return imageResourcesId;
    }

    public void setImageResourcesId(int imageResourcesId) { this.imageResourcesId = imageResourcesId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isPregnant() { return pregnant; }

    public void setPregnant(boolean pregnant) { this.pregnant = pregnant; }


}
