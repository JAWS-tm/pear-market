package com.pearmarket.app.servlets;

public class ErrorManager extends Exception{
    public enum ErrorTypes {
        INVALID_PARAMETER,
        NULL_OBJECT,
        UNKNOWN_ERROR,
        ERROR_404
    }

    private ErrorTypes type = ErrorTypes.INVALID_PARAMETER;
    private String title = "";
    private String description = "";
    public ErrorManager(ErrorTypes type) {
        this.type = type;

        switch (type) {
            case INVALID_PARAMETER:
                title = "Le lien saisi n'existe pas";
                description = "Les paramètres de l'URL sont erronés.";
                break;

            case NULL_OBJECT:
                title = "L'élement recherché est introuvable";
                description = "Aucun élément ne correspond à votre recherche, il a soit été supprimé ou n'exite pas.";
                break;

            case ERROR_404:
                title = "La page que vous cherchez n'existe pas";
                description = "Aucune page correspondante n'a été trouvé, réessayez votre requête ou vérifier l'URL.";
                break;

            default:
            case UNKNOWN_ERROR:
                title = "Une erreur inconnue s'est produite";
                description = "Veuillez réessayer dans quelques minutes.";
                break;
        }
        description += "\nSi le problème persiste veuillez contacter un administrateur!";
    }



    /** Getters & Setters **/

    public ErrorTypes getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
