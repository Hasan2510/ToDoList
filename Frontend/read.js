'use strict';

fetch('http://localhost:8080/person').then(response => {
    if(response.status != 200) {
        console.error(response);

    }
         return response.json();
    }).then(data => {
            let body = document.querySelector("#rp");
            data.forEach(person => {
            let tr = document.createElement("tr");
            let id = document.createElement("td") ;
            let name = document.createElement("td") ;  
            let achievements = document.createElement("td") ;
            id.innerText = person.id;
            name.innerText = person.name;
            person.achievements.forEach(achievement => {
                achievements.innerHTML += `(${achievement.id}) ${achievement.achievement}: ${achievement.complete} <br>`;
            });
            tr.appendChild(id);  
            tr.appendChild(name);  
            tr.appendChild(achievements);
            body.appendChild(tr);  
            });
                
}).catch(err => console.error(err));




