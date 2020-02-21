package ua.epam.servlet;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.epam.model.Account;
import ua.epam.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AccountServlet", urlPatterns = "/api/v1/account")
@Controller
public class AccountServlet extends HttpServlet {
    private AccountService accountService;
    private Gson gson;

    @Autowired
    public AccountServlet(AccountService accountService) {
        this.accountService = accountService;
        this.gson = new Gson();
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
