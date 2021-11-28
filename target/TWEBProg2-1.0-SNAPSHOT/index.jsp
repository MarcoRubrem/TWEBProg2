<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Prenotazioni Unito</title>

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
            <li class="nav-item active">
                <a class="nav-link" href="btn?btn=home">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" onclick="reg()">Registrazione</a>
            </li>
            <li class="nav-item">
        </ul>
    </div>
</nav>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Nome</th>
        <th scope="col">Cognome</th>
        <th scope="col">Corso</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Mark</td>
        <td>Otto</td>
        <td>@mdo</td>
    </tr>
    <tr>
        <td>Jacob</td>
        <td>Thornton</td>
        <td>@fat</td>
    </tr>
    </tbody>
</table>



<div id="app">
    <p> Login: <input v-model="account" type="text"> </p>
    <button v-on:click="getInfo">OK</button>
    <p>JSessionID: {{ sessione }}</p>
    <p>Controllo sessione: {{ altreInfo }}</p>
    <p>account: {{ account }}</p>
</div>

<script>

    function reg(){

        var r = confirm("Per registrare un nuovo profilo, uscirai da questa sessione. Vuoi continuare?");

        if(r){

            window.location.replace("btn?btn=Registrazione");
        }

    }


    var app = new Vue ({
        el: '#app',
        data: {
            sessione:'sessione inesistente',
            altreInfo:'---',
            account:  'AAAA',
            link :    'ServletSessions'
        },
        methods:{
            getInfo:function(){
                var self = this;
                if (self.sessione==='sessione inesistente')
                    $.post(this.link, {utente: this.account}, function (data) {
                        self.sessione = data;
                    });
                else
                    $.post(this.link, {utente: this.account, sessione: this.sessione},
                        function (data) {
                            self.altreInfo = data;
                        });
            }
        }
    });
</script>


</br>
<a href="ServletSessions">Hello Servlet</a>
</body>
</html>