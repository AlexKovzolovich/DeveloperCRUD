package ua.epam.dto;

import java.util.Objects;

public class AccountDto {

  private Long id;
  private String data;
  private AccountStatusDto accountStatusDto;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public AccountStatusDto getAccountStatusDto() {
    return accountStatusDto;
  }

  public void setAccountStatusDto(AccountStatusDto accountStatusDto) {
    this.accountStatusDto = accountStatusDto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountDto dto = (AccountDto) o;
    return Objects.equals(id, dto.id) &&
        Objects.equals(data, dto.data) &&
        Objects.equals(accountStatusDto, dto.accountStatusDto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, data, accountStatusDto);
  }
}
