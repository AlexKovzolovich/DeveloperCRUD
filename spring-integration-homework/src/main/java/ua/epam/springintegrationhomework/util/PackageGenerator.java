package ua.epam.springintegrationhomework.util;

import java.util.Random;
import ua.epam.springintegrationhomework.model.DeliveryType;

public class PackageGenerator {

  private final static Random random = new Random();

  public static ua.epam.springintegrationhomework.model.Package generatePackage() {
    int typeIndex = random.nextInt(3);
    DeliveryType deliveryType = DeliveryType.values()[typeIndex];
    Long id = random.nextLong();
    return new ua.epam.springintegrationhomework.model.Package(deliveryType, id);
  }

}
