import {useState, useEffect} from 'react';
import Frame from '../components/Frame';
import axios from 'axios';

const WriteBabyCard = () => {
    
    const [session, setSession] = useState(null);
    
    useEffect(() => {
        axios.get('/kids/writebabycard')
        .then((response) => response.json())
        .then((data) => {
            setSession(data);
        }).catch((error) => {
            console.log(error);
        });
    }, []);
    
    return (
    <div>
        <Frame>
            <div className="row">
	              <div className="col-md-5 col-12">
	              	<p style={{fontSize:'22pt', color:'black'}}><b>아기 수첩</b></p>
          	      </div>
          	      <hr />
            </div>
            <div className="row row-cols-2 row-cols-md-3 g-4 mb-5 d-flex justify-content-center">
                <div className="col-md-8">
                    <div className="card mb-4" style={{border: 'none'}}>
                        <div className="card-body" >
                            <form action="/kids/writebabycard" method="post" enctype="multipart/form-data">
                                <div className="mb-3">
                                    <label className="form-label" for="pname" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>보호자</label>
                                    <input type="text" className="form-control" id="pname" name="pname" readonly>{session.username}</input>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" for="bname" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>아기이름</label>
                                    <input type="text" className="form-control" id="bname" name="bname"></input>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" for="birth" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>생년월일</label>
                                    <input type="text" id="birth" name="birth" className="form-control"></input>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" for="gender" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>성별</label>
                                    <input type="text" id="gender" name="gender" className="form-control phone-mask"></input>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" for="height" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>키</label>
                                    <input type="text" id="height" name="height" className="form-control phone-mask"></input>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" for="weight" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>체중</label>
                                    <input type="text" id="weight" name="weight" className="form-control phone-mask"></input>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" for="disease" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>기저질환</label>
                                    <input type="text" id="disease" name="disease" className="form-control"></input>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" for="baby_info" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>기타정보</label>
                                    <input type="text" id="baby_info" name="baby_info" className="form-control phone-mask"></input>
                                </div>
                                <div className="mb-3">
                                    <label className="form-label" for="photo" style={{color:'#454545', fontWeight: 'bold', fontSize: '12pt'}}>사진</label>
                                    <input type="file" id="photo" name="photo" className="form-control"></input>
                                </div>
                                <button type="submit" className="btn btn-primary">등록</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </Frame>
    </div>
        
    );
};
export default WriteBabyCard;