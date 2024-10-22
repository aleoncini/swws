var STORE_ORIGIN = 'http://dybala:8080';
var IDP_ORIGIN = 'https://lukaku:8443';
const keycloak = new Keycloak();

async function kc_init() {
  console.log('=========> calling Keycloak init');
    try {
        keycloak.init({
            onLoad: 'login-required'
        });
        console.log(`=========> User is ${keycloak.authenticated ? 'authenticated' : 'not authenticated'}`);
    } catch (error) {
        console.error('=========> Failed to initialize adapter:   ', error);
    }
}

function start() {
  return kc_init();
}

// --- OIDC FUNCTIONS -----------------------------------------
function initKeycloak(callback) {
    console.log('=========> initializing keycloak.');
    keycloak.init({
        onLoad: 'login-required'
    }).then(function(authenticated){
        if(authenticated) {
            console.log("=========> Authenticated as " + keycloak.idTokenParsed.name);
            console.log("=========>          user ID " + keycloak.subject);
            if (typeof callback == 'function'){
                callback(keycloak.subject);
            }
        } else {
            alert("Authentication failed");
        }
    });
}

function getAccessToken() {
    var theUrl = IDP_ORIGIN + '/realms/ask/protocol/openid-connect/token';
    $.ajax({
        headers: {
            'Access-Control-Allow-Origin': 'http://dybala:8080'
        },
        type: "POST",
        url: theUrl,
        body: "client_id=ask&password=password&username=andrea&grant_type=password",
        contentType: "application/x-www-form-urlencoded",
        complete: function(response, status, xhr){
            var token = response.responseText;
            console.log(token);
        }
    });
};

function callUserApi() {
    getAccessToken();
};

// --- Users API FUNCTIONS -----------------------------------------
function getUser() {
    var theUrl = STORE_ORIGIN + '/api/users/me';
    $.ajax({
        url: theUrl,
        type: 'GET',
        dataType: 'json',
        beforeSend: function(req) {
            req.setRequestHeader('Authorization', 'Bearer ' + keycloak.token);
        },
        complete: function(response, status, xhr){
            var keyObject = jQuery.parseJSON(response.responseText);
            console.log('Key READY...');
        }
    });
};