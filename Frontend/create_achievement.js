'use strict';


fetch("http://localhost:8080/person").then(response => {
    if(response.status != 200) {
        console.error(response);

    }
    return response.json();
}).then(data => {
    let person_select = document.querySelector("#personselect");
    data.forEach(person => {
    let option = document.createElement("option");
    option.value = person.id;
    option.text = person.name;
    person_select.appendChild(option);  
    });
}).catch(err => console.error(err));

 
    
 

function create_achievement() {
fetch("http://localhost:8080/achievement", {
   method: 'post',
    headers: {
      "Content-type": "application/json"
  },
    body: JSON.stringify({
       achievement: document.querySelector("#Achievement").value,
       complete: document.querySelector("#completed").value,
        person: {id: parseInt(document.querySelector("#personselect").value)}
       
     })
    })
    .then(res => {
      if(res.status!=201){
          console.error(res)
      }  
        res.json()})
    .then((data)=> {
       console.log(`Request succeeded with JSON response ${data}`);
   })
   .catch((error)=> {
       console.log(`Request failed ${error}`);
 });
}
