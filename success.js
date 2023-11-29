function getData(){
    var queryString=window.location.search;
    var urlParams = new URLSearchParams(queryString);
    var customerName = urlParams.get('customerName');
    var customerId = urlParams.get('customerId');
    var email = urlParams.get('email');
    console.log({customerName,customerId,email})
    document.getElementById("acknowledgment").classList.remove("hidden");
    document.getElementById("customerId").textContent = customerId;
    document.getElementById("customerNameAck").textContent = customerName;
    document.getElementById("emailAck").textContent = email;
}
