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

function clickLike(menu) {
    let f = document.createElement('form');
    function returnObj(name, value) {
        let obj;
        obj = document.createElement('input');
        obj.setAttribute('type', 'hidden');
        obj.setAttribute('name', name);
        obj.setAttribute('value', value);
        return obj;
    }

    f.appendChild(returnObj('menu', menu));
    f.appendChild(returnObj('store', document.getElementById('storeName').innerText));
    f.appendChild(returnObj('selectStore', document.getElementById('selectStoreName').innerText));
    f.setAttribute('method', 'post');
    f.setAttribute('action', '/store');
    document.body.appendChild(f);
    f.submit();
}

$(document).on("click", "#like", function () {
    let src = ($(this).attr('src') == empty)
        ? full
        : empty;
    $(this).attr('src', src);
});
