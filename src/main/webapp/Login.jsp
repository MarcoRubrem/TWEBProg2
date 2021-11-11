
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Prenotazioni Unito</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Elenchi
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Corsi</a>
                    <a class="dropdown-item" href="#">Ripetizioni</a>
                    <a class="dropdown-item" href="#">Prenotazioni</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <a class="btn btn-outline-success" href="Registrazione.jsp" role="button">Registrazione</a>
            <button type="button" class="btn btn-danger">Login</button>

        </form>
    </div>
</nav>

<form>
    <div class="form-group">
        <div id="accerr"></div>
        <label for="account">Account</label>
        <input type="text" class="form-control" id="account" placeholder="Account">
    </div>
    <div class="form-group">
        <div id="pwerr"></div>
        <label for="pw">Password</label>
        <input type="password" class="form-control" id="pw" placeholder="Password">
    </div>

    <button type="button" onclick="login()" class="btn btn-primary">Submit</button>

    <script>
        var account;
        var pw;
        function setXMLHttpRequest() {
            var xhr = null;
            if (window.XMLHttpRequest) {      // browser standard con supporto nativo
                xhr = new XMLHttpRequest();
            } else if (window.ActiveXObject) {   // browser MS Internet Explorer 6 o 						// precedente - ActiveX
                xhr = new ActiveXObject("Microsoft.XMLHTTP");
            }
            return xhr;
        }
        function login() {
            account = document.getElementById("account").value;
            pw = document.getElementById("pw").value;

            if(account === "") {

                accerr = document.getElementById("accerr");
                accerr.innerHTML = "Account obbligatorio";

            }
            else{

                accerr.innerHTML = "";

            }
            if (pw === ""){

                pwerr = document.getElementById("pwerr");
                pwerr.innerHTML = "Password obbligatoria";
            }
            else{

                pwerr.innerHTML = "";

            }

            var url = "login?account=" + account + "&pw="+pw;

            xhrObj.open("post", url, true);
            xhrObj.onreadystatechange = updatePage;
            xhrObj.send(null);
        }
        function updatePage() {
            var risp;
            var accerr;

            if (xhrObj.readyState === 4) {

                risp = xhrObj.responseText;
                accerr = document.getElementById("accerr");
                accerr.innerHTML = risp;

            }
        }
    </script>

    <script type="text/javascript">
        var xhrObj = setXMLHttpRequest();
    </script>


</form>
</body>
</html>
