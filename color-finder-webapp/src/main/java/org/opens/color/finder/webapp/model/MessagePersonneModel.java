/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.color.finder.webapp.model;

import java.io.Serializable;

/**
 * Command Class qui contient les informations utilisées dans le formulaire.
 *
 * @author Scub-Foundation
 */
public class MessagePersonneModel implements Serializable {

    /**
     * Identifiant unique généré.
     */
    private static final long serialVersionUID = -9110504410854001626L;
    /**
     * Prénom de la personne.
     */
    private String prenom;
    /**
     * Nom de la personne.
     */
    private String nom;
    /**
     * Message adressé à la personne.
     */
    private String message;

    /**
     * Accesseur en lecture
     *
     * @return le prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Accesseur en écriture
     *
     * @param prenom le nouveau prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Accesseur en lecture
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur en écriture
     *
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Accesseur en lecture
     *
     * @return le message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Accesseur en écriture
     *
     * @param message le nouveau message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
