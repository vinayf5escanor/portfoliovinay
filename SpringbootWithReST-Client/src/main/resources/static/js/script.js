function getStudent(id) {
fetch('http://localhost:8080/fetch-student/' + id) // fetch data from our service
.then(data => data.json()) // JSONify the data returned
.then(function(data) { // with the JSON data
// modify textToDisplay below here!
var textToDisplay = ""; // create and append to a blank var
textToDisplay += "ID: " + data.student.id + "<br>";
textToDisplay += "Name: " + data.student.name + "<br>";
// finally, change our relevant div to display the var
document.getElementById("student" + id).innerHTML = textToDisplay;
});
}