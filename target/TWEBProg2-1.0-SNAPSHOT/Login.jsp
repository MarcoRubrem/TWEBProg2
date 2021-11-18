
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
    <a class="navbar-brand" href="index.jsp">Prenotazoni Unito</a>

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
                    <form action="btn" method="get">
                        <input type="submit" class="dropdown-item" name="btn" value="Corsi"/>
                        <input type="submit" class="dropdown-item" name="btn" value="Ripetizioni"/>
                        <input type="submit" class="dropdown-item" name="btn" value="Prenotazioni"/>
                    </form>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="btn" method="get">
            <input type="submit" class="btn btn-outline-danger" name="btn" role="button" value="Registrazione">
            <input type="submit" class="btn btn-success" name="btn" value="Login" role="button">

        </form>
    </div>
</nav>

<form method="post">
    <div class="form-group">
        <div id="accerr"></div>
        <label for="account">Account</label>
        <input type="text" class="form-control" id="account" placeholder="Account">
    </div>
    <div class="form-group">
        <label for="pw">Password</label>
        <input type="password" class="form-control" id="pw" placeholder="Password">
    </div>

    <button type="button" id="Login" onclick="login()" class="btn btn-primary">Submit</button>
</form>
    <script>


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

            var account = document.getElementById("account").value;
            var pw = document.getElementById("pw").value;
            var params = {
                account: account,
                pw: pw
            };
            $(document).on("click", "#Login", function() {// When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                $.post("login", $.param(params), function(responseText) {
                    $("#accerr").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                });
            });
        }

    </script>

    <script type="text/javascript">
        var xhrObj = setXMLHttpRequest();
    </script>



</body>
</html>
