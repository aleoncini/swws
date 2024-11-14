var STORE_ORIGIN = window.location.origin;

// --- Formatting FUNCTIONS -----------------------------------------
function displayPratiche(pratiche, showButtons) {
    $("#wf_list").empty();
    $.each(pratiche, function (index, p) {
        addPToList(p, showButtons);
    });
    $('#wf_list').show(500);
};

function addPToList(p, showButtons) {
    var date = new Date(p.date);
    var dateString = date.toLocaleString();//date.toDateString() + ' - ' + date.toTimeString();
    var appr = '';
    if(p.approved == 0){
        appr = "PENDING APPROVAL";
    }
    if(p.approved == 1){
        appr = "APPROVED";
    }
    if(p.approved == -1){
        appr = "REFUSED";
    }
    var rowContent = '<a href\"' + p.fonte + '\" class=\"list-group-item list-group-item-action flex-column align-items-start\">';
    rowContent += '<div class=\"d-flex w-100 justify-content-between\">';
    rowContent += '  <h5 class=\"mb-1\">' + p.id + '</h5>';
    rowContent += '  <small class=\"text-muted\">status: ' + p.status + '</small>';
    rowContent += '</div>';
    rowContent += '<p class=\"mb-1\">' + dateString + '</p>';
    rowContent += '<p class=\"mb-1\">' + p.note + '</p>';
    rowContent += '<p class=\"mb-1\">This workflow is ' + appr + '</p>';
    if(showButtons){
        rowContent += '<br><div style=\"position: relative; float: right;\">';
        rowContent += '<button type=\"button\" data-id="' + p.id + '" class=\"approve_button btn btn-sm btn-outline-success\">Approve</button>&nbsp;';
        rowContent += '<button type=\"button\" data-id="' + p.id + '" class=\"refuse_button btn btn-sm btn-outline-warning\">Refuse</button>&nbsp;';
        rowContent += '</div>';
    }
    rowContent += '</a>';

    $('#wf_list').append(rowContent);
};

// --- Users API  FUNCTIONS -----------------------------------------
function loadPraticheDaApprovare(callbackFunction) {
    var theUrl = STORE_ORIGIN + '/pratiche/tba';
    $.ajax({
        url: theUrl,
        type: 'GET',
        dataType: 'json',
        complete: function(response, status, xhr){
            var pratiche = jQuery.parseJSON(response.responseText);
            callbackFunction(pratiche, true);
        }
    });
};

function loadPratiche(callbackFunction) {
    var theUrl = STORE_ORIGIN + '/pratiche';
    $.ajax({
        url: theUrl,
        type: 'GET',
        dataType: 'json',
        complete: function(response, status, xhr){
            var pratiche = jQuery.parseJSON(response.responseText);
            callbackFunction(pratiche, false);
        }
    });
};

function approve(id, appr, callbackFunction) {
    var theUrl = STORE_ORIGIN + '/pratiche/' + id + '?approved=' + appr + '&status=IN+PROGRESS';
    $.ajax({
        url: theUrl,
        type: 'POST',
        dataType: 'json',
        complete: function(response, status, xhr){
            callbackFunction(id);
        }
    });
};