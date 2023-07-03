import styled from 'styled-components';
import naver from '../static/images/btnG_아이콘원형.png';
import kakao from '../static/images/kakao_login_medium.png';
import google from '../static/images/google_login.png';
import {useState, useEffect} from 'react';
import '../static/vendor/boxicons/css/boxicons.css';
import '../static/assets/vendor/css/core.css';
import '../static/assets/vendor/css/theme-default.css';
import '../static/assets/css/demo.css';
import '../static/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css';
import '../static/assets/vendor/css/pages/page-auth.css';

const ImgSize = styled.img`
    width:100%;
    height:100%;
`;

const Fontstyle = styled.p`
    font-size:23pt;
    color:black;
`;
const Linkstyle = styled.a`
    text-align:center;
`;

const Login = () => {
    const [message, setMessage] = useState(null);
  useEffect(() => {
    fetch('/test/hello')
        .then(response => response.text())
        .then(message => {
            setMessage(message);
            console.log(message);
        });
  },[])
	return (
        <div>
            <div variant="bootstrap.container-xxl">
                <div className="authentication-wrapper authentication-basic container-p-y">
                    <div className="authentication-inner">
                        <div className="card">
                            <div className="card-body">
                                <div className="mb-3 d-flex justify-content-center">
                                    <Fontstyle><b>사용자 로그인</b></Fontstyle>
                                </div>
                                <br></br>
                                <div className="row d-flex justify-content-center">		       
                                    <div className="col-md-3">
                                        <Linkstyle href="/oauth2/authorization/naver"><ImgSize src={naver} /></Linkstyle>
                                    </div>
                                    <div className="col-md-5">
                                        <Linkstyle href="/oauth2/authorization/kakao"><ImgSize src={kakao} /></Linkstyle>
                                    </div>
                                </div>
                                <br></br>
                                <div className="row d-flex justify-content-center">
                                    <div className="col-md-10">
                                        <Linkstyle href="/oauth2/authorization/google">
                                            <ImgSize src={google} />
                                        </Linkstyle>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Login;
