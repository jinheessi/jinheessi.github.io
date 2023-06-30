import {useState, useEffect} from 'react';
import axios from 'axios';

const Topbar = () => {
    const [session, setSession] = useState([]);
    
    useEffect(() => {
        axios.get('/kids/home')
        .then((response) => response.json())
        .then((data) => setSession(data))
        .catch((error) => console.log(error));
    }, []);

    return(
        <div id="topbar" className="d-flex align-items-center fixed-top">
                {session.supervisor === null && session.email === null &&
	                <div className="container d-flex justify-content-end">
                        <br />
                        <a className="nav-link scrollto active" data-bs-toggle="modal" data-bs-target="#exampleModal"  href="/kids/login" style={{color:'#454545', fontSize:'13pt'}}><b>로그인</b></a>&emsp;
                        <a className="nav-link scrollto" href="/master/signup" style={{color:'#454545', fontSize:'13pt'}}><b>회원가입</b></a>
                        <i className="bi mobile-nav-toggle"></i>
                    </div>
                }
                {session.username !== null &&
                    <div className="container d-flex justify-content-end">
                        <p className="mt-2" style={{color:'black', fontWeight:'bold'}}>{session.username}({session.email})님 접속을 환영합니다."</p>
                    </div>
                }
                {session.supervisor !== null &&
                <div className="container d-flex justify-content-end">
                    <p className="mt-2" style={{color:'black', fontWeight:'bold'}}>{session.supervisor}({session.hospitalUserId}, {session.hospitalName})님 접속을 환영합니다."</p>
                </div>
                }
	    </div>
    );
};

export default Topbar;