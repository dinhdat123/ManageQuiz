/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const ops = document.querySelectorAll(".option-answer");
const makeForm = document.getElementById("make-form");
const question = document.getElementById("question");
const mess = document.getElementById("mess");

function check(ops) {
    let checked = 0;
    for (let i = 0; i < ops.length; i++) {
        //loop each option input to check at least 1 option has checked
        if (ops[i].checked === true)
            //1 option has checked return true
            checked++;
    }
    return checked >= 1 && checked <= 3;
}

makeForm.addEventListener("submit", (e) => {
    if (!check(ops)) {
        //in all option is not check at least 1 then prevent to submit form
        e.preventDefault();
        mess.style.color = "red";
        mess.innerHTML = "You have to check at least 1 option and maximum of 3 options";
    }
    
})
