fetch("profile")

.then(response=>response.json())

.then(user=>{

    document.getElementById("fullName").innerHTML=user.fullName;
    document.getElementById("email").innerHTML=user.email;
    document.getElementById("phone").innerHTML=user.phone;
    document.getElementById("language").innerHTML=user.language;
    document.getElementById("voice").innerHTML=user.voiceEnabled ? "Yes" : "No";

})

.catch(err=>{

    alert("Unable to load profile");

    console.log(err);

});