$(document).ready(function(){
    $('#post-btn').on('click', function(e){
        e.preventDefault();
        let dataObj = {
            "domain": $("#domain"). val(),
            "type": $("#type"). val(),
            "postalCode": $("#postal"). val(),
            "officeCode": $("#office"). val(),
            "assignee": "",
            "targetOffice": $("#target"). val(),
            "motivation": $("#motivation"). val()
        };

        $.ajax({
            url:'https://roboranademo.appiancloud.com/suite/webapi/request',
            headers:{
                'Authorization': 'Basic Z2xlbm4uZG9ybWFlbHNAY3Jvbm9zLmJlOkRyb21tZWRhcmlzMg=='
            },
            type:'POST',
            data: JSON.stringify(dataObj),
            contentType: 'application/json',
            dataType: 'JSON'
        }).done(function(result){
            alert("succes");
        }).fail(function(result){
            alert("fail");
        });
    });

    $('#get-btn').on('click', function(e){
        $.ajax({
            url:'https://roboranademo.appiancloud.com/suite/webapi/getAuthorisationRequestDomain?requestId='+$('#id').val(),
            headers:{
                'Authorization': 'Basic Z2xlbm4uZG9ybWFlbHNAY3Jvbm9zLmJlOkRyb21tZWRhcmlzMg=='
            },
            type:'GET',
            contentType: 'application/json',
            dataType: 'JSON'
        }).done(function(result){
            alert("succes");
            $('#getDomain').append(result[0].domain);
        }).fail(function(result){
            alert("fail");
        });
    });
});

function getData(){
    $.ajax({
        url:'https://roboranademo.appiancloud.com/suite/webapi/getAuthorisationRequestDomain?requestId=1',
        headers:{
            'Authorization': 'Basic Z2xlbm4uZG9ybWFlbHNAY3Jvbm9zLmJlOkRyb21tZWRhcmlzMg=='
        },
        type:'GET',
        contentType: 'application/json',
        dataType: 'JSON'
    }).done(function(result){
        alert("succes");
        $('#getDomain').append(result[0].domain);
    }).fail(function(result){
        alert("fail");
    });
}