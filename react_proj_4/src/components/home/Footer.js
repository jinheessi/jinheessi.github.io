import {useState, useEffect} from 'react';
import axios from 'axios';

const Footer = () => {
    const [session, setSession] = useState(null);

    useEffect(() => {
        axios.get('/kids/home')
        .then((response) => response.json())
        .then((data) => setSession(data))
        .catch((error) => console.log(error))
    }, []);

    return (
        <div>
            <footer id="footer">
                <div className="footer-top">
                    <div className="container">
                        <div className="row">
        
                            <div className="col-12 col-md-4 footer-contact">
                                <h1 style={{color:'black', fontFamily:'GODOM'}}><b>응애응애</b></h1><br />
                                <p style={{fontFamily:'GODOM', color:'#454545', fontSize:'11pt'}}><b>
                                서울특별시 강서구 <br />
                                화곡로 179<br />
                                대한상공회의소 서울기술교육센터 <br /><br />
                                02)311-1000<br />
                                </b>
                                </p>
                            </div>
        
                            <div className="col-12 col-md-4 footer-links">
                                <p style={{fontSize:'18pt', color:'black'}}><b>바로가기</b></p>
                                {/*session.email === null &&
                                    <ul>
                                        <li><a href="/kids/home" style={{color:'#454545', fontSize:'12pt'}}><b>&emsp;  홈</b></a></li>
                                        <li><a href="/kids/pediatric" style={{color:'#454545', fontSize:'12pt'}}><b>&emsp;  소아과</b></a></li>
                                        <li><a href="/kids/emergency" style={{color:'#454545', fontSize:'12pt'}}><b>&emsp;  응급실</b></a></li>
                                        <li><a href="/master/login" style={{color:'#454545', fontSize:'12pt'}}><b>&emsp;  로그인</b></a></li>
                                        <li><a href="/master/signup" style={{color:'#454545', fontSize:'12pt'}}><b>&emsp;  회원 가입</b></a></li>
                                    </ul>
    */}
                            </div>
                            <div className="col-12 col-md-4 footer-links">
                                <p style={{fontSize:'18pt', color:'black'}}><b>제공 서비스</b></p>
                                <ul>
                                <li><b><a href="/kids/myreservation" style={{color:'#454545', fontSize:'12pt'}}>&emsp;  예약 관리</a></b></li>
                                <li><b><a href="https://apis.map.kakao.com/" target="_blank" style={{color:'#454545', fontSize:'12pt'}} rel="noopener noreferrer">&emsp;  카카오 맵 API</a></b></li>
                                <li><b><a href="https://www.data.go.kr/index.do" target="_blank" style={{color:'#454545', fontSize:'12pt'}} rel="noopener noreferrer">&emsp;  공공 데이터 API</a></b></li>
                                <li><b><a href="/kids/healthInfo" style={{color:'#454545', fontSize:'12pt'}}>&emsp;  건강 정보</a></b></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
	        </footer>
	        <a href="#" className="back-to-top d-flex align-items-center justify-content-center"><i className="bi bi-arrow-up-short"></i></a>
      </div>
	  
    );
};

export default Footer;