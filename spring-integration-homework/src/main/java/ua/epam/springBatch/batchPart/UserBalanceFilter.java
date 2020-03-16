package ua.epam.springBatch.batchPart;

import org.springframework.batch.item.ItemProcessor;
import ua.epam.springBatch.model.User;

public class UserBalanceFilter implements ItemProcessor<User, User> {

  @Override
  public User process(User user) throws Exception {
    return user.getAccount().isLowBalance() ? user : null;
  }
}
