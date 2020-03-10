package ua.epam.springintegrationhomework.model;

import java.util.Date;

public class Package {
    private final DeliveryType deliveryType;
    private final Long id;

    public Package(DeliveryType deliveryType, Long id) {
        this.deliveryType = deliveryType;
        this.id = id;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Package{" +
                "deliveryType=" + deliveryType +
                ", id=" + id +
                '}';
    }
}
