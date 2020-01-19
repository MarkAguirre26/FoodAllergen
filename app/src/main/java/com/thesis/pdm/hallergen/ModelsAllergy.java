package com.thesis.pdm.hallergen;

import java.util.ArrayList;

public class ModelsAllergy {

    private String AllergyName;
    private String Description;
    private String FamilyGUID;
    private String uid;
    private ArrayList<ModelsIngredient> HarmfulIngredients;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFamilyGUID() {
        return FamilyGUID;
    }

    public void setFamilyGUID(String familyGUID) {
        FamilyGUID = familyGUID;
    }

    public String getAllergyName() {
        return AllergyName;
    }

    public void setAllergyName(String allergyName) {
        AllergyName = allergyName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ArrayList<ModelsIngredient> getHarmfulIngredients() {
        return HarmfulIngredients;
    }

    public void setHarmfulIngredients(ArrayList<ModelsIngredient> harmfulIngredients) {
        HarmfulIngredients = harmfulIngredients;
    }
}
