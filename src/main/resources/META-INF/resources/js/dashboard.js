var STORE_ORIGIN = window.location.origin;

function publish(type, source, data, callback) {
    let outputStr = data.replace(/ /g, '%20');
    var theUrl = STORE_ORIGIN + '/events?type=' + type + "&src=" + source + "&data=" + outputStr;
    $.ajax({
        url: theUrl,
        type: 'POST',
        dataType: 'json',
        complete: function(response, status, xhr){
            callback(response.responseText);
        }
    });
};

function showPublishResult(id) {
    var content = 'Evento correttamente creato con identificativo: <b>' + id + '</b>';
    $("#save_ok").html(content);
    $("#save_ok").fadeIn();
    window.setTimeout(function () { 
        $("#save_ok").fadeOut();
        $("#btn_save").prop('disabled', false);
    }, 4000);
};