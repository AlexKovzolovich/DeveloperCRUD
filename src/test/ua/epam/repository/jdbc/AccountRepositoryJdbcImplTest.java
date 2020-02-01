package ua.epam.repository.jdbc;

import lombok.extern.log4j.Log4j;
import org.junit.*;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.AccountMapper;
import ua.epam.model.Account;
import ua.epam.repository.AccountRepository;
import ua.epam.repository.testUtil.TestUtil;
import ua.epam.util.ConnectionUtil;

import java.util.List;

import static org.junit.Assert.*;

@Log4j
public class AccountRepositoryJdbcImplTest {
    private static AccountRepository accountRepository;
    private Account expectedAccount = new Account(1L, "first account", Account.AccountStatus.ACTIVE);
    private String data = "test";
    private Account toSave = new Account(null, data, Account.AccountStatus.BANNED);

    @BeforeClass
    public static void prepare() {
        try {
            accountRepository = new AccountRepositoryJdbcImpl(new AccountMapper());
            ConnectionUtil.changeToTestMod();
        } catch (PersistException e) {
            fail();
        }
    }

    @Before
    public void populateDB() {
        TestUtil.initTestDB();
        TestUtil.populateDB();
    }

    @After
    public void dropData() {
        TestUtil.dropDB();
    }

    @Test
    public void testGetById() {
        try {
            Account account = accountRepository.getById(1L);
            assertEquals(expectedAccount, account);
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testGetAll() {
        try {
            List<Account> accounts = accountRepository.getAll();
            assertEquals(2,accounts.size());
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testSave() {
        try {
            accountRepository.save(toSave);
            List<Account> accounts = accountRepository.getAll();
            assertEquals(3, accounts.size());
            assertEquals(data, accounts.get(accounts.size() - 1).getData());
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testUpdate() {
        try {
            Account account = accountRepository.getById(1L);
            account.setData("update");
            accountRepository.update(account);
            assertEquals(account, accountRepository.getById(account.getId()));
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testDelete() {
        try {
            accountRepository.save(toSave);
            List<Account> beforeDelete = accountRepository.getAll();
            accountRepository.delete(beforeDelete.get(beforeDelete.size() - 1));
            List<Account> afterDelete = accountRepository.getAll();
            assertEquals(beforeDelete.size(), afterDelete.size() + 1);
        } catch (PersistException e) {
            log.error(e);
            fail();
        }
    }
}