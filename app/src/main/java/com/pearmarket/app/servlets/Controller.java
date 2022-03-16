package com.pearmarket.app.servlets;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.UserDAO;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.utils.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe abstraite qui permet le polymorphisme utilisé dans la servlet.
 */
public abstract class Controller {
    private String jspLink;
    private String title;
    private String[] styleFiles;
    private boolean whiteNavBar = false;
    private boolean hasLayout = true;

    private boolean isRendered = false;
    private String redirectLink;

    protected final HttpServletRequest request;
    protected final HttpServletResponse response;
    protected final DAOFactory daoFactory;
    private final UserDAO userDAO;

    public Controller(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        this.daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDAO(DAOFactory.DBType.MariaDB);
    }

    /***
     * Effectue tous les calculs de logiques reliés à la page
     * @throws ServletException
     * @throws IOException
     * @throws ErrorManager Si un problème survient (permet l'affichage d'une page d'erreur)
     */
    public abstract void process() throws ServletException, IOException, ErrorManager;

    /**
     * Affiche la page et transmet les données essentielles à la jsp
     * Ou execute une redirection si fournie (traitée ici pour eviter le double forward)
     * @throws IOException
     * @throws ServletException
     */
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
            request.setAttribute("hasLayout", hasLayout);
            request.getRequestDispatcher("/jsp/layout.jsp").forward(request, response);
        }
        isRendered = true;
    }

    /**
     * Redirige l'utilisateur vers un url donné
     * @param link url de redirection
     * @throws ServletException
     * @throws IOException
     */
    protected void redirect(String link) throws ServletException, IOException {
        redirectLink = request.getContextPath() + link;
        render();
    }

    /**
     * Permet de définir une redirection d'après connexion (ex: cart => connexion => checkout)
     * @param link lien sur lequel l'utilisateur sera redirigé lorsqu'il se sera connecté
     */
    protected void setRequestedLink(String link) {
        request.getSession().setAttribute("requestedLink", link);
    }

    /**
     * @return lien d'après connexion
     */
    protected String getRequestedLink() {
        return (String) request.getSession().getAttribute("requestedLink");
    }

    /**
     * Défini/Modifie l'utilisateur connecté et le redirige sur la page "d'après connexion" s'il en a "demandé une"
     * @param user donnée de l'utilisateur
     * @throws ServletException
     * @throws IOException
     */
    protected void setLoggedUser(User user) throws ServletException, IOException {
        request.getSession().setAttribute("loggedUser", user);

        if (this.getRequestedLink() != null) {
            this.redirect(this.getRequestedLink());
            this.setRequestedLink(null);
        }
    }

    /**
     * Récupère l'utilisateur connecté si il y en a un
     * @return l'utilisateur connecté ou null s'il y en a pas
     * @throws ServletException
     * @throws IOException
     */
    protected User getLoggedUser() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("loggedUser");
        if (user != null) { // refresh data
            User userRefresh = userDAO.getUser(user.getEmail());
            if (userRefresh != null) {
                user = userRefresh;
                setLoggedUser(userRefresh);
            }
        }

        return user;
    }


    /**
     * Converti une String en int et génère une page d'erreur si la conversion est impossible
     * Permet de "sécuriser" les paramètres url avant leur utilisation
     * @param str "l'entier" a convertir
     * @return l'entier converti
     * @throws ErrorManager
     */
    protected int parseIntParam(String str) throws ErrorManager {
        int id = -1;
        try {
            id = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new ErrorManager(ErrorManager.ErrorTypes.INVALID_PARAMETER);
        }

        return id;
    }

    /** Getters & Setters **/

    /**
     * @return la page jsp va être affichée
     */
    public String getJspLink() {
        return jspLink;
    }

    /**
     * Défini la jsp va être chargée et affichée
     */
    public void setJspLink(String jspLink) {
        this.jspLink = jspLink;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Défini un nom à la page (par défaut : Pear Market)
     * si un title est défini l'affichage sera le suivant : [title] - Pear Market
     * @param title nom de page
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getStyleFiles() {
        return styleFiles;
    }

    /**
     * Défini quels fichiers css seront chargés dans la jsp
     * @param styleFiles la liste des noms de fichiers css
     */
    public void setStyleFiles(String[] styleFiles) {
        this.styleFiles = styleFiles;
    }

    public boolean getWhiteNavBar() {
        return whiteNavBar;
    }

    /**
     * Défini la nav bar comme blanche ou transparente
     * @param whiteNavBar true/false
     */
    public void setWhiteNavBar(boolean whiteNavBar) {
        this.whiteNavBar = whiteNavBar;
    }

    /**
     * Défini si le layout (header et footer) doit être affiché
     * @param hasLayout
     */
    public void setHasLayout(boolean hasLayout) {
        this.hasLayout = hasLayout;
    }
}
