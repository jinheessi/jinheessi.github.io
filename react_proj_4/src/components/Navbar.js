import styled from 'styled-components';
import React, {useState, useEffect} from 'react';
import axios from 'axios';

const SpanColor = styled.span`
    color: #454545;
    font-size:10pt;
    font-weight: bold;
`;

const SpanColor2 = styled.span`
    font-size:12pt; 
    font-weight:bold; 
    color:black;
`;

const SpanColor3 = styled.span`
    font-size:10pt; 
    font-weight:bold; 
    color:#1977CC; 
    text-align:end;
`;

const ImgSize = styled.img`
    width:100%;
    height:100%;
`;


const Navbar = () => {
    const Session = () => {
        const [session, setSession] = useState(null);
        useEffect(() => {
            axios.get('/api/user/session').then(response =>{
                setSession(response.data);
            }).catch(error => {
                console.log(error);
            });
        }, []);
        return session;
    };

    const Member = () => {
        const [member, setMember] = useState(null);
        useEffect(() => {
            axios.get('/api/user/member').then(response=>{
                setMember(response.data);
            }).catch(error => {
                console.log(error);
            });
        }, []);
    };


    const session = Session();
    return (
        <nav
            className="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
            id="layout-navbar"
          >
          	<div className="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0  d-xl-none ">
		        <a className="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
		          <i className="bx bx-menu bx-sm"></i>
		        </a>
		    </div>
		    
            <div className="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
		         <div className="navbar-nav align-items-center">
		          <div classNameName="nav-item d-flex align-items-center">
		            <a href="javascript:searchPlaces()"><i classNameName="bx bx-search fs-4 lh-0"></i></a>
		            <input type="text" className="form-control w-100 border-0 shadow-0" placeholder="병원을 검색하세요" aria-label="Recipient's username" aria-describedby="button-addon2" value="" id="keyword"></input>
		          </div>
		        </div>

              <ul classNameName="navbar-nav d-flex justify-content-start align-items-start ms-auto">

	          <li className="nav-item navbar-dropdown dropdown-user dropdown">
	            <a className="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
	              <div className="avatar avatar-online  d-flex justify-content-center align-items-center">
	                {/*session.role === 'USER' && (<ImgSize src="/profile/member.stored_filename"></ImgSize>)*/}
	              </div>
	            </a>
	            <ul className="dropdown-menu pt-0 pb-1 ps-1 pe-1">
	              <li>
	                <a className="dropdown-item" href="#">
	                   <div className="d-flex">
	                    <div className="flex-shrink-0 mt-0 me-3">
	                      <div className="avatar avatar-online d-flex justify-content-center align-items-center">
	                      	<ImgSize src="/profile/member.stored_filename"></ImgSize>
	                      </div>
	                    </div>
	                    <div className="flex-grow-1">
	                      <SpanColor2>{/*session.username*/}</SpanColor2>
	                      {/*session.role === 'USER' && (<SpanColor3>(사용자)</SpanColor3>)*/}
	                      <SpanColor className="fw-semibold d-block">{/*session.email*/}</SpanColor>
	                    </div>
                        </div>
	                </a>
	              </li>
	              <li>
	                <a href="/kids/modifyInfo" className="dropdown-item d-flex justify-content-start">
	                  <i className="bx bx-user me-2"></i>
	                  <SpanColor  className="align-middle"><b>내 정보 수정</b></SpanColor>
	                </a>
	              </li>
	              <li>
	                <a className="dropdown-item d-flex justify-content-start" href="/kids/logout">
	                  <i className="bx bx-power-off me-2"></i>
	                  <SpanColor className="align-middle" ><b>로그아웃</b></SpanColor>
	                </a>
	              </li>
	            </ul>
	          </li>
              </ul>
            </div>
          </nav>

    );
};

export default Navbar;