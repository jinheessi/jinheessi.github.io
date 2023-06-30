import {useState, useEffect} from 'react';
import Frame from '../components/Frame';
import axios from 'axios';

const Receipt = () => {
    const [session, setSession] = useState(null);
    const [hpid, setHpid] = useState(null);
    const [dutyName, setDutyName] = useState(null);
    const currentDate = new Date();
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    const formattedDate = currentDate.toLocaleDateString('ko-KR', options);
    
    useEffect(() => {
        axios.get('/kids/receipt')
        .then((response) => response.json())
        .then((data) => {
            setSession(data.session);
            setHpid(data.hpid);
            setDutyName(data.dutyName);
        }).catch((error) => console.log(error));
    }, []);
    return (
        <div>
            <Frame>
                <div className="row">
                    <div className="col-12">
                        <input type="hidden" value={dutyName} id="dutyName" />
                        <p style={{fontSize:'23pt', color:'black', fontWeight:'bold'}}>{dutyName} ({hpid}) 접수</p>
                    </div>
                    <hr/>
                </div>
                <br/><br/><br/>
                <div className="row d-flex justify-content-center">
                    <div className="col-md-5">
                        <div className="card mb-4" style={{border : 'none'}}>
                            <p style={{fontSize:'18pt'}}><b>접수 카드</b></p>
                            <form action="/kids/receipt" method="POST">
                                <div className="card-body">
                                    <div className="row mb-3">
                                        <label className="col-md-2 col-form-label" for="regdate"  style={{color:'#454545', fontSize:'11pt'}}><b>신청일</b></label>
                                        <div className="col-md-10">
                                        <input type="text" className="form-control" id="regdate" name="regdate" style={{color:'#454545', fontSize:'11pt', fontWeight: 'bold'}} readonly value={formattedDate} />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <label className="col-sm-2 col-form-label" for="rname" style={{color:'#454545', fontSize:'11pt'}}><b>신청자</b></label>
                                        <div className="col-md-10">
                                            <input type="text" className="form-control" id="rname" name="rname" style={{color:'#454545', fontSize:'11pt', fontWeight: 'bold'}} /*value=*username}*/ readonly />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <label className="col-sm-2 col-form-label" for="pname" style={{color:'#454545', fontSize:'11pt'}}><b>환자 이름</b></label>
                                        <div className="col-md-10">
                                            <input type="text" className="form-control" id="pname" name="pname" style={{color:'#454545', fontSize:'11pt', fontWeight: 'bold'}} />
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <label className="col-sm-2 col-form-label" for="birth" style={{color:'#454545', fontSize:'11pt'}}><b>생년월일</b></label>
                                        <div className="col-sm-10">
                                        <input type="text" className="form-control" id="birth" name="birth" style={{color:'#454545', fontSize:'11pt', fontWeight: 'bold'}}/>
                                        </div>
                                    </div>
                                    <div className="row mb-3">
                                        <label className="col-sm-2 col-form-label" for="gender" style={{color:'#454545', fontSize:'11pt'}}><b>성별</b></label>
                                        <div className="col-sm-10">
                                        <input type="text" className="form-control" id="gender" name="gender" style={{color:'#454545', fontSize:'11pt', fontWeight: 'bold'}}/>
                                        </div>
                                    </div>

                                    
                                    <div className="row mb-3">
                                        <label className="col-sm-2 col-form-label" for="symptom" style={{color:'#454545', fontSize:'11pt'}}><b>증상</b></label>
                                        <div className="col-sm-10">
                                        <textarea id="symptom" name="symptom" className="form-control" style={{color:'#454545', fontSize:'11pt', fontWeight: 'bold'}}></textarea>
                                        </div>
                                    </div>
                                    
                                    <div className="row mb-3">
                                        <label className="col-sm-2 col-form-label" for="weight" style={{color:'#454545', fontSize:'11pt'}}><b>접수 상태</b></label>
                                        <div className="col-sm-10">
                                            <input type="text" className="form-control" id="state" name="state" value="대기" style={{color:'#454545', fontSize:'11pt', fontWeight: 'bold'}} readonly/>
                                        </div>
                                    </div>
                                    
                                    <input type="hidden" className="form-control" id="dutyName" name="dutyName" value={dutyName}/>
                                    <input type="hidden" className="form-control" id="hpid" name="hpid" value={hpid}/>
                                    <div className="row d-flex justify-content-center align-items">
                                        <div className="col-md-10">
                                            <input id="btn" name="btn" type="submit" className="btn btn-outline-primary" value="접수하기"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </Frame>
        </div>
            
            
              
            
            	
            		
            		
				      
				        
        
        
    );
};

export default Receipt;





