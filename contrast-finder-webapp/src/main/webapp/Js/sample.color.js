var $foregroundRed = document.querySelector('#foreground-red'),
    $foregroundGreen = document.querySelector('#foreground-green'),
    $foregroundBlue = document.querySelector('#foreground-blue'),

    $foregroundInput = document.querySelector('#foreground-input'),
    $foregroundSample = document.querySelector('#foreground-sample'),
    $foregroundSampleInvalid = document.querySelector('#foreground-sample-invalid'),

    $backgroundRed = document.querySelector('#background-red'),
    $backgroundGreen = document.querySelector('#background-green'),
    $backgroundBlue = document.querySelector('#background-blue'),

    $backgroundInput = document.querySelector('#background-input'),
    $backgroundSample = document.querySelector('#background-sample'),
    $backgroundSampleInvalid = document.querySelector('#background-sample-invalid'),


    foregroundRGBInputs = [$foregroundRed, $foregroundGreen, $foregroundBlue], 
    backgroundRGBInputs = [$backgroundRed, $backgroundGreen, $backgroundBlue];

$(document).ready(function() {
    changeBackgroundSample();
    changeForegroundSample();

    $foregroundInput.onchange = function() {
        changeForegroundSample();
    };

    $backgroundInput.onchange = function() {
        changeBackgroundSample();
    };

    for (var i = 0; i < foregroundRGBInputs.length; i++) {
        foregroundRGBInputs[i].onchange = function() {
            updateForegroundHexColor();
        }
    }

    for (var i = 0; i < backgroundRGBInputs.length; i++) {
        backgroundRGBInputs[i].onchange = function() {
            updateBackgroundHexColor();
        }
    }
});

function changeForegroundSample() {
    var color = $foregroundInput.value;
    color = isValidateColor(color.toString());
    if (color !== "false") {
        $foregroundSample.style.backgroundColor = color;
        $foregroundSample.classList.add('color-sample');
        $foregroundSample.classList.add('sample-bordered');

        $foregroundSampleInvalid.style.display = "none";

        $foregroundInput.classList.remove('error');

        for (var i = 0; i < foregroundRGBInputs.length; i++) {
            foregroundRGBInputs[i].classList.remove('error');
        }

        changeForegroundRGBValues();
    } else {
        $foregroundSample.style.backgroundColor = "rgba(0,0,0,0)";
        $foregroundSample.classList.remove('color-sample');
        $foregroundSample.classList.remove('sample-bordered');

        $foregroundSampleInvalid.style.display = "inherit";

        $foregroundInput.classList.add('error');

        $foregroundRed.value = "";
        $foregroundGreen.value = "";
        $foregroundBlue.value = "";
    }
}

function changeBackgroundSample() {
    var color = $backgroundInput.value;
    var sample = document.getElementById("background-sample");
    color = isValidateColor(color.toString());
    if (color !== "false") {
        $backgroundSample.style.backgroundColor = color;
        $backgroundSample.classList.add('color-sample');
        $backgroundSample.classList.add('sample-bordered');

        $backgroundSampleInvalid.style.display = "none";

        $backgroundInput.classList.remove('error');

        for (var i = 0; i < backgroundRGBInputs.length; i++) {
            backgroundRGBInputs[i].classList.remove('error');
        }

        changeBackgroundRGBValues();
    } else {
        $backgroundSample.style.backgroundColor = "rgba(0,0,0,0)";
        $backgroundSample.classList.remove('color-sample');
        $backgroundSample.classList.remove('sample-bordered');

        $backgroundSampleInvalid.style.display = "inherit";

        $backgroundInput.classList.add('error');

        $backgroundRed.value = "";
        $backgroundGreen.value = "";
        $backgroundBlue.value = "";
    }
}

