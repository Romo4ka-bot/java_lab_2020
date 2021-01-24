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
    let login_reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let val = document.getElementById("login").value;
    if (!login_reg.test(val)) {
        printError("Login is not correct");
        return false;

    }
    return true;
}

document.getElementById("form").onsubmit = function() {
    if (!allFieldsEntered()) {
        printError("Fill in all the fields");
        return false;
    }

    return validate();
}