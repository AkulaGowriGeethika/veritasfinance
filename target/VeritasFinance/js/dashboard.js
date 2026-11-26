fetch("dashboard")

.then(response => response.json())

.then(data => {

    document.getElementById("totalAssets").innerHTML =
    data.totalAssets;

    document.getElementById("assetValue").innerHTML =
    "₹ " + Number(data.totalAssetValue).toLocaleString("en-IN");

    document.getElementById("trustScore").innerHTML =
    data.trustScore;

})

.catch(error => {

    console.log(error);

});