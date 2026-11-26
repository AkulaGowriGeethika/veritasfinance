function getAIAdvice(){

let score=document.getElementById("score").value;

fetch("gemini?score="+score)

.then(response=>response.text())

.then(data=>{

document.getElementById("result").innerHTML=data;

});

}