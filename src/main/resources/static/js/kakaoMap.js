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
function makeMainMap() {
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
}
function makeMarker(name, selectStore, lat, lng) {
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(lat, lng),
        image : markerImage
    });

    var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: new kakao.maps.LatLng(lat, lng),
        content: makeContent(name),
        yAnchor: 1
    });

    customOverlay.setMap(null);

    kakao.maps.event.addListener(marker, 'click', function () {
        if (selectStore == true)
            window.open('/selectStore?storeName=' + name, "_self");
        else
            window.open('/store?storeName=' + name, "_self");
    });

    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, customOverlay));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(map, marker, customOverlay));
}

function makeStoreMap(name, lat, lng) {
    const container = document.getElementById('storeMap'); // 지도를 담을 영역
    let options = { // 지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(lat+0.001, lng), // 지도의 중심좌표
        level: 4 // 지도의 레벨(확대, 축소 정도)
    };
    let map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
    var imageSrc = 'https://user-images.githubusercontent.com/90389517/212600851-24700888-5f81-40fe-81e7-db62bbf5a7bc.png', // 마커이미지의 주소
        imageSize = new kakao.maps.Size(60, 60), // 마커이미지의 크기
        imageOption = {offset: new kakao.maps.Point(32, 69)}; // 마커이미지의 옵션. 마커의 좌표와 일치시킬 이미지 안에서의 좌표 설정.
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(lat, lng),
        image : markerImage
    });

    var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: new kakao.maps.LatLng(lat, lng),
        content: makeContent(name),
        yAnchor: 1
    });
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

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

    // 클릭한 위도, 경도 정보를 가져옵니다
    var latlng = mouseEvent.latLng;

    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);

    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
    message += '경도는 ' + latlng.getLng() + ' 입니다';

    var resultDiv = document.getElementById('clickLatlng');
    resultDiv.innerHTML = message;

});
