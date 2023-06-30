import {useState, useEffect} from 'react';
import axios from 'axios';

const RoadView = () => {
    const [hospital, setHospital] = useState([]);    
    const Hospital = () => {
        axios.get('/kids/viewHospital')
        .then((response) => response.json())
        .then((data) => {
            setHospital(data);
        }).catch((error) => {
            console.log(error);
        });
    }

    useEffect(() => {
        Hospital();
        var lat = hospital.wgs84Lat;
        var lon = hospital.wgs84Lon;
        var mapWrapper = document.getElementById('mapWrapper'); //지도를 감싸고 있는 DIV태그
        
        
        var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
        var mapCenter = new window.kakao.maps.LatLng(lat ,lon); // 지도의 가운데 좌표
        var mapOption = {
                center: mapCenter, // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };
        
        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        var map = new window.kakao.maps.Map(mapContainer, mapOption);
        map.addOverlayMapTypeId(window.kakao.maps.MapTypeId.ROADVIEW); //지도 위에 로드뷰 도로 올리기
        
        var rvContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
        var rv = new window.kakao.maps.Roadview(rvContainer); //로드뷰 객체
        var rvClient = new window.kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
        
        toggleRoadview(mapCenter);
        
        // 마커 이미지를 생성합니다.
        var markImage = new window.kakao.maps.MarkerImage(
            'https://t1.daumcdn.net/localimg/localimages/07/2018/pc/roadview_minimap_wk_2018.png',
            new window.kakao.maps.Size(26, 46),
            {
                // 스프라이트 이미지를 사용합니다.
                // 스프라이트 이미지 전체의 크기를 지정하고
                spriteSize: new window.kakao.maps.Size(1666, 168),
                // 사용하고 싶은 영역의 좌상단 좌표를 입력합니다.
                // background-position으로 지정하는 값이며 부호는 반대입니다.
                spriteOrigin: new window.kakao.maps.Point(705, 114),
                offset: new window.kakao.maps.Point(13, 46)
            }
        );
        
        // 드래그가 가능한 마커를 생성합니다.
        var rvMarker = new window.kakao.maps.Marker({
            image : markImage,
            position: mapCenter,
            draggable: true,
            map: map
        });
        
        //마커에 dragend 이벤트를 할당합니다
        window.kakao.maps.event.addListener(rvMarker, 'dragend', function(mouseEvent) {
            var position = rvMarker.getPosition(); //현재 마커가 놓인 자리의 좌표
            toggleRoadview(position); //로드뷰를 토글합니다
        });
        
        //지도에 클릭 이벤트를 할당합니다
        window.kakao.maps.event.addListener(map, 'click', function(mouseEvent){
            
            // 현재 클릭한 부분의 좌표를 리턴 
            var position = mouseEvent.latLng; 
        
            rvMarker.setPosition(position);
            toggleRoadview(position); //로드뷰를 토글합니다
        });
        
        //로드뷰 toggle함수
        function toggleRoadview(position){
        
            //전달받은 좌표(position)에 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄웁니다
            rvClient.getNearestPanoId(position, 50, function(panoId) {
                if (panoId === null) {
                    rvContainer.style.display = 'none'; //로드뷰를 넣은 컨테이너를 숨깁니다
                    mapWrapper.style.width = '100%';
                    map.relayout();
                } else {
                    mapWrapper.style.width = '50%';
                    map.relayout(); //지도를 감싸고 있는 영역이 변경됨에 따라, 지도를 재배열합니다
                    rvContainer.style.display = 'block'; //로드뷰를 넣은 컨테이너를 보이게합니다
                    rv.setPanoId(panoId, position); //panoId를 통한 로드뷰 실행
                    rv.relayout(); //로드뷰를 감싸고 있는 영역이 변경됨에 따라, 로드뷰를 재배열합니다
                }
            });
        }
    }, []);
        
    return(
        <div className="card-body map-wrap">
            <div id="mapWrapper" style={{width:'50%',height:'300px',float:'left'}}>
                <div id="map" style={{width:'100%',height:'100%'}}></div>
            </div>
            <div id="rvWrapper" style={{width:'50%',height:'300px',float:'left'}}>
                <div id="roadview" style={{width:'100%',height:'100%'}}></div>
            </div>                            
        </div>
    );

};

export default RoadView;