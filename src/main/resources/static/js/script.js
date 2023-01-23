$(document).ready(function () {
    $('.li').click(function () {
        $(this).toggleClass('jbBox');
    });
});

$(document).ready(function(){
    $(".arrow-drop").click(function() {
        var submenu = $("#weekMenu");
        if (submenu.is(":visible")) {
            submenu.slideUp();
            $(".arrow-drop").css("transform", "scaleY(1)");
        } else {
            submenu.slideDown();
            $(".arrow-drop").css("transform", "scaleY(-1)");
        }
    });
});