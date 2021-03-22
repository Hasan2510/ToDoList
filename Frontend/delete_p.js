function delete_person() {
    let pid= parseInt(document.querySelector("#person_id").value)
     fetch("http://localhost:8080/person/"+pid, {//2
         method: 'delete',//3
       })
       .then((data) => {
         console.log(`Request succeeded with JSON response ${data}`);
         // some function to execute if successful
       })
       .catch((error) => {
         //some function to execute if error
       });
 }