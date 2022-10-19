/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const questions = document.querySelectorAll(".ques");
const resultForm = document.getElementById("result-form");
const result = document.getElementById("result");
const timeDisplay = document.getElementById("time");
const nextBtn = document.getElementById("nextBtn");

let currentIndex = 1;
//set default currentIndex is 1

function startTimer(duration, timeDisplay) { 

    let minutes, seconds;

    setInterval(function () { 
        //this function will excecute after 1000 millliseconds

        minutes = parseInt(duration / 60, 10); 
        //minutes equal timer divide to 60
        seconds = parseInt(duration % 60, 10); 
        //seconds equal timer modulo to 60

        minutes = minutes < 10 ? "0" + minutes : minutes; 
        //append to 0 before minutes if it less than 10
        seconds = seconds < 10 ? "0" + seconds : seconds;
        //append to 0 before seconds if it less than 10

        timeDisplay.textContent = minutes + ":" + seconds;
        //display time

        if (duration === 0) {
            //duration equal 0 then submit result then return
            submitResult();
            return;
        }

        duration--;
        //duration decrease to 1 second

    }, 1000); 
}

window.onload = function () {

    questions[0].style.display = "block";
    //display block the first question when web loaded all the resource  
    let timelabel = timeDisplay.textContent;
    let duration = parseInt(timelabel.substr(0, 2), 10) * 60 + parseInt(timelabel.substr(3, 5), 10);
    startTimer(duration - 1, timeDisplay);
    //duration is decreased to 1 cause the timedisplay has already displayed the first second
};

function getResult() {
    let rs = "";
    let inputAnswers = document.querySelectorAll('.op');
    //get all input checkbox of answers
    for (let i = 0; i < inputAnswers.length; i++) {
        if (inputAnswers[i].checked) {
            //if input checkbox of answers was checked then append id of
            //that answer to rs string
            rs += inputAnswers[i].value + " ";
        }
    }
    return rs;
}

function submitResult() {
    result.value = getResult();
    //assign value of result of all checkbox input to result input of
    //hidden form the submit to server

    resultForm.submit();

}

nextBtn.onclick = function () {
    if (currentIndex === questions.length) {
        //currentIndex is equal to position of last question then submit the hidden form
        submitResult();
    } else {
        //currentIndex is not equal to position of last question 
        //then set display style of next and previous question div
        if (currentIndex === questions.length - 1) {
            //currentIndex is equal to position of before last question 
            //then change text of nextBtn to finish
            nextBtn.textContent = "Finish";
        }

        questions[currentIndex - 1].style.display = "none";
        //set style of the previous question div to none

        questions[currentIndex].style.display = "block";
        //set style of the next question div to block
        ++currentIndex;
        //currentIndex have to increase to 1 after the nextBtn is clicked        
    }
}


