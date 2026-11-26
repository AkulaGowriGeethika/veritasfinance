console.log("editAsset.js loaded");

window.onload = function () {

    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    fetch("editAsset?id=" + id)
        .then(response => response.json())
        .then(asset => {

            console.log(asset);

            document.getElementById("assetId").value = asset.assetId;
            document.getElementById("userId").value = asset.userId;

            document.getElementById("assetName").value = asset.assetName;
            document.getElementById("assetType").value = asset.assetType;
            document.getElementById("estimatedValue").value = asset.estimatedValue;
            document.getElementById("purchaseYear").value = asset.purchaseYear;
            document.getElementById("ownershipType").value = asset.ownershipType;
            document.getElementById("location").value = asset.location;
            document.getElementById("description").value = asset.description;
            document.getElementById("documentPath").value = asset.documentPath;
        });

    document.getElementById("editForm").addEventListener("submit", function (e) {

        e.preventDefault();

        const formData = new FormData(this);

        fetch("updateAsset", {
            method: "POST",
            body: formData
        })
        .then(response => response.text())
        .then(msg => {
            alert(msg);

            if (msg.trim() === "success") {
                window.location = "viewAssets.html";
            }
        })
        .catch(err => {
            console.log(err);
            alert(err);
        });

    });

};