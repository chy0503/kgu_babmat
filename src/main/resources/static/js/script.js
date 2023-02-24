$(document).ready(function () {
    $('.li').click(function () {
        $(this).toggleClass('jbBox');
    });
    $("#search").keydown(function (key) {
        if ($("#search").keyCode == 13) {
            $("#search_form").submit();
        }
    });
    $(".arrow-drop").click(function () {
        var submenu = $("#weekMenu");
        if (submenu.is(":visible")) {
            submenu.slideUp();
            $(".arrow-drop").css("transform", "scaleY(1)");
        } else {
            submenu.slideDown();
            $(".arrow-drop").css("transform", "scaleY(-1)");
        }
    });
    $(".arrow-drop").click(function () {
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

$(document).ready(function () {
    $("#dial_close").click(function () {
        $("#dial_writeReview").css("display", "none");
    });
    $("#write_review_btn1").click(function () {
        $("#dial_writeReview").css("display", "block");
    });
    $("#write_review_btn2").click(function () {
        $("#dial_writeReview").css("display", "block");
    });
});

function clickLike(menu, action) {
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
    f.appendChild(returnObj('price', Number(document.getElementById(menu + 'price').innerText.replace("₩", ""))));
    f.appendChild(returnObj('store', document.getElementById('storeName').innerText));
    f.appendChild(returnObj('selectStore', document.getElementById('selectStoreName').innerText));
    f.setAttribute('method', 'post');
    f.setAttribute('action', action);
    document.body.appendChild(f);
    f.submit();
}

function clickLogout() {
    let f = document.createElement('form');
    f.setAttribute('method', 'post');
    f.setAttribute('action', '/logout');
    document.body.appendChild(f);
    f.submit();
}