function isValidateColor(str) {
    if (str.match(/^#[a-fA-F0-9]{6}$/i) !== null
            || str.match(/^#[a-fA-F0-9]{3}$/i) !== null)
    {
        return str;
    }
    else if (str.match(/^#?([a-fA-F0-9]{6})$/) !== null
            || str.match(/^#?([a-fA-F0-9]{3})$/) !== null) {
        str = setValidColor(str);
        return str;
    }
    else {
        return "false"
    }
}

function setValidColor(str) {
    var result = str.splice(0, 0, "#");
    return result;
}

String.prototype.splice = function(idx, rem, s) {
    return (this.slice(0, idx) + s + this.slice(idx + Math.abs(rem)));
};

function changeForegroundRGBValues() {
    var rgbText = $foregroundSample.style.backgroundColor.split('rgb(')[1].split(')')[0];
    $foregroundRed.value = parseInt(rgbText.split(',')[0]);
    $foregroundGreen.value = parseInt(rgbText.split(',')[1]);
    $foregroundBlue.value = parseInt(rgbText.split(',')[2]);
}

function changeBackgroundRGBValues() {
    var rgbBackground = $backgroundSample.style.backgroundColor.split('rgb(')[1].split(')')[0];
    $backgroundRed.value = parseInt(rgbBackground.split(',')[0]);
    $backgroundGreen.value = parseInt(rgbBackground.split(',')[1]);
    $backgroundBlue.value = parseInt(rgbBackground.split(',')[2]);
}

/**
 * Convertit un nombre en hexadécimale
 */
function componentToHex(value) {
    var hexValue = value.toString(16);
    return hexValue.length == 1 ? "0" + hexValue : hexValue;
}

/**
 * Mise à jour des valeurs hex de la couleur du texte
 */ 
function updateForegroundHexColor() {
    if ( validTextRGBValues() ) {
        var red = componentToHex(parseInt($foregroundRed.value)), 
            green = componentToHex(parseInt($foregroundGreen.value)), 
            blue = componentToHex(parseInt($foregroundBlue.value)),
            hexColor =  "#" + red + green + blue;

        $foregroundInput.value = hexColor;
        $foregroundSample.style.backgroundColor = hexColor;
        $foregroundInput.classList.remove('error');
        $foregroundSample.classList.add('color-sample');
        $foregroundSample.classList.add('sample-bordered');
        $foregroundSampleInvalid.style.display = "none";
    } else {
        $foregroundInput.classList.add('error');
        $foregroundSample.style.backgroundColor = "rgba(0,0,0,0)";
        $foregroundSample.classList.remove('color-sample');
        $foregroundSample.classList.remove('sample-bordered');
        $foregroundSampleInvalid.style.display = "inherit";
    }
}

/**
 * Mise à jour des valeurs hex de la couleur du fond
 */ 
function updateBackgroundHexColor() {
    if ( validBackgroundRGBValues() ) {
        var red = componentToHex(parseInt($backgroundRed.value)), 
            green = componentToHex(parseInt($backgroundGreen.value)), 
            blue = componentToHex(parseInt($backgroundBlue.value)),
            hexColor =  "#" + red + green + blue;

        $backgroundInput.value = hexColor;
        $backgroundSample.style.backgroundColor = hexColor;
        $backgroundInput.classList.remove('error');
        $backgroundSample.classList.add('color-sample');
        $backgroundSample.classList.add('sample-bordered');
        $backgroundSampleInvalid.style.display = "none";
    } else {
        $backgroundInput.classList.add('error');
        $backgroundSample.style.backgroundColor = "rgba(0,0,0,0)";
        $backgroundSample.classList.remove('color-sample');
        $backgroundSample.classList.remove('sample-bordered');
        $backgroundSampleInvalid.style.display = "inherit";
    }
}

/**
 * Vérifie que les valeurs RVB du texte soient correctes
 */ 
function validTextRGBValues() {
    if ( $foregroundRed.value !== '' ) {
        if ( isNaN($foregroundRed.value) || $foregroundRed.value < 0 || $foregroundRed.value > 255 ) {
            console.log('test');
            $foregroundRed.classList.add('error');
            $foregroundRed.setAttribute('aria-invalid', true);
            return false;
        }
    }

    if ( $foregroundGreen.value !== '' ) {
        if ( isNaN($foregroundGreen.value) || $foregroundGreen.value < 0 || $foregroundGreen.value > 255 ) {
            $foregroundGreen.classList.add('error');
            $foregroundGreen.setAttribute('aria-invalid', true);
            return false;
        }
    }

    if ( $foregroundBlue.value !== '' ) {
        if ( isNaN($foregroundBlue.value) || $foregroundBlue.value < 0 || $foregroundBlue.value > 255 ) {
            $foregroundBlue.classList.add('error');
            $foregroundBlue.setAttribute('aria-invalid', true);
            return false;
        }
    }

    for (var i = 0; i < foregroundRGBInputs.length; i++) {
        foregroundRGBInputs[i].classList.remove('error');
        foregroundRGBInputs[i].setAttribute('aria-invalid', false);
    }
    return true;
} 

/**
 * Vérifie que les valeurs RVB du fond soient correctes
 */ 
function validBackgroundRGBValues() {
    if ( $backgroundRed.value !== '' ) {
        if ( isNaN($backgroundRed.value) || $backgroundRed.value < 0 || $backgroundRed.value > 255 ) {
            $backgroundRed.classList.add('error');
            $backgroundRed.setAttribute('aria-invalid', true);
            return false;
        }
    }

    if ( $backgroundGreen.value !== '' ) {
        if ( isNaN($backgroundGreen.value) || $backgroundGreen.value < 0 || $backgroundGreen.value > 255 ) {
            $backgroundGreen.classList.add('error');
            $backgroundGreen.setAttribute('aria-invalid', true);
            return false;
        }
    }

    if ( $backgroundBlue.value !== '' ) {
        if ( isNaN($backgroundBlue.value) || $backgroundBlue.value < 0 || $backgroundBlue.value > 255 ) {
            $backgroundBlue.classList.add('error');
            $backgroundBlue.setAttribute('aria-invalid', true);
            return false;
        }
    }


    for (var i = 0; i < backgroundRGBInputs.length; i++) {
        backgroundRGBInputs[i].classList.remove('error');
        backgroundRGBInputs[i].setAttribute('aria-invalid', false);
    }
    return true;
} 