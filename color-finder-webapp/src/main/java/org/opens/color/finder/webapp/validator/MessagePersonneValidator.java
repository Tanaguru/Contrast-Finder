/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.color.finder.webapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.opens.color.finder.webapp.model.MessagePersonneModel;

/**
 * Validateur pour le contenu de notre formulaire.
 *
 * @author Scub-Foundation
 */
public class MessagePersonneValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        // Déclare les classes supportées par ce validateur
        return MessagePersonneModel.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Object target, Errors errors) {
        // On spécifie ici que le champ du formulaire "prenom" est requis
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", null, "Champ obligatoire : veuillez saisir un prénom");
        // On spécifie ici que le champ du formulaire "nom" est requis
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", null, "Champ obligatoire : veuillez saisir un nom");
    }
}