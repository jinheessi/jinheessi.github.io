import React, { useEffect, useState } from "react";

const MapComponent = () => {
    useEffect(() => {
			
        var lat;
        var lon;
        var markers = [];
        var locPosition;
        var markerloc = [];
        var arr = [];
        
        var countInDiameter = [];


        const mapContainer = document.getElementById('map'); // 지도를 표시할 div
        const mapOption = {
          center: new window.kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };
        const map = new window.kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        if (navigator.geolocation) {
			    
            // GeoLocation을 이용해서 접속 위치를 얻어옵니다
            navigator.geolocation.getCurrentPosition(function(position) {
                
                var lat = position.coords.latitude; // 위도
                var lon = position.coords.longitude; // 경도
                
                locPosition = new window.kakao.maps.LatLng(lat, lon); // geolocation으로 얻어온 좌표
                   
                map.setCenter(locPosition);   
                    
              });
            
        } else { // HTML5의 GeoLocation을 사용할 수 없을때 
            
            var locPosition = new window.kakao.maps.LatLng(37.566826, 126.9786567)
            alert('현재 위치를 찾을 수 없습니다!');
        }

        // 주소-좌표 변환 객체를 생성합니다
        const geocoder = new window.kakao.maps.services.Geocoder();
    
        const marker = new window.kakao.maps.Marker(); // 클릭한 위치를 표시할 마커입니다
        const infowindow = new window.kakao.maps.InfoWindow({ zIndex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
    
        // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        
        window.kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
            searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
                if (status === window.kakao.maps.services.Status.OK) {
                    var detailAddr = !!result[0].road_address ? '<div style="color:black;">도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                    detailAddr += '<div style="color:black;">지번 주소 : ' + result[0].address.address_name + '</div>';
                    
                    var content = '<div class="bAddr">' +
                                    '<span class="title" style="color:black;">법정동 주소정보</span>' + 
                                    detailAddr + 
                                '</div>';

                    // 마커를 클릭한 위치에 표시합니다 
                    marker.setPosition(mouseEvent.latLng);
                    marker.setMap(map);

                    // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                    infowindow.setContent(content);
                    infowindow.open(map, marker);
                }   
            });
        });

        function searchAddrFromCoords(coords, callback) {
            // 좌표로 행정동 주소 정보를 요청합니다
            geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
        }
        
        function searchDetailAddrFromCoords(coords, callback) {
            // 좌표로 법정동 상세 주소 정보를 요청합니다
            geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
        }

        // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
        function displayCenterInfo(result, status) {
          if (status === window.kakao.maps.services.Status.OK) {
            const infoDiv = document.getElementById('centerAddr');
    
            for (let i = 0; i < result.length; i++) {
              // 행정동의 region_type 값은 'H' 이므로
              if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
              }
            }
          }
        }
        window.kakao.maps.event.addListener(map, 'idle', function () {
          searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        });

        // 검색 결과 목록과 마커를 표출하는 함수입니다
			function displayPlaces(data) {
			
			    var hospital_table = document.querySelector('#hospital_table'), 
			    menuEl = document.getElementById('menu_wrap'), 
			    bounds = new window.kakao.maps.LatLngBounds(), 
			    listStr = '';
			  
			    
			    // 검색 결과 목록에 추가된 항목들을 제거합니다
			
			    // 지도에 표시되고 있는 마커를 제거합니다
			    removeMarker();
			    
			    for (var i=0; i < data.length; i++ ) {
					var dLat = (Math.PI / 180) * (data[i].wgs84Lat - map.getCenter().getLat());
				    var dLon = (Math.PI / 180) * (data[i].wgs84Lon - map.getCenter().getLng());
				
				    var a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos((Math.PI/180)*(map.getCenter().getLat()))* Math.cos((Math.PI/180)*(data[i].wgs84Lat))* Math.sin(dLon/2)* Math.sin(dLon/2);
				    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				    var d = 6400 * c * 1000;
				    d = d / 1000;
				    d = d.toFixed(2);
		            arr.push([i,data[i], d]);
		            arr.sort((a,b) => a[2] - b[2]);
			    }
			    
			    for (var i=0; i < arr.length; i++){
			        // 마커를 생성하고 지도에 표시합니다
			        var placePosition = new window.kakao.maps.LatLng(arr[i][1].wgs84Lat, arr[i][1].wgs84Lon),
			            marker = addMarker(placePosition, i), 
			            itemEl = getListItem(arr[i][0], arr[i][1], arr[i][2]); // 검색 결과 항목 Element를 생성합니다
			        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
			        // LatLngBounds 객체에 좌표를 추가합니다
			        bounds.extend(placePosition);
			        // 마커와 검색결과 항목에 mouseover 했을때
			        // 해당 장소에 인포윈도우에 장소명을 표시합니다
			        // mouseout 했을 때는 인포윈도우를 닫습니다
			        (function(marker, title) {
			            window.kakao.maps.event.addListener(marker, 'mouseover', function() {
			                displayInfowindow(marker, title);
			            });
			
			            window.kakao.maps.event.addListener(marker, 'mouseout', function() {
			                infowindow.close();
			            });
			
			            itemEl.onmouseover =  function () {
			                displayInfowindow(marker, title);
			            };
			
			            itemEl.onmouseout =  function () {
			                infowindow.close();
			            };
			        })(marker, arr[i][1].dutyName);
					
			        var row = '<tr><td style="font-size:11pt; color:#454545"><i class="fab fa-angular fa-lg text-danger me-3"></i><b>'
			        + arr[i][1].dutyName + '</b></td><td style="font-size:11pt; color:#454545"><b>' + arr[i][1].dutyAddr + '<b></td><td style="font-size:11pt; color:#454545"><b>' + 
			        arr[i][1].dutyTel1 + '</b></td><td style="font-size:11pt; color:#454545"><b>' + arr[i][2] + 'km</b></td><td><ul style="list-style-type:none;"><li style="font-size:11pt; color:#454545"><b>월: ' + arr[i][1].dutyTime1s + '~' + arr[i][1].dutyTime1c + '</b></li>'+
			        '<li style="font-size:11pt; color:#454545"><b>화: ' + arr[i][1].dutyTime2s + '~' + arr[i][1].dutyTime2c + '</b></li>' + '<li style="font-size:11pt; color:#454545"><b>수: ' + arr[i][1].dutyTime3s + '~' + arr[i][1].dutyTime3c + '</b></li>' + 
			        '<li style="font-size:11pt; color:#454545"><b>목: ' + arr[i][1].dutyTime4s + '~' + arr[i][1].dutyTime4c + '</b></li>' + '<li style="font-size:11pt; color:#454545"><b>금: ' + arr[i][1].dutyTime5s + '~' + arr[i][1].dutyTime5c + '</b></li>' + 
			        '<li style="font-size:11pt; color:#454545"><b>토: ' + arr[i][1].dutyTime6s + '~' + arr[i][1].dutyTime6c + '</b></li>' + '<li style="font-size:11pt; color:#454545"><b>일: ' + arr[i][1].dutyTime7s + '~' + arr[i][1].dutyTime7c + '</b></li></ul></td>' +
			        '<td><input type="button" class="btn-primary" value="예약하기"></td></tr>';
			        hospital_table.innerHTML += row;
			    }
			    
			
			    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
			    menuEl.scrollTop = 0;
			
			    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
			    map.setBounds(bounds);
			}
			
			
			// 검색결과 항목을 Element로 반환하는 함수입니다
			function getListItem(index, hospital, distance) {
			    var el = document.createElement('li'),
			    itemStr = '<form><span class="markerbg marker_' + (index+1) + '">'+'</span>' +
			                '<div class="info" style="border:none">' +
			                '   <h6 style="color:black;"> ' + hospital.dutyName + '</h6>';
			
			    
		        itemStr += '    <span style="color:black;">주소: ' + hospital.dutyAddr + '</span>';
			     
			    itemStr += '  <span class="tel">전화번호: ' + hospital.dutyTel1  + '</span>' + '<span style=";color:black;">거리: '+ distance + 'km</span>' +
			                '</div></form>';           
			
			    el.innerHTML = itemStr;
			    el.className = 'item';
			    
			    return el;
				
			}

            
			
			// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
			function addMarker(position, idx) {
			    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
			        imageSize = new window.kakao.maps.Size(36, 37),  // 마커 이미지의 크기
			        imgOptions =  {
			            spriteSize : new window.kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
			            spriteOrigin : new window.kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			            offset: new window.kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
			        },
			        markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
			            marker = new window.kakao.maps.Marker({
			            position: position, // 마커의 위치
			            image: markerImage 
			        });
			
			    marker.setMap(map); // 지도 위에 마커를 표출합니다
			    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
				
			    markerloc.push(marker.getPosition());
			   
			    
			    return marker;
			}
			
			// 지도 위에 표시되고 있는 마커를 모두 제거합니다
			function removeMarker() {
			    for ( var i = 0; i < markers.length; i++ ) {
			        markers[i].setMap(null);
			    }   
			    markers = [];
			}
			
			
			// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
			// 인포윈도우에 장소명을 표시합니다
			function displayInfowindow(marker, title) {
			    var content = '<div style="padding:10px;">'+'<p style="font-family:GODOM; font-size:9pt; color:#454545">' + title + '</p></div>';
			
			    infowindow.setContent(content);
			    infowindow.open(map, marker);
			}
			
			 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
			function removeAllChildNods(el) {   
			    while (el.hasChildNodes()) {
			        el.removeChild (el.lastChild);
			    }
			}

			
			function viewHospital (index, keyword, road, old_addr, phone, x, y){
				document.location.href="/kids/viewHospital?index="+index+"&keyword="+keyword+"&road="+road+"&old_addr="+old_addr+"&phone="+phone+ "&x="+x +"&y="+y;
			}
			
			var drawingFlag = false; // 원이 그려지고 있는 상태를 가지고 있을 변수입니다
			var centerPosition; // 원의 중심좌표 입니다
			var drawingCircle; // 그려지고 있는 원을 표시할 원 객체입니다
			var drawingLine; // 그려지고 있는 원의 반지름을 표시할 선 객체입니다
			var drawingOverlay; // 그려지고 있는 원의 반경을 표시할 커스텀오버레이 입니다
			var drawingDot; // 그려지고 있는 원의 중심점을 표시할 커스텀오버레이 입니다

			var circles = []; // 클릭으로 그려진 원과 반경 정보를 표시하는 선과 커스텀오버레이를 가지고 있을 배열입니다

			// 지도에 클릭 이벤트를 등록합니다
			window.kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
			        
			    // 클릭 이벤트가 발생했을 때 원을 그리고 있는 상태가 아니면 중심좌표를 클릭한 지점으로 설정합니다
			    if (!drawingFlag) {    
			        
			        // 상태를 그리고있는 상태로 변경합니다
			        drawingFlag = true; 
			       
			        // 원이 그려질 중심좌표를 클릭한 위치로 설정합니다 
			        centerPosition = new window.kakao.maps.LatLng(mouseEvent.latLng.getLat(), mouseEvent.latLng.getLng());
			        // 그려지고 있는 원의 반경을 표시할 선 객체를 생성합니다
			        if (!drawingLine) {
			            drawingLine = new window.kakao.maps.Polyline({
			                strokeWeight: 3, // 선의 두께입니다
			                strokeColor: '#00a0e9', // 선의 색깔입니다
			                strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
			                strokeStyle: 'solid' // 선의 스타일입니다
			            });    
			        }
			        
			        // 그려지고 있는 원을 표시할 원 객체를 생성합니다
			        if (!drawingCircle) {                    
			            drawingCircle = new window.kakao.maps.Circle({ 
			                strokeWeight: 1, // 선의 두께입니다
			                strokeColor: '#00a0e9', // 선의 색깔입니다
			                strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
			                strokeStyle: 'solid', // 선의 스타일입니다
			                fillColor: '#00a0e9', // 채우기 색깔입니다
			                fillOpacity: 0.2 // 채우기 불투명도입니다 
			            });     
			        }
			        
			        // 그려지고 있는 원의 반경 정보를 표시할 커스텀오버레이를 생성합니다
			        if (!drawingOverlay) {
			            drawingOverlay = new window.kakao.maps.CustomOverlay({
			                xAnchor: 0,
			                yAnchor: 0,
			                zIndex: 1
			            });              
			        }
			    }
			    });

			// 지도에 마우스무브 이벤트를 등록합니다
			// 원을 그리고있는 상태에서 마우스무브 이벤트가 발생하면 그려질 원의 위치와 반경정보를 동적으로 보여주도록 합니다
			window.kakao.maps.event.addListener(map, 'mousemove', function (mouseEvent) {
			        
			    // 마우스무브 이벤트가 발생했을 때 원을 그리고있는 상태이면
			    if (drawingFlag) {

			        // 마우스 커서의 현재 위치를 얻어옵니다 
			        var mousePosition = mouseEvent.latLng; 
			        
			        // 그려지고 있는 선을 표시할 좌표 배열입니다. 클릭한 중심좌표와 마우스커서의 위치로 설정합니다
			        var linePath = [centerPosition, mousePosition];     
			        
			        // 그려지고 있는 선을 표시할 선 객체에 좌표 배열을 설정합니다
			        drawingLine.setPath(linePath);
			        
			        // 원의 반지름을 선 객체를 이용해서 얻어옵니다 
			        var length = drawingLine.getLength();
			        
			        if(length > 0) {
			            
			            // 그려지고 있는 원의 중심좌표와 반지름입니다
			            var circleOptions = { 
			                center : centerPosition, 
			            radius: length,                 
			            };
			            
			            // 그려지고 있는 원의 옵션을 설정합니다
			            drawingCircle.setOptions(circleOptions); 
			                
			            // 반경 정보를 표시할 커스텀오버레이의 내용입니다
			            var radius = Math.round(drawingCircle.getRadius()),   
			            content = '<div class="info">반경 <span class="number">' + radius + '</span>m</div>';
			            
			            // 반경 정보를 표시할 커스텀 오버레이의 좌표를 마우스커서 위치로 설정합니다
			            drawingOverlay.setPosition(mousePosition);
			            
			            // 반경 정보를 표시할 커스텀 오버레이의 표시할 내용을 설정합니다
			            drawingOverlay.setContent(content);
			            
			            // 그려지고 있는 원을 지도에 표시합니다
			            drawingCircle.setMap(map); 
			            
			            // 그려지고 있는 선을 지도에 표시합니다
			            drawingLine.setMap(map);  
			            
			            // 그려지고 있는 원의 반경정보 커스텀 오버레이를 지도에 표시합니다
			            drawingOverlay.setMap(map);
			            
			        } else { 
			            
			            drawingCircle.setMap(null);
			            drawingLine.setMap(null);    
			            drawingOverlay.setMap(null);
			            
			        }
			    }     
			});     

			// 지도에 마우스 오른쪽 클릭이벤트를 등록합니다
			// 원을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면
			// 마우스 오른쪽 클릭한 위치를 기준으로 원과 원의 반경정보를 표시하는 선과 커스텀 오버레이를 표시하고 그리기를 종료합니다
    		window.kakao.maps.event.addListener(map, 'rightclick', function (mouseEvent) {

			    if (drawingFlag) {

			        // 마우스로 오른쪽 클릭한 위치입니다 
			        var rClickPosition = mouseEvent.latLng; 

			        // 원의 반경을 표시할 선 객체를 생성합니다
			        var polyline = new window.kakao.maps.Polyline({
			            path: [centerPosition, rClickPosition], // 선을 구성하는 좌표 배열입니다. 원의 중심좌표와 클릭한 위치로 설정합니다
			            strokeWeight: 3, // 선의 두께 입니다
			            strokeColor: '#00a0e9', // 선의 색깔입니다
			            strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
			            strokeStyle: 'solid' // 선의 스타일입니다
			        });
			        
			        // 원 객체를 생성합니다
			        var circle = new window.kakao.maps.Circle({ 
			            center : centerPosition, // 원의 중심좌표입니다
			            radius: polyline.getLength(), // 원의 반지름입니다 m 단위 이며 선 객체를 이용해서 얻어옵니다
			            strokeWeight: 1, // 선의 두께입니다
			            strokeColor: '#00a0e9', // 선의 색깔입니다
			            strokeOpacity: 0.1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
			            strokeStyle: 'solid', // 선의 스타일입니다
			            fillColor: '#00a0e9', // 채우기 색깔입니다
			            fillOpacity: 0.2  // 채우기 불투명도입니다 
			        });
			        
			        var radius = Math.round(circle.getRadius()), // 원의 반경 정보를 얻어옵니다
			            content = getTimeHTML(radius,markerloc); // 커스텀 오버레이에 표시할 반경 정보입니다

			        // 반경정보를 표시할 커스텀 오버레이를 생성합니다
			        var radiusOverlay = new window.kakao.maps.CustomOverlay({
			            content: content, // 표시할 내용입니다
			            position: rClickPosition, // 표시할 위치입니다. 클릭한 위치로 설정합니다
			            xAnchor: 0,
			            yAnchor: 0,
			            zIndex: 1 
			        });  

			        // 원을 지도에 표시합니다
			        circle.setMap(map); 
			        
			        // 선을 지도에 표시합니다
			        polyline.setMap(map);
			        
			        // 반경 정보 커스텀 오버레이를 지도에 표시합니다
			        radiusOverlay.setMap(map);
			        
			        // 배열에 담을 객체입니다. 원, 선, 커스텀오버레이 객체를 가지고 있습니다
			        var radiusObj = {
			            'polyline' : polyline,
			            'circle' : circle,
			            'overlay' : radiusOverlay
			        };
			        
			        // 배열에 추가합니다
			        // 이 배열을 이용해서 "모두 지우기" 버튼을 클릭했을 때 지도에 그려진 원, 선, 커스텀오버레이들을 지웁니다
			        circles.push(radiusObj);   
			    
			        // 그리기 상태를 그리고 있지 않는 상태로 바꿉니다
			        drawingFlag = false;
			        
			        // 중심 좌표를 초기화 합니다  
			        centerPosition = null;
			        
			        // 그려지고 있는 원, 선, 커스텀오버레이를 지도에서 제거합니다
			        drawingCircle.setMap(null);
			        drawingLine.setMap(null);   
			        drawingOverlay.setMap(null);
			    }
			});
			
			// 지도에 표시되어 있는 모든 원과 반경정보를 표시하는 선, 커스텀 오버레이를 지도에서 제거합니다
			function removeCircles() {         
			    for (var i = 0; i < circles.length; i++) {
			        circles[i].circle.setMap(null);    
			        circles[i].polyline.setMap(null);
			        circles[i].overlay.setMap(null);
			    }         
			    circles = [];
			}

			// 마우스 우클릭 하여 원 그리기가 종료됐을 때 호출하여 
			// 그려진 원의 반경 정보와 반경에 대한 도보, 자전거 시간을 계산하여
			// HTML Content를 만들어 리턴하는 함수입니다
			function getTimeHTML(distance, markerloc) {
				for(var i = 0 ; i < markerloc.length ; i++){
					var dLat = (Math.PI / 180) * (markerloc[i]['Ma'] - lat);
				    var dLon = (Math.PI / 180) * (markerloc[i]['La'] - lon);
				
				    var a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos((Math.PI/180)*(lat))* Math.cos((Math.PI/180)*(markerloc[i]['Ma']))* Math.sin(dLon/2)* Math.sin(dLon/2);
				    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				    var d = 6400 * c * 1000;
				    d = d / 1000;
				    d = d.toFixed(2);
		            arr.push([i,markerloc[i], d]);
		            arr.sort((a,b) => a[2] - b[2]);
				}
				
				
			    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
			    var walkkTime = distance / 67 | 0;
			    var walkHour = '', walkMin = '';
				
			    for(i = 0 ; i < arr.length ; i++){
			    	if(arr[i][2] < distance/1000){
			    		countInDiameter.push(arr[i][2]);
			    	}
			    }
			    
			    var lenCountInDiameter = countInDiameter.length;
			    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
			    if (walkkTime > 60) {
			        walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
			    }
			    walkMin = '<span class="number">' + walkkTime % 60 + '</span>분'
				
			    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
			    var bycicleTime = distance / 227 | 0;
			    var bycicleHour = '', bycicleMin = '';

			    // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
			    if (bycicleTime > 60) {
			        bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
			    }
			    bycicleMin = '<span class="number">' + bycicleTime % 60 + '</span>분'

			    // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
			    var content = '<ul class="info">';
			    content += '    <li>';
			    content += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
			    content += '    </li>';
			    content += '    <li>';
			    content += '        <span class="label">도보</span>' + walkHour + walkMin;
			    content += '    </li>';
			    content += '    <li>';
			    content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
			    content += '    </li>';
			    content += '    <li>';
			    content += '        <span class="label">병원 수</span>' + lenCountInDiameter;
			    content += '    </li>';
			    content += '</ul>'

			    return content;
			}
			
      }, []);





  return <div id="map" style={{ width: "100%", height: "100%", position: "relative", overflow: "hidden" }}></div>;
};

export default MapComponent;
