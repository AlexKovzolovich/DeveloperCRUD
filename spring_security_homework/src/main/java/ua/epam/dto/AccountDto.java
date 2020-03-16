package ua.epam.dto;

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
}
