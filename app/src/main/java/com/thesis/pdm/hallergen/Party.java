package com.thesis.pdm.hallergen;

public class Party {
    private int lifeStageAgeGroup;
    private int age;
    private int gender;
    private int weight;
    private int pregnant;
    private int lactating;



    public int getLifeStageAgeGroup() {
        return lifeStageAgeGroup;
    }

    public int getPregnant() {
        return pregnant;
    }

    public void setPregnant(int pregnant) {
        this.pregnant = pregnant;
    }

    public int getLactating() {
        return lactating;
    }

    public void setLactating(int lactating) {
        this.lactating = lactating;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



    public void setLifeStageAgeGroup(int lifeStageAgeGroup) {
        this.lifeStageAgeGroup = lifeStageAgeGroup;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
