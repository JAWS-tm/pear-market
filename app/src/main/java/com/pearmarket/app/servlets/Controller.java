package com.pearmarket.app.servlets;

import com.pearmarket.app.beans.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    protected void redirect(String link) throws ServletException, ErrorManager, IOException {
        redirectLink = request.getContextPath() + link;
    }

    public abstract void process() throws ServletException, IOException, ErrorManager;

    protected void render() throws IOException, ServletException {
        if (redirectLink != null) {
            response.sendRedirect(redirectLink);
        }
        else if (!jspLink.isEmpty() && !isRendered)
        {
            request.setCharacterEncoding("UTF-8");

            if (title != null)
                request.setAttribute("title", title + " - Pear Market");
            else
                request.setAttribute("title", "Pear Market");

            request.setAttribute("styleFiles", styleFiles);
            request.setAttribute("whiteNavBar", whiteNavBar);

            request.setAttribute("pageContent", jspLink);
            request.getRequestDispatcher("/jsp/layout.jsp").forward(request, response);

            isRendered = true;
        }
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
