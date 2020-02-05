package com.thesis.pdm.hallergen;

public class RecommendedEnergyIntakesPerDay {

    private double weight;
    private double energy;

    public RecommendedEnergyIntakesPerDay(Party party) {
        if (party.getLifeStageAgeGroup() == Constant.INFANT) {
            if (party.getAge() >= 0 && party.getAge() <= 5) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(6.5);
                    setEnergy(620);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(6.0);
                    setEnergy(560);

                }
            } else if (party.getAge() >= 6 && party.getAge() <= 11) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(9.0);
                    setEnergy(720);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(8.0);
                    setEnergy(630);

                }
            }
        } else if (party.getLifeStageAgeGroup() == Constant.CHILDREN) {
            if (party.getAge() >= 1 && party.getAge() <= 2) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(12.0);
                    setEnergy(1000);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(11.5);
                    setEnergy(920);

                }
            } else if (party.getAge() >= 3 && party.getAge() <= 5) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(17.5);
                    setEnergy(1350);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(17.0);
                    setEnergy(1260);

                }
            } else if (party.getAge() >= 6 && party.getAge() <= 9) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(23.0);
                    setEnergy(1600);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(22.5);
                    setEnergy(1470);

                }
            } else if (party.getAge() >= 10 && party.getAge() <= 12) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(33.0);
                    setEnergy(2060);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(36.0);
                    setEnergy(1980);

                }
            } else if (party.getAge() >= 13 && party.getAge() <= 15) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(48.5);
                    setEnergy(2700);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(46.0);
                    setEnergy(2170);

                }
            } else if (party.getAge() >= 16 && party.getAge() <= 18) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(59.0);
                    setEnergy(3010);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(51.5);
                    setEnergy(2280);

                }
            }
        } else if (party.getLifeStageAgeGroup() == Constant.ADULT) {
            if (party.getAge() >= 19 && party.getAge() <= 29) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(60.5);
                    setEnergy(2530);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(52.5);
                    setEnergy(1930);

                }
            } else if (party.getAge() >= 30 && party.getAge() <= 49) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(60.5);
                    setEnergy(2420);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(52.5);
                    setEnergy(1870);

                }
            } else if (party.getAge() >= 60 && party.getAge() <= 69) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(60.5);
                    setEnergy(2140);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(52.5);
                    setEnergy(1610);

                }
            } else if (party.getAge() >= 70) {
                if (party.getGender() == Constant.MALE) {
                    setWeight(60.5);
                    setEnergy(1960);
                } else if (party.getGender() == Constant.FEMALE) {
                    setWeight(52.5);
                    setEnergy(1540);

                }
            }
        }


        if (party.getGender() == Constant.FEMALE) {
            if (party.getPregnant() == Constant.YES) {
                this.setEnergy(this.getEnergy() + 300);
            }
            if (party.getLactating() == Constant.YES) {
                this.setEnergy(this.getEnergy() + 500);
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

    @Override
    public String toString() {
        return "RecommendedEnergyIntakesPerDay{" +
                "weight=" + weight +
                ", energy=" + energy +
                '}';
    }
}
