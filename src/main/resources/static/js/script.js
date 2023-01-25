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

const empty = 'https://user-images.githubusercontent.com/90389517/214607565-4f95f8d6-2ca0-4b5d-98da-fca4637aac41.png';
const full = 'https://user-images.githubusercontent.com/90389517/214608922-ab6c4c5c-9b94-4645-bfdb-d98fd2e06949.png';
$(document).on("click", "#like", function () {
    let src = ($(this).attr('src') == empty)
        ? full
        : empty;
    $(this).attr('src', src);
});
