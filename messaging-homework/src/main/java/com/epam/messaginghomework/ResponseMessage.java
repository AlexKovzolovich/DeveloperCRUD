package com.epam.messaginghomework;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class ResponseMessage {

  private UUID id;

  private BigDecimal sum;

  public ResponseMessage() {
  }

  public ResponseMessage(UUID id, BigDecimal sum) {
    this.id = id;
    this.sum = sum;
  }
}
