/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global data, swal */

function addvote(){
    var id= $('input[type=radio][mane=flat]:checked').attr('id');
    data=(candidateid:id);
    $.post("AddVoteControllerServlet",data,processresponse);
}

function processresponse(responseText){
    responseText=responseText.trim();
    if(responseText==="success"){
        swal("Success","Voting Done","success").then((value)=>{
            window.location="votingresponse.jsp";
        });
        }
        else{
        swal("Failure","Voting Failed","error").then((value)=>{
            window.location="votingresponse.jsp";
        });
}
}