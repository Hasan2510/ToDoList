function delete_achievement() {
   let aid= parseInt(document.querySelector("#Achievement_id").value)
    fetch("http://localhost:8080/achievement/"+aid, {//2
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