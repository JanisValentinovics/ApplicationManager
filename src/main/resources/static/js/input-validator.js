const TEMPERATURE_REGEX = new RegExp("^(([3][4-9]($|((\.|\,)[0-9])))|([4][0-1]($|((\.|\,)[0-9])))|42)$");
const ID_REGEX = new RegExp("^([0-9]{6})-([0-9]{5})$");
const NUMBER_REGEX = new RegExp("^(2|6)[0-9]{7}$");

const temperature = document.getElementById("temperature");
const id = document.getElementById("personalCode");
const number = document.getElementById("phoneNumber");
const update = document.getElementById("update-patient");

const temperatureError = document.getElementById("temperature-error-msg");
const idError = document.getElementById("id-error-msg");
const numberError = document.getElementById("number-error-msg");

function validateTemperature(t) {
    let r = !TEMPERATURE_REGEX.test(t);
    if (r) {
        temperatureError.textContent = "* Temperature is incorrect, must be value from 34 to 42 and have none or only one fractional number.\n";
    } else {
        temperatureError.textContent = "";
    }
    return r;
}

function validateID(v) {
    let r = !ID_REGEX.test(v);
    if (r) {
        idError.textContent = "* Personal ID has to be 6 digits divided with '-' and than 5 more digits, XXXXXX-XXXXX."
    } else {
        idError.textContent = "";
    }
    return r;
}

function validateNumber(v) {
    let r = !NUMBER_REGEX.test(v);
    if (r) {
        numberError.textContent = "* Phone number must be 8 digits long and must start with 2 or 6."
    } else {
        numberError.textContent = "";
    }
    return r;
}

function parseTemperature(t) {
    t.toString().replace(",", ".");
    return t;
}

update.addEventListener("click", (e) => {
    let error = false;
    error = validateTemperature(temperature.value) || error;
    temperature.value = parseTemperature(temperature.value);
    error = validateID(id.value) || error;
    error = validateNumber(number.value) || error;
    if (error) {
        e.preventDefault();
    }
});