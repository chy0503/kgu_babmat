const container = document.getElementById('map'); // 지도를 담을 영역
let options = { // 지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.3014, 127.0373), // 지도의 중심좌표
    level: 4 // 지도의 레벨(확대, 축소 정도)
};
let map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

var imageSrc = 'https://user-images.githubusercontent.com/90389517/212600851-24700888-5f81-40fe-81e7-db62bbf5a7bc.png', // 마커이미지의 주소
    imageSize = new kakao.maps.Size(60, 60), // 마커이미지의 크기
    imageOption = {offset: new kakao.maps.Point(32, 69)}; // 마커이미지의 옵션. 마커의 좌표와 일치시킬 이미지 안에서의 좌표 설정.

var biggerImageSize = new kakao.maps.Size(65, 65), // 마커이미지의 크기
    biggerImageOption = {offset: new kakao.maps.Point(34, 74)};


// 마커의 이미지정보를 가지고 있는 마커이미지 생성
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
var biggerMarkerImage = new kakao.maps.MarkerImage(imageSrc, biggerImageSize, biggerImageOption);

var positions = [
    {
        latlng: new kakao.maps.LatLng(37.30318, 127.0359)
    },
    {
        latlng: new kakao.maps.LatLng(37.30053, 127.03715)
    },
    {
        latlng: new kakao.maps.LatLng(37.30115, 127.03613)
    },
    {
        latlng: new kakao.maps.LatLng(37.29775, 127.03852)
    },
    {
        latlng: new kakao.maps.LatLng(37.3019, 127.03665)
    }
];

var storeNames = ['경슐랭', '이스퀘어 식당', '감성코어', '기숙사 식당', '플랜비'];

for (let i = 0; i < positions.length; i ++) {
    var marker = new kakao.maps.Marker({
        map: map,
        title: storeNames[i],
        position: positions[i].latlng,
        image : markerImage
    });

    var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: positions[i].latlng,
        content: makeContent(storeNames[i]),
        yAnchor: 1
    });

    customOverlay.setMap(null);

    kakao.maps.event.addListener(marker, 'click', function () {
        if (storeNames[i] == "경슐랭")
            window.open('/selectStore?storeName=' + storeNames[i], "_self");
        else
            window.open('/store?storeName=' + storeNames[i], "_self");
    });

    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, customOverlay));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(map, marker, customOverlay));
}

function makeContent(storeName) {
    return '<div class="customoverlay">' +
        '  <a href="#">' +
        '    <span class="title">' + storeName + '</span>' +
        '  </a>' +
        '</div>'
}

function makeOverListener(map, marker, customOverlay) {
    return function() {
        marker.setImage(biggerMarkerImage);
        customOverlay.setMap(map);
    };
}

function makeOutListener(map, marker, customOverlay) {
    return function() {
        marker.setImage(markerImage);
        customOverlay.setMap(null)
    };
}