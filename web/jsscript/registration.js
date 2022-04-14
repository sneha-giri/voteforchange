let username,password,cpassword,city,address,adhar,email,mobile;
function addUser() {
    username = $("username").val();
    password = $("password").val();
    cpassword = $("cpassword").val();
    city = $("city").val();
    address = $("address").val();
    adhar = $("adhar").val();
    email= $("email").val();
    mobile = $("mobile").val();
    if (validateUser()) {
        if (password!==cpassword) {
            swal("Error!", "Passwords do not match", "error");
            return;
        } 
        else {
            if (checkEmail()===false)
                return;
            let data = {
                username: username,
                password: password,
                cpassword: cpassword,
                city: city,
                address: address,
                adhar: adhar,
                email: email,
                mobile: mobile
            };
            let xhr = $.post("RegistrationControllerServlet", data, processresponse);
            xhr.fail(handleError);
        }
    }
}
function processresponse(responseText,textStatus,xhr) {
    let str = responseText.trim();
    if (str === "success") {
        swal("Success", "Registration done successfully!you can now login", "success");
        setTimeout(redirectUser,3000);

    } else if (str === "uap")
        swal("Error", "Sorry!the userid is already present!", "error");
    else
        swal("Error", "Registration failed!Try again", "error");
}
function handleError(xhr) {
    swal("Error", "Problem in server communication:" + xhr.statusText, "error");
}
function validateUser() {
    if (username === "" || password === "" || cpassword === "" || city === "" || address === "" || adhar === "" || email === "" || mobile === "") {
        swal("Error!", "All fields are mandatory", "error");
        return false;
    }
    return true;
}
function checkEmail(){
    let dotpos=email.indexOf("@");
    let attheratepos=email.indexOf(".");
    if (attheratepos < 1 || dotpos < attheratepos + 2 || dotpos + 2 > email.length) {
        swal("Error!", "Please enter a valid email", "error");
        return false;
    }
    return true;
}
function redirectUser() {
    window.location = "login.html";
}
