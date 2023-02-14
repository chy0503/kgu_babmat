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
    f.appendChild(returnObj('price', Number(document.getElementById(menu+'price').innerText.replace("₩", ""))));
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

function writeReview() {
    let f = document.createElement('form');
    function returnObj(name, value) {
        let obj;
        obj = document.createElement('input');
        obj.setAttribute('type', 'hidden');
        obj.setAttribute('name', name);
        obj.setAttribute('value', value);
        return obj;
    }

    f.appendChild(returnObj('selectStoreName', document.getElementById('selectStoreName').innerText));
    f.appendChild(returnObj('storeName', document.getElementById('storeName').innerText));
    f.appendChild(returnObj('menu', document.getElementById('menu').innerText));
    f.appendChild(returnObj('reviewScore', Number(document.getElementById('reviewScore').innerText)));
    f.appendChild(returnObj('review', Number(document.getElementById('review').innerText)));
    f.setAttribute('method', 'post');
    f.setAttribute('action', '/reviewCreate');
    document.body.appendChild(f);
    f.submit();
}