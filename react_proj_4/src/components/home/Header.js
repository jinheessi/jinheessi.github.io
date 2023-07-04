import {useState, useEffect} from 'react';
import axios from 'axios';

const Header = () => {
    const [session, setSession] = useState([]);
    useEffect(() => {
        axios.get('/api/session')
        .then((response) => setSession(response.data))
        .catch((error) => console.log(error));
    },[]);

    return (
	  <header id="header" className="fixed-top">
        <div className="container d-flex align-items-center">&emsp;
	      <p className="logo me-auto" style={{color:'black', fontSize:'31pt', fontFamily:'GODOM'}}>응애응애</p>
	        
	        {session.email === null && session.supervisor === null &&
                <nav id="navbar" className="navbar order-last order-lg-0">
                    <ul>
                    <li><a className="nav-link scrollto" data-bs-toggle="modal" data-bs-target="#exampleModal"style= {{fontSize:'18pt', color:'black'}}><b>소아과</b></a></li>
                    <li><a className="nav-link scrollto" data-bs-toggle="modal" data-bs-target="#exampleModal"style= {{fontSize:'18pt', color:'black'}}><b>응급실</b></a></li>
                    <li><a className="nav-link scrollto" data-bs-toggle="modal" data-bs-target="#exampleModal"style= {{fontSize:'18pt', color:'black'}}><b>예약</b></a></li>
                    <li><a className="nav-link scrollto" data-bs-toggle="modal" data-bs-target="#exampleModal"style= {{fontSize:'18pt', color:'black'}}><b>건강 정보</b></a></li>
                    
                    </ul>
                    <i className="bi bi-list mobile-nav-toggle"></i>
                    <br />
                    <br /><br />
                </nav>
    }
            {session.username !== null && 
                <nav id="navbar" className="navbar order-last order-lg-0">
                    <ul>
                        <li><a className="nav-link scrollto" href="/kids/pediatric"style= {{fontSize:'18pt', color:'black'}}><b>소아과</b></a></li>
                        <li><a className="nav-link scrollto" href="/kids/emergency" style= {{fontSize:'18pt', color:'black'}}><b>응급실</b></a></li>
                        <li><a className="nav-link scrollto" href="/kids/reservation" style= {{fontSize:'18pt', color:'black'}}><b>예약</b></a></li>
                        <li><a className="nav-link scrollto" href="/kids/healthInfo" style= {{fontSize:'18pt', color:'black'}}><b>건강 정보</b></a></li>
                        <li className="dropdown"><a href="" style= {{fontSize:'18pt', color:'black'}}><span><b>마이 페이지</b></span> <i className="bi bi-chevron-down"></i></a>
                        <ul>
                            <li><a href="/kids/modifyInfo" style= {{fontSize:'12pt', color:'black'}}><b>내 정보 보기/수정</b></a></li>
                            <li><a href="/kids/myreservation" style= {{fontSize:'12pt', color:'black'}}><b>예약 관리</b></a></li>
                            <li><a href="/kids/reviewView" style= {{fontSize:'12pt', color:'black'}}><b>리뷰</b></a></li>
                            <li><a href="/kids/logout" style= {{fontSize:'12pt', color:'black'}}><b>로그아웃</b></a></li>
                        </ul>
                    </li>
                    </ul>
                    <i className="bi bi-list mobile-nav-toggle"></i>
                    <br />
                    <br /><br />
                </nav>
}
        </div>
      </header>
	    
         
	  
    );
};

export default Header;