function startVoice(){

let language=document.getElementById("language").value;

fetch("voice?language="+language)

.then(response=>response.text())

.then(data=>{

document.getElementById("voiceText").innerHTML=data;

let speech=new SpeechSynthesisUtterance(data);

speech.lang="en-US";

if(language==="Telugu")
speech.lang="te-IN";

if(language==="Hindi")
speech.lang="hi-IN";

speechSynthesis.speak(speech);

});

}