
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registrazione</title>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="btn?btn=home">Prenotazioni Unito</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
                <a class="nav-link" href="btn?btn=home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="btn?btn=Registrazione">Registrazione<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="btn?btn=Login">Login</a>
            </li>
        </ul>
    </div>
</nav>

<form>
    <div class="form-group">
        <div id="accerr"></div>
        <label for="account">Account</label>
        <input type="text" class="form-control" id="account" placeholder="Account">
    </div>
    <div class="form-group">
        <label for="pw">Password</label>
        <input type="password" class="form-control" id="pw" placeholder="Password">
    </div>
    <p>Ruolo</p>
    <div class="form-check-inline">
        <input class="form-check-input" type="radio" name="radio" id="Cliente" value="Cliente" checked>
        <label class="form-check-label" for="Cliente">
            Cliente
        </label>
    </div>
    <div class="form-check-inline">
        <input class="form-check-input" type="radio" name="radio" id="Amministratore Sito" value="Amministratore Sito">
        <label class="form-check-label" for="Amministratore Sito">
            Amministratore Sito
        </label>
    </div>
    </br>
    <button type="button" id="Reg" onclick="reg()" class="btn btn-primary">Submit</button>
</form>
<script>
    var accerr;

    function setXMLHttpRequest() {
        var xhr = null;
        if (window.XMLHttpRequest) {      // browser standard con supporto nativo
            xhr = new XMLHttpRequest();
        } else if (window.ActiveXObject) {   // browser MS Internet Explorer 6 o 						// precedente - ActiveX
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xhr;
    }
    function reg() {

        var account = document.getElementById("account").value;
        var pw = document.getElementById("pw").value;
        var ruolo = document.getElementById("radio");
        accerr = document.getElementById("accerr");
        var url = "reg?account=" + account + "&pw="+pw+"&Ruolo="+ruolo;
        xhrObj.open("post", url, true);
        xhrObj.responseType = 'text';
        xhrObj.onreadystatechange = updatePage;
        xhrObj.send(null);

    }
    function updatePage() {


        if (xhrObj.readyState === 4) {
            if (xhrObj.status === 200) {
                var risp = xhrObj.responseText;
                if(risp === "loggato"){

                    window.location.replace("index.jsp");
                }
                else {

                    accerr.innerHTML = risp;
                }
            }
        }
    }

</script>

<script type="text/javascript">
    const xhrObj = setXMLHttpRequest();
</script>
</body>
</html>