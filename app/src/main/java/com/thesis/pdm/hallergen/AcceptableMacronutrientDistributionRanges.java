package com.thesis.pdm.hallergen;

public class AcceptableMacronutrientDistributionRanges {
    private int INFANT = 1;
    private int CHILDREN = 2;
    private int ADULT = 3;

    private int lifeStageAgeGroup;
    private int age;


    private int proteinMin;
    private int proteinMax;
    private int totalFatMin;
    private int totalFatMax;
    private int carbohydtrateMin;
    private int carbohydtrateMax;


    public AcceptableMacronutrientDistributionRanges(Party party) {
        this.age = party.getAge();
        this.lifeStageAgeGroup = party.getLifeStageAgeGroup();


        if (lifeStageAgeGroup == INFANT) {
            if (getAge() >= 0 && getAge() <= 5) {
                setProteinMin(5);
                setCarbohydtrateMax(0);
                setTotalFatMin(40);
                setTotalFatMax(60);
                setCarbohydtrateMin(35);
                setCarbohydtrateMax(55);
            } else if (getAge() >= 6 && getAge() <= 11) {
                setProteinMin(8);
                setCarbohydtrateMax(15);
                setTotalFatMin(30);
                setTotalFatMax(40);
                setCarbohydtrateMin(45);
                setCarbohydtrateMax(32);

            }
        } else if (lifeStageAgeGroup == CHILDREN) {
            if (getAge() >= 1 && getAge() <= 2) {
                setProteinMin(6);
                setCarbohydtrateMax(15);
                setTotalFatMin(25);
                setTotalFatMax(35);
                setCarbohydtrateMin(50);
                setCarbohydtrateMax(69);

            } else if (getAge() >= 3 && getAge() <= 18) {
                setProteinMin(6);
                setCarbohydtrateMax(15);
                setTotalFatMin(15);
                setTotalFatMax(30);
                setCarbohydtrateMin(55);
                setCarbohydtrateMax(49);

            }
        } else if (lifeStageAgeGroup == ADULT) {
            if (getAge() >= 19) {
                setProteinMin(10);
                setCarbohydtrateMax(15);
                setTotalFatMin(15);
                setTotalFatMax(30);
                setCarbohydtrateMin(55);
                setCarbohydtrateMax(45);

            }
        }


    }

    public int getProteinMin() {
        return proteinMin;
    }

    public void setProteinMin(int proteinMin) {
        this.proteinMin = proteinMin;
    }

    public int getProteinMax() {
        return proteinMax;
    }

    public void setProteinMax(int proteinMax) {
        this.proteinMax = proteinMax;
    }

    public int getCarbohydtrateMin() {
        return carbohydtrateMin;
    }

    public void setCarbohydtrateMin(int carbohydtrateMin) {
        this.carbohydtrateMin = carbohydtrateMin;
    }

    public int getCarbohydtrateMax() {
        return carbohydtrateMax;
    }

    public void setCarbohydtrateMax(int carbohydtrateMax) {
        this.carbohydtrateMax = carbohydtrateMax;
    }

    public int getTotalFatMax() {
        return totalFatMax;
    }

    public void setTotalFatMax(int totalFatMax) {
        this.totalFatMax = totalFatMax;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getTotalFatMin() {
        return totalFatMin;
    }

    public void setTotalFatMin(int totalFat) {
        this.totalFatMin = totalFat;
    }


}
