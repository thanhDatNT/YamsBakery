package com.thanhdat.yams.Models;

import java.io.Serializable;

public class ChooseTime implements Serializable {

    private double timeDelivery;
    private double dateDelivery;

    public ChooseTime(double timeDelivery, double dateDelivery) {
        this.timeDelivery = timeDelivery;
        this.dateDelivery = dateDelivery;
    }

    public double getTimeDelivery() {
        return timeDelivery;
    }

    public void setTimeDelivery(double timeDelivery) {
        this.timeDelivery = timeDelivery;
    }

    public double getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(double dateDelivery) {
        this.dateDelivery = dateDelivery;
    }
}
