import {useState, useEffect} from 'react';
import Frame from '../components/Frame';
import axios from 'axios';

import '../static/css/modifyInfo.css';

const ModifyInfo = () => {
    const [session, setSession] = useState([]);
    const [member, setMember] = useState([]);
    useEffect(() => {
        axios.get('/api/session')
        .then((response) => setSession(response.data))
        .catch((error) => console.log(error))
    },[]);
    
    useEffect(() => {
        axios.get('/api/member')
        .then((response) => setMember(response.data))
        .catch((error) => console.log(error))
    },[]);

    return (
            <Frame>
                <div className="row">
                    <div className="col-md-12">
                        <ul className="nav nav-pills flex-column flex-md-row mb-3">
                            <li className="nav-item"><a className="nav-link active" href="javascript:void(0);" style={{fontWeight:'bold'}}><i className="bx bx-user-circle me-1"></i>계정</a></li>
                            <li className="nav-item"><a className="nav-link" href="pages-account-settings-notifications.html" style={{fontWeight:'bold'}}><i className="bx bx-bell me-1"></i> 공지사항</a></li>
                        </ul>
                            <div className="card mb-4">
                                <h5 className="card-header" style={{color:'#454545'}}><b>프로필</b></h5>
                                <div className="card-body">
                                    <div className="d-flex align-items-start align-items-sm-center gap-4">
                            
                                        <img src="|/profile/${member.stored_filename}|" alt="user-avatar" className="d-block rounded" height="100" width="100" id="uploadedAvatar" />
                                        <div className="button-wrapper">
                                        <label htmlFor="fileUpload" className="btn btn-primary me-2 mb-4" tabIndex={0}>
                                        <span className="d-none d-sm-block">프로필 사진 업로드</span>
                                        <i className="bx bx-upload d-block d-sm-none"></i>
                                        <input type="file" id="fileUpload" name="fileUpload" className="account-file-input" hidden accept="image/png, image/jpeg" />
                                        </label>
                                            <button type="button" className="btn btn-outline-secondary account-image-reset mb-4">
                                                <i className="bx bx-reset d-block d-sm-none"></i>
                                                <span className="d-none d-sm-block">취소</span>
                                            </button>
                                            <p className="text-muted mb-0" style={{color:'black'}}>이미지 파일 800K 이하 JPG, GIF, PNG</p>
                                        </div>
                                    </div>
                                </div>
                                <hr className="my-0"/>
                                <div className="card-body">
                                    <form action="/kids/modifyInfo" method="POST" encType="multipart/form-data">
                                        <div className="row">
                                            <div className="mb-3 col-md-4">
                                                <label htmlFor="email" className="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>아이디</b></label>
                                                <input className="form-control" type="text" value={session.email} readOnly style={{color:'gray', fontWeight:'bold'}}/>
                                            </div>
                                            <div className="mb-3 col-md-4">
                                                <label htmlFor="username" className="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>이름</b></label>
                                                <input className="form-control" type="text" name="username" id="username" value={session.username} readOnly style={{color:'gray', fontWeight:'bold'}}/>
                                            </div>
                                            <div className="mb-3 col-md-4">
                                                <label htmlFor="email" className="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>이메일</b></label>
                                                <input className="form-control" type="text" id="email" name="email" value={session.email} placeholder="" style={{color:'#454545', fontWeight:'bold'}}/>
                                            </div>
                                        </div>
                                    
                                        <div className="row"> 
                                            <div className="mb-3 col-md-4">
                                            <label htmlFor="nickname" className="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>별명</b></label>
                                            <input type="text" className="form-control" id="nickname" name="nickname" value={member.nickname} />
                                            </div>
                                            <div className="mb-3 col-md-4">
                                            <label className="form-label" for="telno" style={{fontSize:'12pt',color:'#454545'}}><b>전화번호</b></label>
                                            <div className="input-group input-group-merge">
                                                <span className="input-group-text"></span>
                                                <input type="text" id="telno" name="telno" className="form-control" value={member.telno} />
                                            </div>
                                            </div>
                                            <div className="mb-3 col-md-4">
                                                <label htmlFor="addrSearch" className="form-label" style={{color:'black', fontSize: '10pt'}}>주소 검색</label>
                                                <div className="input-group mb-3">
                                                <input type="text" id="addrSearch" className="form-control"/> 
                                                <button className="btn btn-primary d-grid" type="button" style={{textAlign:'center', fontSize:'10pt'}}>검색</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="mb-3 col-md-4">
                                            <label htmlFor="zipcode" className="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>우편번호</b></label>
                                            <input type="text" className="form-control" id="zipcode" name="zipcode" placeholder="" value={member.zipcode} />
                                            </div>
                                            <div className="mb-3 col-md-4">
                                            <label htmlFor="address" className="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>주소</b></label>
                                            <input type="text" className="form-control" id="address" name="address" placeholder="" value={member.address} />
                                            </div>
                                            <div className="mt-3 col-md-4 d-flex justify-content-center align-items-center">
                                            <button type="submit" className="btn btn-primary me-2" style={{fontSize:'12pt'}}>저장</button>
                                                <a href="/kids/pediatric" className="btn btn-outline-secondary" style={{fontSize:'12pt'}}>취소</a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div> 
                    </div>
                </div>
                <div className="layout-overlay layout-menu-toggle"></div>
            </Frame>
            
    );
};

export default ModifyInfo;