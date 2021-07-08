$(document).ready(function (){
    $("#penz").click(function (){
        if ($("#eledel_radio").hasClass("bold_p")){
            $("#eledel_radio").removeClass("bold_p");
        }
        $("#penz_radio").addClass("bold_p");
    });

    $("#eledel").click(function (){
        if ($("#penz_radio").hasClass("bold_p")){
            $("#penz_radio").removeClass("bold_p");
        }
        $("#eledel_radio").addClass("bold_p");
    });
});