/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.color.finder.webapp.controller;

import java.awt.Color;
import java.util.Collection;
import javax.validation.Valid;
import org.opens.color.finder.webapp.model.ColorModel;
import org.opens.color.finder.webapp.validator.ColorModelValidator;
import org.opens.colorfinder.factory.ColorFinderFactory;
import org.opens.colorfinder.result.ColorResult;
import org.opens.utils.colorconvertor.ColorConverter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Scub-Foundation
 */
@Controller
public class IndexController {

    /**
     * Nom du model
     */
    private String commandName;
    /**
     * Vue à afficher lorsque le formulaire est correctement rempli
     */
    private String successView;
    /**
     * Vue contenant le formulaire
     */
    private String formView;
    /**
     * Vue contenant le formulaire
     */
    @Autowired
    private ColorFinderFactory colorFinderFactory;

    /**
     * Initialisation du validateur
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ColorModelValidator());
    }

    /**
     * Initialisation de la page de formulaire
     *
     * @param model modele de la page
     * @return le nom de la page à afficher
     */
    @RequestMapping(value = "form.mvc")
    public String initAccueil(final Model model) {
        ColorModel colorModel = new ColorModel();

        model.addAttribute(commandName, colorModel);
        return formView;
    }

    /**
     * Réception du résultat du formulaire
     *
     * @param model modele de la page
     * @param messagePersonneModel Modele retourné par le formulaire
     * @param result Résultat du validateur
     * @return le nom de la page à afficher
     */
    @RequestMapping(value = "form.mvc", method = RequestMethod.POST)
    public String getInfoAccueil(final Model model, @Valid ColorModel colorModel, BindingResult result) {
        // S'il y a des erreurs, on reste sur le formulaire
        if (result.hasErrors()) {
            return formView;
        } // Sinon, on passe à la page d'affichage des informations
        else {

            Color foregroundColor = ColorConverter.hex2Rgb(colorModel.getForeground());
            Color backgroundColor = ColorConverter.hex2Rgb(colorModel.getBackground());
            boolean isBackgroundTested = colorModel.getIsBackgroundTested();
            Float ratio = Float.valueOf(colorModel.getRatio());

            Collection<ColorResult> colorResults =
                    colorFinderFactory.getColorFinder().findColors(foregroundColor, backgroundColor, isBackgroundTested, ratio);

//            model.addAttribute("colorModel", colorModel);
            model.addAttribute("colorResult", colorResults);
            return formView;
        }

    }

    /**
     * Setter sur le nom du modèle
     */
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Setter du nom de la successView
     */
    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    /**
     * Setter du nom de la formView
     */
    public void setFormView(String formView) {
        this.formView = formView;
    }
}