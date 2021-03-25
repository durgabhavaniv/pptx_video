'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');
var loading = document.getElementById("loading");


function showLoading(){
        loading.innerHTML = "<img id=\"loading-image\" src=\"/resources/images/loading.gif\" alt=\"Loading...\" />";
        loading.style.display = "block";
}

function hideLoading(){
        loading.innerHTML = "";
        loading.style.display = "none";
}


function uploadSingleFile(file) {
    showLoading();
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/card/filter1VideoUploadFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            hideLoading()
            singleFileUploadError.style.display = "none";
            // singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.innerHTML = "<div > <video width=\"640\" height=\"480\" autoplay> <source src='" + response.fileDownloadUri + "' type=\"video/mp4\"> Your browser does not support the video tag. </video> </div> <div class=\"col\"> <p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p> </div>"
            singleFileUploadSuccess.style.display = "block";
        } else {
            hideLoading()
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);