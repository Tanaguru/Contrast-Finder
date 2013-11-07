/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.colorfinder.cli;

import java.awt.Color;
import java.util.Collection;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.opens.colorfinder.ColorFinder;
import org.opens.colorfinder.factory.ColorFinderFactory;
import org.opens.colorfinder.result.ColorResult;
import org.opens.utils.colorconvertor.ColorConverter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author alingua
 */
 public class ColorFinderCliLauncher {

    private static final float DEFAUT_COEFF_LEVEL = 4.5f;
    private static final float MIN_RATIO = 1.0f;
    private static final float MAX_RATIO = 21.0f;
    private static final int BEGIN_COLOR = 2;

    /**
     *
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {

        if (args == null) {
            return;
        }
        Options options = createOptions();
        CommandLineParser clParser = new BasicParser();
        CommandLine commandLine = clParser.parse(options, args);

        if (!commandLine.hasOption('f')) {
            printUsage(options);
            return;
        }
        if (!commandLine.hasOption('b')) {
            printUsage(options);
            return;
        }

        String foregroundCli = commandLine.getOptionValue('f');
        Color foregroundColor = ColorConverter.hex2Rgb(foregroundCli);
        if (foregroundColor == null) {
            printUsage(options);
            return;
        }

        String backgroundCli = commandLine.getOptionValue('b');
        Color backgroundColor = ColorConverter.hex2Rgb(backgroundCli);
        if (backgroundColor == null) {
            printUsage(options);
            return;
        }

        boolean backgroundTested = false;
        if (commandLine.hasOption('c')) {
            String component = commandLine.getOptionValue('c');
            if (component.equalsIgnoreCase("foreground")) {
                backgroundTested = false;
            } else if (component.equalsIgnoreCase("background")) {
                backgroundTested = true;
            } else {
                printUsage(options);
                return;
            }
        }

        Float coefficientLevel = DEFAUT_COEFF_LEVEL;
        String rationOp = commandLine.getOptionValue('r');
        if (commandLine.hasOption('r')) {
            try {
                Float.parseFloat(rationOp);
            } catch (NumberFormatException nfe) {
                printUsage(options);
                return;
            }
            Float coeff = Float.valueOf(commandLine.getOptionValue('r'));
            if (coeff >= MIN_RATIO && coeff <= MAX_RATIO) {
                coefficientLevel = coeff;
            } else {
                printUsage(options);
                return;
            }
        }

        ListableBeanFactory bf;
        if (commandLine.hasOption('i')) {
            String implementation = commandLine.getOptionValue('i');
            if (implementation.equalsIgnoreCase("onebyone")) {
                bf = new XmlBeanFactory(new ClassPathResource("spring-context-rgb.xml"));
            } else if (implementation.equalsIgnoreCase("hsvfinder")) {
                bf = new XmlBeanFactory(new ClassPathResource("spring-context-hsv.xml"));
            } else {
                printUsage(options);
                return;
            }
        } else {
            bf = new XmlBeanFactory(new ClassPathResource("spring-context-hsv.xml"));
        }
        
        ColorFinderFactory cff = (ColorFinderFactory)bf.getBean("colorFinderFactory");
        
        findColor(foregroundColor,
                  backgroundColor,
                  backgroundTested,
                  coefficientLevel, 
                  cff);

    }

    /**
     * 
     * @param foregroundColor
     * @param backgroundColor
     * @param isBackgroundTested
     * @param coefficientLevel
     * @param colorFinderFactory 
     */
    private static void findColor (
            Color foregroundColor,
            Color backgroundColor,
            boolean isBackgroundTested,
            Float coefficientLevel, 
            ColorFinderFactory colorFinderFactory) {

        ColorFinder cff = colorFinderFactory.getColorFinder();
        Collection<ColorResult> colorResults = cff.findColors(
                                                    foregroundColor, 
                                                    backgroundColor, 
                                                    isBackgroundTested, 
                                                    coefficientLevel);
        
        String foundColor, oppositeColor;
        for (ColorResult colorResult : colorResults) {
            foundColor = "#" + Integer.toHexString(colorResult.getColor().getRGB()).substring(BEGIN_COLOR);
            oppositeColor = "#" + Integer.toHexString(colorResult.getComparisonColor().getRGB()).substring(BEGIN_COLOR);
            System.out.println("Found Color : "
                    + foundColor);
            System.out.println("Opposite Color : "
                    + oppositeColor);
            System.out.println("Contrast : "
                    + colorResult.getContrast());
        }
    }

    /**
     *
     * @return the command line options
     */
    private static Options createOptions() {
        // create the Options
        Options options = new Options();

        options.addOption("f", "foreground-color", true, "foreground color hexadecimal value : #000011 for example");
        options.addOption("b", "background-color", true, "background color haxadecimal value : #0012F0 for example");
        options.addOption("c", "component-modify", true, "component which can be modify (background or foreground)");
        options.addOption("r", "ratio", true, "coefficient value : 4.5 for 4.5:1 ratio it should be between 1 to 21");
        options.addOption("i", "implementation", true, "implementation type (onebyone or hsvfinder)");

        return options;
    }

    /**
     * 
     * @param options 
     */
    private static void printUsage(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("./colorFinder [OPTIONS]... -f \"foregroundColor\" -b \"backgroundColor\"", options);
    }
}