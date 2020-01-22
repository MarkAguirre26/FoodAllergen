package com.thesis.pdm.hallergen;

public class CalloriesBaseOnAgeAndGender {

    public ModelEnergy getEnergyBaseLine(ModelEnergy energy) {

        int age = energy.getAge();
        String gender = energy.getGender();
        String ageState = energy.getAgeState();


        ModelEnergy e = new ModelEnergy();

        if (ageState.equalsIgnoreCase("Infant")) {
//        Infant 0-5
            if (age >= 0 && age <= 5) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(6.5);
                    e.setEnergy(620);
                } else {
                    e.setWeight(6.0);
                    e.setEnergy(560);
                }
            }
            //        Infant 6-11
            if (age >= 6 && age <= 11) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(9.0);
                    e.setEnergy(720);
                } else {
                    e.setWeight(8.0);
                    e.setEnergy(630);
                }


            }
        } else {


            //        Children 1-2
            if (age >= 1 && age <= 2) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(12.0);
                    e.setEnergy(1000);
                } else {
                    e.setWeight(11.5);
                    e.setEnergy(920);
                }

            }

            //        Children 3-5
            if (age >= 3 && age <= 5) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(17.5);
                    e.setEnergy(1350);
                } else {
                    e.setWeight(17.0);
                    e.setEnergy(1260);
                }

            }

            //        Children 6-9
            if (age >= 6 && age <= 9) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(23.0);
                    e.setEnergy(1600);
                } else {
                    e.setWeight(22.5);
                    e.setEnergy(1470);
                }

            }

            //        Children 10-12
            if (age >= 10 && age <= 12) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(33.0);
                    e.setEnergy(2060);
                } else {
                    e.setWeight(36.0);
                    e.setEnergy(1980);
                }

            }

            //        Children 13-15
            if (age >= 13 && age <= 15) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(48.5);
                    e.setEnergy(2700);
                } else {
                    e.setWeight(46.0);
                    e.setEnergy(2170);
                }

            }


            //        Children 16-18
            if (age >= 16 && age <= 18) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(59.0);
                    e.setEnergy(3010);
                } else {
                    e.setWeight(51.5);
                    e.setEnergy(2280);
                }

            }


            //        Adult 19-29
            if (age >= 19 && age <= 29) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(60.5);
                    e.setEnergy(2530);
                } else {
                    e.setWeight(52.5);
                    e.setEnergy(1930);
                }

            }


            //        Adult 30-49
            if (age >= 30 && age <= 49) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(60.5);
                    e.setEnergy(2420);
                } else {
                    e.setWeight(52.5);
                    e.setEnergy(1870);
                }
            }
            //        Adult 50-59
            if (age >= 50 && age <= 59) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(60.5);
                    e.setEnergy(2420);
                } else {
                    e.setWeight(52.5);
                    e.setEnergy(1870);
                }

            }

            //        Adult 60-69
            if (age >= 60 && age <= 69) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(60.5);
                    e.setEnergy(2140);
                } else {
                    e.setWeight(52.5);
                    e.setEnergy(1610);
                }

            }

            //        Adult 60-69
            if (age >= 70) {
                if (gender.equalsIgnoreCase("m")) {
                    e.setWeight(60.5);
                    e.setEnergy(1960);
                } else {
                    e.setWeight(52.5);
                    e.setEnergy(1540);
                }

            }


            if (energy.isPregnant()) {
                int kcal = e.getEnergy() + 300;
                e.setEnergy(kcal);
            }
            if (energy.isLactating()) {
                int value = e.getEnergy() + 500;
                e.setEnergy(value);
            }

        }

        return e;

    }


}
