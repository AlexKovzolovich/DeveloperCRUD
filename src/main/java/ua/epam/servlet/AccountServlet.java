package ua.epam.servlet;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import ua.epam.exceptions.PersistException;
import ua.epam.mapper.AccountMapper;
import ua.epam.mapper.AccountStatusMapper;
import ua.epam.model.Account;
import ua.epam.repository.jdbc.AccountRepositoryJdbcImpl;
import ua.epam.repository.jdbc.AccountStatusRepositoryJdbcImpl;
import ua.epam.service.serviceImpl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Log4j

public class AccountServlet extends HttpServlet {
    private AccountServiceImpl accountService;
    private final Gson gson = new Gson();

    {
        try {
            accountService = new AccountServiceImpl(new AccountRepositoryJdbcImpl(
                    new AccountMapper(new AccountStatusRepositoryJdbcImpl(
                            new AccountStatusMapper()))));
        } catch (PersistException e) {
            log.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = gson.fromJson(request.getReader(), Account.class);
        accountService.save(account);

        response.sendRedirect("/api/v1/account");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = gson.fromJson(request.getReader(), Account.class);
        accountService.delete(account);

        response.sendRedirect("/api/v1/account");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = gson.fromJson(request.getReader(), Account.class);
        accountService.update(account);

        response.sendRedirect("/api/v1/account");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        if (id == null) {
            List<Account> accounts = accountService.getAll();
            if (accounts == null) {
                response.sendError(404);
            } else {
                for (Account account : accounts) {
                    writer.println(gson.toJson(account));
                }
            }
        }
        else {
            Long accountId = Long.parseLong(id);
            Account account = accountService.getById(accountId);
            writer.println(gson.toJson(account));
        }

        writer.flush();
        writer.close();
    }
}
