$(document).ready(function() {
    changeBackgroundSample();
    changeForegroundSample();
    document.getElementById("foreground-input").onchange = function() {
        changeForegroundSample();
        console.log("change foreground Appel");
    };
    document.getElementById("background-input").onchange = function() {
        changeBackgroundSample();
    };
});

function changeForegroundSample() {
    var input = document.getElementById("foreground-input");
    var color = input.value;
    var sample = document.getElementById("foreground-sample");
    color = isValidateColor(color.toString());
    if (color !== "false") {
        sample.style.backgroundColor = color;
        sample.classList.add('color-sample');
        sample.classList.add('sample-bordered');
        document.getElementById("foreground-sample-invalid").style.display = "none";
        input.classList.remove('error');
    } else {
        sample.style.backgroundColor = "rgba(0,0,0,0)";
        sample.classList.remove('color-sample');
        sample.classList.remove('sample-bordered');
        document.getElementById("foreground-sample-invalid").style.display = "inherit";
        input.classList.add('error');
    }
}

function changeBackgroundSample() {
    var input = document.getElementById("background-input");
    var color = input.value;
    var sample = document.getElementById("background-sample");
    color = isValidateColor(color.toString());
    if (color !== "false") {
        sample.style.backgroundColor = color;
        sample.classList.add('color-sample');
        sample.classList.add('sample-bordered');
        document.getElementById("background-sample-invalid").style.display = "none";
        input.classList.remove('error');
    } else {
        sample.style.backgroundColor = "rgba(0,0,0,0)";
        sample.classList.remove('color-sample');
        sample.classList.remove('sample-bordered');
        document.getElementById("background-sample-invalid").style.display = "inherit";
        input.classList.add('error');
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
    var result = str.splice(0, 0, "#")
    return result;
}

String.prototype.splice = function(idx, rem, s) {
    return (this.slice(0, idx) + s + this.slice(idx + Math.abs(rem)));
};