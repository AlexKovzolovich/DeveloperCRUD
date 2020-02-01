package ua.epam.repository.jdbc;

import org.junit.*;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.SkillMapper;
import ua.epam.model.Skill;
import ua.epam.repository.SkillRepository;
import ua.epam.repository.testUtil.TestUtil;
import ua.epam.util.ConnectionUtil;

import java.util.List;

import static org.junit.Assert.*;

public class SkillRepositoryJdbcImplTest {
    private static SkillRepository skillRepository;
    private Skill expectedSkill = new Skill(1L, "java");
    private Skill toSave = new Skill(null,"test");

    @BeforeClass
    public static void prepare() {
        try {
            skillRepository = new SkillRepositoryJdbcImpl(new SkillMapper());
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
            Skill loadedSkill = skillRepository.getById(1L);
            assertEquals(expectedSkill, loadedSkill);
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testGetAll() {
        try {
            List<Skill> allSkills = skillRepository.getAll();
            assertEquals(5, allSkills.size());
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testSave() {
        try {
            skillRepository.save(toSave);
            List<Skill> skills = skillRepository.getAll();
            assertEquals(6, skills.size());
            assertEquals("test", skills.get(skills.size() - 1).getName());
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testUpdate() {
        try {
            Skill skill = skillRepository.getById(1L);
            skill.setName("test");
            skillRepository.update(skill);
            assertEquals(skill, skillRepository.getById(skill.getId()));
        } catch (PersistException e) {
            fail();
        }
    }

    @Test
    public void testDelete() {
        try {
            Skill skill = skillRepository.getById(2L);
            List<Skill> beforeDelete = skillRepository.getAll();
            skillRepository.delete(skill);
            List<Skill> afterDelete = skillRepository.getAll();
            assertEquals(beforeDelete.size(), afterDelete.size() + 1);
        } catch (PersistException e) {
            fail();
        }
    }
}