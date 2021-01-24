let btnOpen = document.getElementById("btn_open")

btnOpen.onclick = function () {
    let more = document.getElementById("search__div");
    console.log(more.style.display)
    if (more.style.maxHeight === "0px" || more.style.maxHeight === "") {
        more.style.maxHeight = "200px"
    } else {
        more.style.maxHeight = "0px"
    }
};