package ua.epam.dto;

import java.util.Objects;

public class AccountStatusDto {

  private Long id;
  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountStatusDto dto = (AccountStatusDto) o;
    return Objects.equals(id, dto.id) &&
        Objects.equals(status, dto.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status);
  }
}
