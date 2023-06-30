import {useState, useEffect} from 'react';
import axios from 'axios';
import Frame from '../components/Frame';


const Babycard = () => {
    const [babies, setBabies] = useState([]);
    useEffect(()=>{
        axios.get('/kids/babycard')
        .then((response) => response.json())
        .then((data) => {
            setBabies(data);
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
                            
                <hr></hr>
                </div>
                <div className="row row-cols-2 row-cols-md-3 g-4 mb-5">
                    {
                        babies.length === 0 ?(
                            <div className="col-md-12" style={{display:'block', textAlign:'center', fontSize:'14pt', fontWeight:'bold'}}>
                                <br/>
                                <p>아이 정보가 없습니다.</p>
                                <p>새롭게 정보를 등록하시겠습니까?</p><br/>
                                <a href="/kids/writebabycard" className="btn btn-outline-primary">등록</a>
                            </div>
                        )
                    :
                        (
                            babies.map((baby) => (
                            <div className="row row-cols-2 row-cols-md-3 g-4 mb-5">
                                <div className="col-md-4 col-12">
                                    <div className="card h-100">
                                        <div className="card-body">
                                            <h4 className="card-title" style={{color:'black', fontWeight:'bold'}} ><b>{baby.bname}</b></h4><hr></hr>
                                            <img  className="card-img-top" src="/profile/{baby.stored_filename}"  style={{height:'50%', fontWeight:'bold'}}></img>
                                            <hr></hr>
                                            <p className="card-text" style={{color:'#454545', fontSize:'14pt', fontWeight:'bold'}}><b>생년월일: {baby.birth}</b></p>
                                            <p className="card-text" style={{color:'#454545', fontSize:'14pt', fontWeight:'bold'}}><b>키: {baby.height}|</b></p>
                                            <p className="card-text" style={{color:'#454545', fontSize:'14pt', fontWeight:'bold'}}><b>몸무게: {baby.weight}</b></p>
                                            <p className="card-text" style={{color:'#454545', fontSize:'14pt', fontWeight:'bold'}}><b>기저질환: {baby.disease}</b></p>				      
                                            <p className="card-text" style={{color:'#454545', fontSize:'14pt', fontWeight:'bold'}}><b>기타정보: {baby.baby_info}</b></p>
                                            <br></br>
                                            <div style={{display:'block',textAlign:'end'}}>						        	
                                                <a href="/kids/modifybabycard?bname={baby.bname}" className="btn btn-outline-success">수정</a>
                                                <a href="/kids/deletebabycard?bname={baby.bname}" className="btn btn-outline-danger">삭제</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-md-12 d-flex justify-content-end">
                                            <a href="/kids/writebabycard" className="btn btn-outline-primary">등록</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))
                            
                        )
                    
                    
                    }
                </div>            
        </Frame>
    </div>
    );  
};

export default Babycard;