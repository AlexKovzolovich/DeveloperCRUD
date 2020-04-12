package com.epam.messaginghomework;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class RequestMessage implements Serializable {
  private final UUID id = UUID.randomUUID();

  private BigDecimal firstAddendum;

  private BigDecimal secondAddendum;

  public RequestMessage() {
  }

  public RequestMessage(BigDecimal firstAddendum, BigDecimal secondAddendum) {
    this.firstAddendum = firstAddendum;
    this.secondAddendum = secondAddendum;
  }
}
