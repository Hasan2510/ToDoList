function Update_ach() {
    let Aid= parseInt(document.querySelector("#UID").value)
    fetch("http://localhost:8080/achievement/"+Aid, {
       method: 'put',
        headers: {
          "Content-type": "application/json"
      },
        body: JSON.stringify({
           achievement: document.querySelector("#UA").value,
           complete: document.querySelector("#UAC").value,
            
           
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
    