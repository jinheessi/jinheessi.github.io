import {useState, useEffect} from 'react';
import Frame from '../components/Frame';
import axios from 'axios';


const ModifyInfo = () => {
    const [session, setSession] = useState(null);
    const [member, setMember] = useState(null);
    useEffect(() => {
        axios.get('/api/session')
        .then((response) => response.json())
        .then((data) => setSession(data))
        .catch((error) => console.log(error))
    },[]);
    
    useEffect(() => {
        axios.get('/api/member')
        .then((response) => response.json())
        .then((data) => setMember(data))
        .catch((error) => console.log(error))
    },[]);

    return (
            <Frame>
                <div class="row">
                    <div class="col-md-12">
                        <ul class="nav nav-pills flex-column flex-md-row mb-3">
                        <li class="nav-item"><a class="nav-link active" href="javascript:void(0);" style={{fontWeight:'bold'}}><i class="bx bx-user-circle me-1"></i>계정</a></li>
                        <li class="nav-item"><a class="nav-link" href="pages-account-settings-notifications.html" style={{fontWeight:'bold'}}><i class="bx bx-bell me-1"></i> 공지사항</a></li>
                        </ul>
                        <form action="/kids/modifyInfo" method="POST" enctype="multipart/form-data">
                        <div class="card mb-4">
                        <h5 class="card-header" style={{color:'#454545'}}><b>프로필</b></h5>
                            <div class="card-body">
                            <div class="d-flex align-items-start align-items-sm-center gap-4">
                            
                            <img src="|/profile/${member.stored_filename}|" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar" />
                            <div class="button-wrapper">
                            
                                <label for="fileUpload" class="btn btn-primary me-2 mb-4" tabindex="0">
                                <span class="d-none d-sm-block">프로필 사진 업로드</span>
                                <i class="bx bx-upload d-block d-sm-none"></i>
                                <input type="file" id="fileUpload" name="fileUpload" class="account-file-input" hidden="" accept="image/png, image/jpeg" />
                                </label>
                                <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
                                <i class="bx bx-reset d-block d-sm-none"></i>
                                <span class="d-none d-sm-block">취소</span>
                                </button>
                                <p class="text-muted mb-0" style={{color:'black'}}>이미지 파일 800K 이하 JPG, GIF, PNG</p>
                            </div>
                            </div>
                        </div>
                        
                        </div>
                        
                        <hr class="my-0"/>
                        <div class="card-body">
                            <div class="row">
                                <div class="mb-3 col-md-4">
                                <label for="email" class="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>아이디</b></label>
                                <input class="form-control" type="text" /*value={session.email}*/ readonly style={{color:'gray', fontWeight:'bold'}}/>
                                </div>
                                <div class="mb-3 col-md-4">
                                <label for="username" class="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>이름</b></label>
                                <input class="form-control" type="text" name="username" id="username" /*value={session.username}*/ readonly style={{color:'gray', fontWeight:'bold'}}/>
                                </div>
                                <div class="mb-3 col-md-4">
                                <label for="email" class="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>이메일</b></label>
                                <input class="form-control" type="text" id="email" name="email" /*value={session.email}*/ placeholder="" style={{color:'#454545', fontWeight:'bold'}}/>
                                </div>
                            </div>
                            
                            <div class="row"> 
                                <div class="mb-3 col-md-4">
                                <label for="nickname" class="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>별명</b></label>
                                <input type="text" class="form-control" id="nickname" name="nickname" /*value={member.nickname}*/ />
                                </div>
                                <div class="mb-3 col-md-4">
                                <label class="form-label" for="telno" style={{fontSize:'12pt',color:'#454545'}}><b>전화번호</b></label>
                                <div class="input-group input-group-merge">
                                    <span class="input-group-text"></span>
                                    <input type="text" id="telno" name="telno" class="form-control" /*value={member.telno}*/ />
                                </div>
                                </div>
                                <div class="mb-3 col-md-4">
                                    <label for="addrSearch" class="form-label" style={{color:'black', fontSize: '10pt'}}>주소 검색</label>
                                    <div class="input-group mb-3">
                                    <input type="text" id="addrSearch" class="form-control"/> 
                                    <button class="btn btn-primary d-grid" type="button" onclick="kakaoAddressAPI()" style={{textAlign:'center', fontSize:'10pt'}}>검색</button>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-md-4">
                                <label for="zipcode" class="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>우편번호</b></label>
                                <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="" /*value={member.zipcode}*/ />
                                </div>
                                <div class="mb-3 col-md-4">
                                <label for="address" class="form-label" style={{fontSize:'12pt',color:'#454545'}}><b>주소</b></label>
                                <input type="text" class="form-control" id="address" name="address" placeholder="" /*value={member.address}*/ />
                                </div>
                                <div class="mt-3 col-md-4 d-flex justify-content-center align-items-center">
                                <button type="submit" class="btn btn-primary me-2" style={{fontSize:'12pt'}}>저장</button>
                                    <a href="/kids/pediatric" class="btn btn-outline-secondary" style={{fontSize:'12pt'}}>취소</a>
                                </div>
                            </div>
                            <div class="row ">
                                <div class="col-md-12 mt-2 ">
                                    
                                </div>
                            </div>
                        </div> 
                        </form>
                    </div>
                </div>
            </Frame>
    );
};

export default ModifyInfo;