package com.thesis.pdm.hallergen;

public class RecommendedMacronutrientsIntakesPerDay {


    private double weight;
    private double energy;
    private int protein;
    private double aLinolenicAcid;
    private double linolenicAcid;
    private int diataryFiberMin;
    private int diataryFiberMax;
    private int waterMin;


    public RecommendedMacronutrientsIntakesPerDay(Party party) {

        if (party.getLifeStageAgeGroup() == Constant.INFANT) {
            if (party.getAge() >= 0 && party.getAge() <= 5) {
                if (party.getGender() == Constant.FEMALE) {
                    setWeight(6.0);
                    setEnergy(560);
                    setProtein(8);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(4.5);
                    setDiataryFiberMin(0);
                    setDiataryFiberMax(0);
                    setWaterMin(560);
                } else if (party.getGender() == Constant.MALE) {
                    setWeight(6.5);
                    setEnergy(620);
                    setProtein(9);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(4.5);
                    setDiataryFiberMin(0);
                    setDiataryFiberMax(0);
                    setWaterMin(680);
                }
            } else if (party.getAge() >= 6 && party.getAge() <= 11) {
                if (party.getGender() == Constant.FEMALE) {
                    setWeight(8.0);
                    setEnergy(630);
                    setProtein(15);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(4.5);
                    setDiataryFiberMin(0);
                    setDiataryFiberMax(0);
                    setWaterMin(630);
                } else if (party.getGender() == Constant.MALE) {
                    setWeight(9);
                    setEnergy(720);
                    setProtein(17);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(4.5);
                    setDiataryFiberMin(0);
                    setDiataryFiberMax(0);
                    setWaterMin(890);
                }
            }
        } else if (party.getLifeStageAgeGroup() == Constant.CHILDREN) {
            if (party.getAge() >= 1 && party.getAge() <= 2) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(120);
                    setEnergy(1000);
                    setProtein(18);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(4.5);
                    setDiataryFiberMin(6);
                    setDiataryFiberMax(7);
                    setWaterMin(1000);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(11.5);
                    setEnergy(920);
                    setProtein(17);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(3.0);
                    setDiataryFiberMin(6);
                    setDiataryFiberMax(7);
                    setWaterMin(920);
                }
            } else if (party.getAge() >= 3 && party.getAge() <= 5) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(17.5);
                    setEnergy(1350);
                    setProtein(22);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(8);
                    setDiataryFiberMax(10);
                    setWaterMin(1350);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(17.0);
                    setEnergy(1260);
                    setProtein(21);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(8);
                    setDiataryFiberMax(10);
                    setWaterMin(1260);
                }
            } else if (party.getAge() >= 6 && party.getAge() <= 9) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(23.0);
                    setEnergy(1660);
                    setProtein(30);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(11);
                    setDiataryFiberMax(14);
                    setWaterMin(1600);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(22.5);
                    setEnergy(1470);
                    setProtein(29);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(11);
                    setDiataryFiberMax(14);
                    setWaterMin(1470);
                }
            } else if (party.getAge() >= 10 && party.getAge() <= 12) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(33.0);
                    setEnergy(2060);
                    setProtein(43);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(15);
                    setDiataryFiberMax(17);
                    setWaterMin(2060);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(36.0);
                    setEnergy(1980);
                    setProtein(46);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(15);
                    setDiataryFiberMax(17);
                    setWaterMin(1980);
                }
            } else if (party.getAge() >= 13 && party.getAge() <= 15) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(48.5);
                    setEnergy(2700);
                    setProtein(62);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(18);
                    setDiataryFiberMax(20);
                    setWaterMin(2700);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(46.0);
                    setEnergy(2170);
                    setProtein(57);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(18);
                    setDiataryFiberMax(20);
                    setWaterMin(2170);
                }
            } else if (party.getAge() >= 6 && party.getAge() <= 18) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(59.0);
                    setEnergy(3010);
                    setProtein(43);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(23);
                    setWaterMin(3010);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(51.5);
                    setEnergy(2280);
                    setProtein(61);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(23);
                    setWaterMin(2280);
                }
            }

        } else if (party.getLifeStageAgeGroup() == Constant.ADULT) {
            if (party.getAge() >= 19 && party.getAge() <= 29) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(60.5);
                    setEnergy(2530);
                    setProtein(71);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(25);
                    setWaterMin(2530);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(52.5);
                    setEnergy(1930);
                    setProtein(62);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(25);
                    setWaterMin(1930);
                }
            } else if (party.getAge() >= 30 && party.getAge() <= 49) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(60.5);
                    setEnergy(2420);
                    setProtein(71);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(25);
                    setWaterMin(2420);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(52.5);
                    setEnergy(1870);
                    setProtein(62);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(25);
                    setWaterMin(1870);
                }
            } else if (party.getAge() >= 50 && party.getAge() <= 59) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(60.5);
                    setEnergy(2420);
                    setProtein(71);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(25);
                    setWaterMin(2420);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(52.5);
                    setEnergy(1870);
                    setProtein(62);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(25);
                    setWaterMin(1870);
                }
            } else if (party.getAge() >= 70) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(60.5);
                    setEnergy(1960);
                    setProtein(71);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(25);
                    setWaterMin(1960);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(52.5);
                    setEnergy(1540);
                    setProtein(62);
                    setaLinolenicAcid(0.5);
                    setLinolenicAcid(2.0);
                    setDiataryFiberMin(20);
                    setDiataryFiberMax(25);
                    setWaterMin(1540);
                }
            }
        }


        if (party.getGender() == Constant.FEMALE && party.getLifeStageAgeGroup() != Constant.INFANT){
            if (party.getPregnant() == Constant.YES) {
                this.setEnergy(this.getEnergy() + 300);
                this.setWaterMin(this.getWaterMin() + 300);
            }
            if (party.getLactating() == Constant.YES) {
                this.setEnergy(this.getEnergy() + 500);
                this.setWaterMin(this.getWaterMin() + 500);
            }
        }

    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {


        this.energy = energy;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public double getaLinolenicAcid() {
        return aLinolenicAcid;
    }

    public void setaLinolenicAcid(double aLinolenicAcid) {
        this.aLinolenicAcid = aLinolenicAcid;
    }

    public double getLinolenicAcid() {
        return linolenicAcid;
    }

    public void setLinolenicAcid(double linolenicAcid) {
        this.linolenicAcid = linolenicAcid;
    }

    public int getDiataryFiberMin() {
        return diataryFiberMin;
    }

    public void setDiataryFiberMin(int diataryFiberMin) {
        this.diataryFiberMin = diataryFiberMin;
    }

    public int getDiataryFiberMax() {
        return diataryFiberMax;
    }

    public void setDiataryFiberMax(int diataryFiberMax) {
        this.diataryFiberMax = diataryFiberMax;
    }

    public int getWaterMin() {
        return waterMin;
    }

    public void setWaterMin(int waterMin) {
        this.waterMin = waterMin;
    }

    @Override
    public String toString() {
        return "RecommendedMacronutrientsIntakesPerDay{" +
                "weight=" + weight +
                ", energy=" + energy +
                ", protein=" + protein +
                ", aLinolenicAcid=" + aLinolenicAcid +
                ", linolenicAcid=" + linolenicAcid +
                ", diataryFiberMin=" + diataryFiberMin +
                ", diataryFiberMax=" + diataryFiberMax +
                ", waterMin=" + waterMin +
                '}';
    }
}
