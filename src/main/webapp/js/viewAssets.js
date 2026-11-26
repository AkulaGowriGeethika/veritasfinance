window.onload = loadAssets;

function loadAssets() {

    fetch("assets")
        .then(response => response.json())
        .then(data => {

            let table = document.getElementById("assetTable");
            table.innerHTML = "";

            data.forEach(asset => {

                let row = `
                <tr>

                    <td>${asset.assetId}</td>

                    <td>${asset.assetName}</td>

                    <td>${asset.assetType}</td>

                    <td>${asset.estimatedValue}</td>

                    <td>${asset.purchaseYear}</td>

                    <td>${asset.ownershipType}</td>

                    <td>${asset.location}</td>

                    <td>${asset.documentPath}</td>

                    <td>
                        <button class="edit"
                        onclick="editAsset(${asset.assetId})">
                        Edit
                        </button>
                    </td>

                    <td>
                        <button class="delete"
                        onclick="deleteAsset(${asset.assetId})">
                        Delete
                        </button>
                    </td>

                </tr>
                `;

                table.innerHTML += row;

            });

        })

        .catch(error => {
            console.log(error);
        });

}

function editAsset(id){

    window.location =
    "editAsset.html?id=" + id;

}

function deleteAsset(id){

    if(confirm("Delete this asset?")){

        fetch("deleteAsset?id=" + id,{
            method:"DELETE"
        })

        .then(response=>response.text())

        .then(msg=>{

            alert(msg);

            loadAssets();

        });

    }

}