const container = document.getElementById('map'); // 지도를 담을 영역
let options = { // 지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.3014, 127.0373), // 지도의 중심좌표
    level: 4 // 지도의 레벨(확대, 축소 정도)
};
let map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

var imageSrc = 'https://user-images.githubusercontent.com/90389517/212600851-24700888-5f81-40fe-81e7-db62bbf5a7bc.png', // 마커이미지의 주소
    imageSize = new kakao.maps.Size(60, 60), // 마커이미지의 크기
    imageOption = {offset: new kakao.maps.Point(32, 69)}; // 마커이미지의 옵션. 마커의 좌표와 일치시킬 이미지 안에서의 좌표 설정.

var biggerImageSize = new kakao.maps.Size(61, 61); // 마커이미지의 크기


// 마커의 이미지정보를 가지고 있는 마커이미지 생성
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
var biggerMarkerImage = new kakao.maps.MarkerImage(imageSrc, biggerImageSize, imageOption);

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
        window.open('/store?storeName=' + storeNames[i]);
    });

    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, customOverlay));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));
}

function makeContent(storeName) {
    return '<div class="customoverlay">' +
        '  <a href="/store" target="_self">' +
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

function makeOutListener(customOverlay) {
    return function() {
        customOverlay.setMap(null)
    };
}

// // 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
// function addMarker(position, normalOrigin, overOrigin, clickOrigin) {
//
//     // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
//     var normalImage = createMarkerImage(markerSize, markerOffset, normalOrigin),
//         overImage = createMarkerImage(overMarkerSize, overMarkerOffset, overOrigin),
//         clickImage = createMarkerImage(markerSize, markerOffset, clickOrigin);
//
//     // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
//     var marker = new kakao.maps.Marker({
//         map: map,
//         position: position,
//         image: normalImage
//     });
//
//     // 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
//     marker.normalImage = normalImage;
//
//     // 마커에 mouseover 이벤트를 등록합니다
//     kakao.maps.event.addListener(marker, 'mouseover', function() {
//
//         // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
//         // 마커의 이미지를 오버 이미지로 변경합니다
//         if (!selectedMarker || selectedMarker !== marker) {
//             marker.setImage(overImage);
//         }
//     });
//
//     // 마커에 mouseout 이벤트를 등록합니다
//     kakao.maps.event.addListener(marker, 'mouseout', function() {
//
//         // 클릭된 마커가 없고, mouseout된 마커가 클릭된 마커가 아니면
//         // 마커의 이미지를 기본 이미지로 변경합니다
//         if (!selectedMarker || selectedMarker !== marker) {
//             marker.setImage(normalImage);
//         }
//     });
//
//     // 마커에 click 이벤트를 등록합니다
//     kakao.maps.event.addListener(marker, 'click', function() {
//
//         // 클릭된 마커가 없고, click 마커가 클릭된 마커가 아니면
//         // 마커의 이미지를 클릭 이미지로 변경합니다
//         if (!selectedMarker || selectedMarker !== marker) {
//
//             // 클릭된 마커 객체가 null이 아니면
//             // 클릭된 마커의 이미지를 기본 이미지로 변경하고
//             !!selectedMarker && selectedMarker.setImage(selectedMarker.normalImage);
//
//             // 현재 클릭된 마커의 이미지는 클릭 이미지로 변경합니다
//             marker.setImage(clickImage);
//         }
//
//         // 클릭된 마커를 현재 클릭된 마커 객체로 설정합니다
//         selectedMarker = marker;
//     });
// }