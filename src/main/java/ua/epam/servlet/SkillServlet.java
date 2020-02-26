package ua.epam.servlet;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.SkillMapper;
import ua.epam.model.Skill;
import ua.epam.repository.jdbc.SkillRepositoryJdbcImpl;
import ua.epam.service.serviceImpl.SkillServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Log4j
public class SkillServlet extends HttpServlet {
    private SkillServiceImpl skillService;
    private final Gson gson = new Gson();

    {
        try {
            skillService = new SkillServiceImpl(new SkillRepositoryJdbcImpl(new SkillMapper()));
        } catch (PersistException e) {
            log.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Skill skill = gson.fromJson(request.getReader(), Skill.class);
        skillService.save(skill);

        response.sendRedirect("/api/v1/skill");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Skill skill = gson.fromJson(request.getReader(), Skill.class);
        skillService.update(skill);

        response.sendRedirect("/api/v1/skill");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Skill skill = gson.fromJson(request.getReader(), Skill.class);
        skillService.delete(skill);

        response.sendRedirect("/api/v1/skill");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("id");
        if (id == null) {
            List<Skill> skills = skillService.getAll();
            if (skills == null) {
                response.sendError(404);
            }
            else {
                for (Skill skill : skills) {
                    writer.println(gson.toJson(skill));
                }
            }
        }
        else {
            Long skillId = Long.parseLong(id);
            Skill skill = skillService.getById(skillId);
            writer.println(gson.toJson(skill));
        }

        writer.flush();
        writer.close();
    }
}
