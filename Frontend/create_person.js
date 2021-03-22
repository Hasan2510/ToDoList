'use strict';



function createperson() {
    fetch("http://localhost:8080/person/", {
       method: 'post',
        headers: {
          "Content-type": "application/json"
      },
        body: JSON.stringify({
           name: document.querySelector("#personname").value,
           age: parseInt(document.querySelector("#age").value)
            
           
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
