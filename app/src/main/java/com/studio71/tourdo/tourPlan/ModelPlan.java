package com.studio71.tourdo.tourPlan;

public class ModelPlan {

    String pic,name,spot,expense,transport,firstDay,secondDay,thirdDay,fourthDay,fifthDay,sixthDay,seventhDay;

    public ModelPlan() {
    }

    public ModelPlan(String pic, String name, String spot, String expense, String transport, String firstDay, String secondDay, String thirdDay, String fourthDay, String fifthDay, String sixthDay, String seventhDay) {
        this.pic = pic;
        this.name = name;
        this.spot = spot;
        this.expense = expense;
        this.transport = transport;
        this.firstDay = firstDay;
        this.secondDay = secondDay;
        this.thirdDay = thirdDay;
        this.fourthDay = fourthDay;
        this.fifthDay = fifthDay;
        this.sixthDay = sixthDay;
        this.seventhDay = seventhDay;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }

    public String getSecondDay() {
        return secondDay;
    }

    public void setSecondDay(String secondDay) {
        this.secondDay = secondDay;
    }

    public String getThirdDay() {
        return thirdDay;
    }

    public void setThirdDay(String thirdDay) {
        this.thirdDay = thirdDay;
    }

    public String getFourthDay() {
        return fourthDay;
    }

    public void setFourthDay(String fourthDay) {
        this.fourthDay = fourthDay;
    }

    public String getFifthDay() {
        return fifthDay;
    }

    public void setFifthDay(String fifthDay) {
        this.fifthDay = fifthDay;
    }

    public String getSixthDay() {
        return sixthDay;
    }

    public void setSixthDay(String sixthDay) {
        this.sixthDay = sixthDay;
    }

    public String getSeventhDay() {
        return seventhDay;
    }

    public void setSeventhDay(String seventhDay) {
        this.seventhDay = seventhDay;
    }
}
