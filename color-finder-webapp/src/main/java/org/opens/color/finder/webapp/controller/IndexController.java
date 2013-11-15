/*
 * Contrast Finder
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */ 

package org.opens.color.finder.webapp.controller;

import java.awt.Color;
import javax.validation.Valid;
import org.opens.color.finder.webapp.model.ColorModel;
import org.opens.color.finder.webapp.validator.ColorModelValidator;
import org.opens.colorfinder.ColorFinder;
import org.opens.colorfinder.factory.ColorFinderFactory;
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
 *
 * @author alingua
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
    @InitBinder("colorModel")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ColorModelValidator());
    }

    /**
     * Initialisation de la page de formulaire
     *
     * @param model modele de la page
     * @return le nom de la page à afficher
     */
    @RequestMapping(value = "form.html")
    public String initAccueil(final Model model) {
        ColorModel colorModel = new ColorModel();

        model.addAttribute(commandName, colorModel);
        return formView;
    }

    /**
     *
     * @param model
     * @param colorModel
     * @param result
     * @return
     */
    @RequestMapping(value = "form.html", method = RequestMethod.POST)
    public String getInfoAccueil(final Model model, @Valid ColorModel colorModel, BindingResult result) {
        if (result.hasErrors()) {
            return formView;
        } else {

            Color foregroundColor = ColorConverter.hex2Rgb(colorModel.getForeground());
            Color backgroundColor = ColorConverter.hex2Rgb(colorModel.getBackground());
            boolean isBackgroundTested = colorModel.getIsBackgroundTested().equals("true");
            Float ratio = Float.valueOf(colorModel.getRatio());

            ColorFinder colorFinder = colorFinderFactory.getColorFinder();
            colorFinder.findColors(foregroundColor, backgroundColor, isBackgroundTested, ratio);
            
            model.addAttribute("colorResult", colorFinder.getColorResult());
            
            String rgbBackground = ColorConverter.hex2Rgb(backgroundColor);
            String rgbForeground = ColorConverter.hex2Rgb(foregroundColor);
            String hsvBackground = ColorConverter.RGB2hsl(backgroundColor);
            String hsvForeground = ColorConverter.RGB2hsl(foregroundColor);
            int resultNumber = colorFinder.getColorResult().getNumberOfSuggestedColors();
            model.addAttribute("backgroundColor", rgbBackground);
            model.addAttribute("foregroundColor", rgbForeground);
            model.addAttribute("backgroundHSLColor", hsvBackground);
            model.addAttribute("foregroundHSLColor", hsvForeground);
            model.addAttribute("resultNumber", resultNumber);

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