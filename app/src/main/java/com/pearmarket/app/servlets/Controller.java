package com.pearmarket.app.servlets;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.elements.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Controller {
    private String jspLink;
    private String title;
    private String[] styleFiles;
    private Boolean whiteNavBar = false;

    private Boolean isRendered = false;
    private String redirectLink;

    protected final HttpServletRequest request;
    protected final HttpServletResponse response;
    protected final DAOFactory daoFactory;

    public Controller(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        this.daoFactory = DAOFactory.getInstance();
    }

    public abstract void process() throws ServletException, IOException, ErrorManager;

    protected void render() throws IOException, ServletException {
        if (isRendered)
            return;

        if (redirectLink != null) {
            response.sendRedirect(redirectLink);
        } else if (!jspLink.isEmpty()) {
            request.setCharacterEncoding("UTF-8");

            if (title != null)
                request.setAttribute("title", title + " - Pear Market");
            else
                request.setAttribute("title", "Pear Market");

            request.setAttribute("styleFiles", styleFiles);
            request.setAttribute("whiteNavBar", whiteNavBar);

            request.setAttribute("pageContent", jspLink);
            request.getRequestDispatcher("/jsp/layout.jsp").forward(request, response);
        }
        isRendered = true;
    }



    protected void redirect(String link) throws ServletException, IOException {
        redirectLink = request.getContextPath() + link;
        render();
    }

    protected void setRequestedLink(String link) {
        request.getSession().setAttribute("requestedLink", link);
    }

    protected String getRequestedLink() {
        return (String) request.getSession().getAttribute("requestedLink");
    }

    protected void setLoggedUser(User user) throws ServletException, IOException {
        request.getSession().setAttribute("loggedUser", user);

        if (this.getRequestedLink() != null) {
            this.redirect(this.getRequestedLink());
            this.setRequestedLink(null);
        }
    }

    protected User getLoggedUser() {
        return (User) request.getSession().getAttribute("loggedUser");
    }


    protected int parseInt(String str) throws ErrorManager {
        int id = -1;
        try {
            id = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new ErrorManager(ErrorManager.ErrorTypes.INVALID_PARAMETER);
        }

        return id;
    }

    /** Getters & Setters **/

    public String getJspLink() {
        return jspLink;
    }

    public void setJspLink(String jspLink) {
        this.jspLink = jspLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getStyleFiles() {
        return styleFiles;
    }

    public void setStyleFiles(String[] styleFiles) {
        this.styleFiles = styleFiles;
    }

    public Boolean getWhiteNavBar() {
        return whiteNavBar;
    }

    public void setWhiteNavBar(Boolean whiteNavBar) {
        this.whiteNavBar = whiteNavBar;
    }
}
