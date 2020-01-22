package com.thesis.pdm.hallergen;

public class ModelEnergy {

    private int age;

    private double weight;
    private int energy;
    private String gender;
    private String ageState;
    private boolean isPregnant;
    private boolean isLactating;

    public ModelEnergy() {

    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public boolean isLactating() {
        return isLactating;
    }

    public void setLactating(boolean lactating) {
        isLactating = lactating;
    }

    public String getAgeState() {
        return ageState;
    }

    public void setAgeState(String ageState) {
        this.ageState = ageState;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }


}
