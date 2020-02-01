package ua.epam.repository.jdbc;

import lombok.extern.log4j.Log4j;
import org.junit.*;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.AccountMapper;
import ua.epam.mapper.DeveloperMapper;
import ua.epam.mapper.SkillMapper;
import ua.epam.model.Account;
import ua.epam.model.Developer;
import ua.epam.model.Skill;
import ua.epam.repository.AccountRepository;
import ua.epam.repository.DeveloperRepository;
import ua.epam.repository.SkillRepository;
import ua.epam.repository.testUtil.TestUtil;
import ua.epam.util.ConnectionUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@Log4j
public class DeveloperRepositoryJdbcImplTest {
    private static DeveloperRepository developerRepository;

    private Account alexAccount = new Account(1L, "first account", Account.AccountStatus.ACTIVE);
    private Set<Skill> alexSkills = new HashSet<>(Arrays.asList(new Skill(1L, "java"), new Skill(5L, "sql")));
    private Developer expected = new Developer(1L, "Alex", alexAccount, alexSkills);
    private Developer toSave = new Developer(null,"test", new Account(null, "test", Account.AccountStatus.ACTIVE), alexSkills);

    @BeforeClass
    public static void prepare() {
        try {
            AccountRepository accountRepository = new AccountRepositoryJdbcImpl(new AccountMapper());
            SkillRepository skillRepository = new SkillRepositoryJdbcImpl(new SkillMapper());
            developerRepository = new DeveloperRepositoryJdbcImpl(new DeveloperMapper(accountRepository), skillRepository, accountRepository);
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
            Developer developer = developerRepository.getById(1L);
            assertEquals(expected, developer);
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testGetAll() {
        try {
            List<Developer> developers = developerRepository.getAll();
            assertEquals(2, developers.size());
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testSave() {
        try {
            developerRepository.save(toSave);
            List<Developer> developers = developerRepository.getAll();
            assertEquals(3, developers.size());
            assertEquals("test", developers.get(developers.size() - 1).getName());
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testUpdate() {
        try {
            Developer developer = developerRepository.getById(1L);
            developer.setName("test");
            Set<Skill> skills = developer.getSkills();
            skills.add(new Skill(2L, "cpp"));
            developer.setSkills(skills);
            developerRepository.update(developer);
            Developer afterUpdate = developerRepository.getById(1L);
            assertEquals("test", afterUpdate.getName());
            assertEquals(3, afterUpdate.getSkills().size());
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testDelete() {
        try {
            List<Developer> beforeDelete = developerRepository.getAll();
            Developer developer = developerRepository.getById(1L);
            developerRepository.delete(developer);
            List<Developer> afterDelete = developerRepository.getAll();
            assertEquals(beforeDelete.size(), afterDelete.size() + 1);
        } catch (PersistException e) {
            log.error(e);
            fail();
        }
    }
}