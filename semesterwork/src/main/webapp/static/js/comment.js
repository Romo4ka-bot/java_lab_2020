let comment_max_len = 255;
let inp = document.getElementById("comment_inp");
let out = document.createElement('div');
inp.after(out);

let func = function () {
    let count = this.value.length;
    if (count >= comment_max_len) {
        this.value = this.value.substring(0, comment_max_len);
    }
    out.innerHTML = this.value.length + "/" + comment_max_len;
};

inp.onkeydown = func;
inp.onkeyup = func;
