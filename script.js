function validateForm() {
    var customerName = document.getElementById("customerName").value;
    var email = document.getElementById("email").value;

    if (sessionStorage.getItem("isloggedIn")!=null){
      var password =  JSON.parse(sessionStorage.getItem("cust_database"))[0][0].password
    }
    else{
      var password = document.getElementById("password").value;
    }
    var address = document.getElementById("address").value;
    var contactNumber = document.getElementById("contactNumber").value;
    var isValid = true;
    // Validation for Customer Name
    if (!/^[A-Za-z ]+$/.test(customerName)) {
      alert("Customer Name must have alphabets only.");
      isValid=false;
      return false;
    }
  
  
    // Validation for Email
    if (!email.includes("@")) {
      alert("Email id not valid.");
      isValid=false;
      return false;
    }
  
    // Validation for Password
    if (!/(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+/.test(password)) {
      alert("Password is not matching the criteria.");
      isValid=false;
      return false;
    }
  
    // Validation for Address
    if (address.trim() === "") {
      alert("Address field must not be blank.");
      isValid=false;
      return false;
    }
  
    // Validation for Contact Number
    if (!/^[0-9]+$/.test(contactNumber)) {
      alert("Contact number must not have any alphabets.");
      isValid=false;
      return false;
    }
  
    if (sessionStorage.getItem("isloggedIn")!=null){
      var customerId = JSON.parse(sessionStorage.getItem("cust_database"))[0][0].customerId;
    }
    else{
      var customerId = Math.floor(Math.random() * 1000);
    }
    // Simulate a random customer ID (replace with actual generation logic)
    //var customerId = Math.floor(Math.random() * 1000);
  
    // Display the acknowledgment screen
    /*
    document.getElementById("acknowledgment").classList.remove("hidden");
    document.getElementById("customerId").textContent = customerId;
    document.getElementById("customerNameAck").textContent = customerName;
    document.getElementById("emailAck").textContent = email;
    // Prevent form submission (you can send data to a server here)
    console.log(isValid);
    */
    if(isValid)
    {
        var params = {
          customerName:customerName,
          customerId:customerId,
          email:email,
          address:address,
          contactNumber:contactNumber
    }
        var encodeParams = Object.entries(params).map(([key,value])=>encodeURIComponent(key)+'='+encodeURIComponent(value)).join('&');
         //window.location.href="success.html";
         console.log("Data is Valid");
         //Storing in sessions
         
         cust_database = []
         myCust = [{customerId:customerId,customerName:customerName,password:password,address:address,contactNumber:contactNumber,email:email}]
         cust_database.push(myCust)
         
         sessionStorage.setItem("cust_database",JSON.stringify(cust_database))
         
         window.location.href="success.html?"+encodeParams;
         //sessionStorage.setItem('myData') = 'mydata1';
         
    }
    return false;
  }


  function onLogin(){
    var loginCustomerId = document.getElementById("loginCustomerId").value;
    var loginPassword = document.getElementById("loginPassword").value;

    console.log(loginCustomerId,loginPassword)

    var cust_database = JSON.parse(sessionStorage.getItem("cust_database"))

    var idFlag = true;
    var passFlag = true;
    

    if(loginCustomerId!=cust_database[0][0].customerId){
      idFlag = false
    }
    if(loginPassword!=cust_database[0][0].password){
      passFlag=false;
    }

    if (idFlag==false && passFlag==true){
      alert("ID not valid")
    }
    else if(idFlag==true && passFlag==false){
      alert("Password not valid")
    }
    else if(idFlag==false && passFlag==false){
      alert("ID/password not valid")
    }
    else{
      sessionStorage.setItem("isloggedIn","true");
      window.location.href="home.html";
    }

    function commitLogout(){
      if (document.getElementById("login_or_logout").innerHTML == "Logout"){
        sessionStorage.removeItem("isloggedIn");
      }
    }
    
  }
  
  function onProfile(){
    cust_database = JSON.parse(sessionStorage.getItem("cust_database"));
    console.log(cust_database);
    document.getElementById("customerIDProfile").value = cust_database[0][0].customerId
    document.getElementById("customerName").value = cust_database[0][0].customerName
    document.getElementById("email").value = cust_database[0][0].email
    document.getElementById("address").value = cust_database[0][0].address
    document.getElementById("contactNumber").value = cust_database[0][0].contactNumber
  }

  function enableUpdate(){
    document.getElementById("customerName").disabled = false;
    document.getElementById("email").disabled = false;
    document.getElementById("address").disabled = false;
    document.getElementById("contactNumber").disabled = false;
    alert("Editing Enabled");
  }
  //document.window

  function checkout(){
    window.location.href = "ordersuccess.html"
  }
