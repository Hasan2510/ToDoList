function Update_per() {
    let Pid= parseInt(document.querySelector("#PID").value)
    fetch("http://localhost:8080/person/"+Pid, {
       method: 'put',
        headers: {
          "Content-type": "application/json"
      },
        body: JSON.stringify({
           name: document.querySelector("#personname").value,
           person_age:{age: parseInt(document.querySelector("#age").value)}
            
           
         })
        })
        .then(res => {
          if(res.status!=200){
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
