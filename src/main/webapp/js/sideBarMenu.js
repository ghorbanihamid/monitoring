// JavaScript Document

var backGroundImage;

$(document).ready(function(){
       // main menu in hub sample
    initMenu();

});



(function (jQuery) {
    jQuery.fn.recipient = function () {
        $('#add').click(function(){
            var v = $("#r1").val();
            $("#r2").append('<option value="'+v+'">'+v+'</option>');
            $("#r1").val('');
        });
    };
})(jQuery);


function initMenu() {


    $('#sideBarMenu ul').show();
    $('#sideBarMenu ul ul').hide();

    if( $("#right_menu").val() == 'null' || $("#right_menu").val() == undefined ) {
        $("#sideBarMenu ul a[href='"+$("#right_menu").val()+"']").addClass("selected");

    } else {
        var elementUl = $("#"+$("#right_menu").val());
        if(elementUl.parent().parent().is('ul')){
            elementUl.parent().parent().show();
        }
        $("#sideBarMenu ul a[id='"+$("#right_menu").val()+"']").addClass("selected");
    }

    $('#sideBarMenu ul li a').click(

            function() {

                var checkElement = $(this).next();
                if(checkElement.is('ul') && $(this).parent().parent().parent().is('div') && checkElement.is(':visible')) {
                    checkElement.slideUp('normal');

                    return false;
                }
                else if(checkElement.is('ul') && checkElement.is(':visible')) {
                    checkElement.slideUp('normal');
                    return false;
                }
                if(checkElement.is('ul') && !checkElement.is(':visible')) {
                    if(checkElement.parent().parent().parent().is('div')) {
                        $('#sideBarMenu ul ul').slideUp('normal');

                    } else {
                        $('#sideBarMenu ul ul').slideUp('normal');
                    }
                    checkElement.slideDown('normal');
                    return false;
                }
            }
            );

}

