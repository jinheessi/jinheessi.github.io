import {useState, useEffect} from 'react';
import Frame from '../components/Frame';
import MapComponent from '../components/MapComponent';

const PublicHealth = () => {
    const change=(index) =>{

        var cnt = [];
        cnt[0] = ['전체'];
        cnt[1] = ['전체','강남구','강동구','강북구','강서구','관악구','광진구','구로구','금천구','노원구','도봉구','동대문구','동작구','마포구','서대문구','서초구','성동구','성북구','송파구','양천구','영등포구','용산구','은평구','종로구','중구','중랑구'];
        cnt[2] = ['전체','강서구','금정구','남구','동구','동래구','부산진구','북구','사상구','사하구','서구','수영구','연제구','영도구','중구','해운대구','기장군'];
        cnt[3] = ['전체','남구','달서구','동구','북구','서구','수성구','중구','달성군'];
        cnt[4] = ['전체','계양구','남구','남동구','동구','부평구','서구','연수구','중구','강화군','옹진군'];
        cnt[5] = ['전체','광산구','남구','동구','북구','서구'];
        cnt[6] = ['전체','대덕구','동구','서구','유성구','중구'];
        cnt[7] = ['전체','남구','동구','북구','중구','울주군'];
        cnt[8] = ['전체','고양시','과천시','광명시','구리시','군포시','남양주시','동두천시','부천시','성남시','수원시','시흥시','안산시','안양시','오산시','의왕시','의정부시','평택시','하남시','가평군','광주시','김포시','안성시','양주군','양평군','여주군','연천군','용인시','이천군','파주시','포천시','화성시'];
        cnt[9] = ['전체','강릉시','동해시','삼척시','속초시','원주시','춘천시','태백시','고성군','양구군','양양군','영월군','인제군','정선군','철원군','평창군','홍천군','화천군','황성군'];
        cnt[10] = ['전체','제천시','청주시','충주시','괴산군','단양군','보은군','영동군','옥천군','음성군','진천군','청원군'];
        cnt[11] = ['전체','공주시','보령시','서산시','아산시','천안시','금산군','논산군','당진군','부여군','서천군','연기군','예산군','청양군','태안군','홍성군'];
        cnt[12] = ['전체','군산시','김제시','남원시','익산시','전주시','정읍시','고창군','무주군','부안군','순창군','완주군','임실군','장수군','진안군'];
        cnt[13] = ['전체','광양시','나주시','목포시','순천시','여수시','여천시','강진군','고흥군','곡성군','구례군','담양군','무안군','보성군','신안군','여천군','영광군','영암군','완도군','장성군','장흥군','진도군','함평군','해남군','화순군'];
        cnt[14] = ['전체','경산시','경주시','구미시','김천시','문겅시','상주시','안동시','영주시','영천시','포항시','고령군','군위군','봉화군','성주군','영덕군','영양군','예천군','울릉군','울진군','의성군','청도군','청송군','칠곡군'];
        cnt[15] = ['전체','거제시','김해시','마산시','밀양시','사천시','울산시','진주시','진해시','창원시','통영시','거창군','고성군','남해군','산청군','양산시','의령군','창녕군','하동군','함안군','함양군','합천군'];
        cnt[16] = ['전체','서귀포시','제주시','남제주군','북제주군'];
            
        var sel = document.querySelector("#county3");
        /* 옵션메뉴삭제 */
        for (var i=sel.length-1; i>=0; i--){
            sel.options[i] = null
            }
        /* 옵션박스추가 */
        for (i=0 ; i < cnt[index].length;i++){                     
            sel.options[i] = new Option(cnt[index][i], cnt[index][i]);
        }
    };
    return (
            <div>
                <Frame>
                    <div className="row">
                        <div className="col-md-5 col-12">
                            <p style={{ fontSize: '20pt', color: 'black' }}><b>내 주변 보건소</b></p>
                        </div>
                        <div className="col-md-7 col-12">
                            <form id="form3" name="form3" method="post" onSubmit="geocoder.addressSearch(this.value)">
                            <div className="row">
                                <div className="col-4 d-flex align-items-end">
                                <select id="city3" name="city3"  onChange = {(e) => change(e.target.selectedIndex)} className="form-select d-flex align-items-end" style={{ color: 'gray' }}>
                                    <option value="전체">시/도</option>
                                    <option value="서울특별시">서울특별시</option>
                                    <option value="부산광역시">부산광역시</option>
                                    <option value="대구광역시">대구광역시</option>
                                    {/* 나머지 option 태그 추가 */}
                                </select>
                                </div>
                                <div className="col-4 d-flex align-items-end">
                                <select id="county3" name="county3"  className="form-select d-flex align-items-end" style={{ color: 'gray' }}>
                                    <option value="">구/군</option>
                                    {/* county 옵션 태그 업데이트 */}
                                </select>
                                </div>
                                <div className="col-4 d-flex align-items-end">
                                <button type="submit" className="btn btn-outline-dark border-0" style={{ color: 'white', backgroundColor: '#1977cc', fontSize: '12pt' }}>검색</button>
                                </div>
                            </div>
                            </form>
                        </div>
                        <hr />
                    </div>
                    <br />
                    <div className="row">
                        <div className="col-md-12 col-md-6 mb-2">
                            <div className="row card"  style={{ border: 'none' }}>
                            <div className="d-flex justify-content-center">
                                <div className="col-12 col-md-12">
                                    <div className="row">
                                        <div className="map_wrap">
                                            <MapComponent/>
                                            <div className="hAddr">
                                                <span className="title">내 위치</span>
                                                <span id="centerAddr"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">

                                    </div>

                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                    <br />
                    <div className="row d-flex justify-content-center">
                        <div className="col-md-12 col-12">
                            <div id="menu_wrap_3">
                                <div className="option">
                                </div>

                                <div className="card"  style={{ border: 'none' }}> 
                                    <p className="card-header" style={{ fontSize: '20pt', color: '#454545' }}><b>병원 리스트</b></p>
                                    <div className="table-responsive text-nowrap">
                                    <table className="table">
                                        <thead>
                                        <tr>
                                            <th style={{ fontSize: '14pt', color: '#454545' }}>병원</th>
                                            <th style={{ fontSize: '14pt', color: '#454545' }}>주소</th>
                                            <th style={{ fontSize: '14pt', color: '#454545' }}>전화번호</th>
                                            <th style={{ fontSize: '14pt', color: '#454545' }}>거리</th>
                                            <th style={{ fontSize: '14pt', color: '#454545' }}>진료시간</th>
                                            <th style={{ fontSize: '14pt', color: '#454545' }}>예약</th>
                                        </tr>
                                        </thead>
                                        <tbody id="hospital_table_3" className="table-border-bottom-0">

                                        </tbody>
                                    </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>  
                </Frame>
            </div>

    );
};

export default PublicHealth;