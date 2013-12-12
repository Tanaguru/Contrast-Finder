package org.opens.color.finder.webservice;

import org.apache.log4j.Logger;
import org.opens.colorfinder.ColorFinder;
import org.opens.colorfinder.factory.ColorFinderFactory;
import org.opens.utils.colorconvertor.ColorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContrastFinderController {

    protected static Logger logger = Logger.getLogger("controller");
    @Autowired
    private ColorFinderFactory colorFinderFactory;

    @RequestMapping("/getColors")
    public @ResponseBody
    JsonColorResult getColor(
            @RequestParam(value = "foreground", required = true) String fgColor,
            @RequestParam(value = "background", required = true) String bgColor,
            @RequestParam(value = "component", required = false, defaultValue = "foreground") String component,
            @RequestParam(value = "ratio", required = false, defaultValue = "4.5") String ratio) {

        boolean isForegroundValid = fgColor.matches("[0-9A-Fa-f]+");
        if (!isForegroundValid) {
            fgColor = "000000";
        }
        boolean isBackgroundValid = bgColor.matches("[0-9A-Fa-f]+");
        if (!isBackgroundValid) {
            bgColor = "000000";
        }
        boolean isBackgroundTested;
        if (component.equals("background")) {
            isBackgroundTested = true;
        } else {
            isBackgroundTested = false;
        }
        if (!(ratio.equals("4.5") || ratio.equals("3") || ratio.equals("7"))) {
            ratio = "4.5";
        }

        MappingJackson2HttpMessageConverter http = new MappingJackson2HttpMessageConverter();
        ColorFinder colorFinder = colorFinderFactory.getColorFinder("RGB");
        colorFinder.findColors(ColorConverter.hex2Rgb("#" + fgColor), ColorConverter.hex2Rgb("#" + bgColor), isBackgroundTested, Float.valueOf(ratio));
        return new JsonColorResult(colorFinder.getColorResult());
    }
}