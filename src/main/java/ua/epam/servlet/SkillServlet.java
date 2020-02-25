package ua.epam.servlet;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.SkillMapper;
import ua.epam.model.Skill;
import ua.epam.repository.jdbc.SkillRepositoryJdbcImpl;
import ua.epam.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SkillServlet", urlPatterns = "/api/v1/skill")
public class SkillServlet extends HttpServlet {
    private SkillService skillService;
    private Gson gson;

    public SkillServlet(SkillService skillService) {
        this.skillService = skillService;
        this.gson = new Gson();
    }

    @Autowired


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
