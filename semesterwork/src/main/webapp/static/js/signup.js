const inputs = document.querySelectorAll(".input");

function add_class() {
    let parent = this.parentNode.parentNode;
    parent.classList.add("focus");
}

function remove_class(){
    let parent = this.parentNode.parentNode;
    if (this.value === "") {
        parent.classList.remove("focus");
    }
}

inputs.forEach(input => {
    input.addEventListener("focus", add_class);
    input.addEventListener("blur", remove_class);
});

function printError(text) {
    let error = document.getElementById("error_text");
    error.innerHTML = text;
}

function allFieldsEntered() {
    let flag = true;
    inputs.forEach(input => {
        if (input.value === "") {
            input.parentNode.parentNode.setAttribute("style", "border-color: red;")
            flag = false;
        }
    })
    return flag;
}

function validate() {
    let name_reg = /^[a-zA-Zа-яА-Я'][a-zA-Zа-яА-Я-' ]+[a-zA-Zа-яА-Я']?$/u;
    let first_name = document.getElementById("first_name").value;
    let second_name = document.getElementById("second_name").value;
    console.log(first_name)
    console.log(second_name)
    if (!name_reg.test(first_name) || !name_reg.test(second_name)) {
        printError("First name or last name is not correct");
        return false;
    }

    let login_reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let val = document.getElementById("login").value;
    if (!login_reg.test(val)) {
        printError("Login is not correct");
        return false;
    }

    let password1 = document.getElementById("password1");
    let password2 = document.getElementById("password2");
    if (password1.value !== password2.value) {
        printError("Passwords do not match")
        return false;
    }
    return true;
}

document.getElementById("form").onsubmit = function() {
    if (!allFieldsEntered()) {
        printError("Fill in all the fields");
        return false;
    }
    let valid = validate();
    return valid;
}