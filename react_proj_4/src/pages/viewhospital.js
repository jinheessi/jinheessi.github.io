import {useState, useEffect} from 'react';
import axios from 'axios';
import Frame from '../components/Frame';
import RoadView from '../components/RoadView';

const ViewHospital = () => {
    const [hospital, setHospital] = useState([]);
    const [review, setReview] = useState([]);
    useEffect(() => {
        axios.get('/kids/viewHospital')
        .then((response) => response.json())
        .then((data) => {
            setHospital(data);
        }).catch((error) => {
            console.log(error);
        });
    },[]);

    useEffect(() => {
        axios.get('/kids/viewHospital')
        .then((response) => response.json())
        .then((data) => {
            setReview(data);
        }).catch((error) => {
            console.log(error);
        });
    }, []);

    /*const DutyDiv = ({hospital}) => {
        var dutyDiv = hospital.dutyDiv;
        switch(dutyDiv){
            case "A":
                return <span>종합 병원</span>;
            case "B":
                return <span>병원</span>;
            case "C":
                return <span>의원</span>;
            case "D":
                return <span>요양 병원</span>;
            case "E":
                return <span>한방 병원</span>;
            case "G":
                return <span>한의원</span>;
            case "H":
                return <span>약국</span>;
            case "I":
                return <span>기타</span>;
            case "M":
                return <span>치과 병원</span>;
            case "N":
                return <span>치과 의원</span>;
            case "R":
                return <span>보건소</span>;
            case "T":
                return <span>119 구급대</span>;
            case "U":
                return <span>경찰서</span>;
            case "V":
                return <span>지방 자치 단체</span>;
            case "W":
                return <span>기타 시설</span>;
            case "Y":
                return <span>중앙 응급 의료 센터</span>;
            case "Z":
                return <span>응급 의료 지원 센터</span>; 
            default:
                break;
        }
    }*/

    /*const Ranking = ({review}) => {
        var ranking = review.ranking;
        switch(ranking){
            case '5':
                return  <td style={{color:'gold', fontWeight:'bold', fontSize:'15pt'}}>★★★★★</td>;
            case '4':
                return  <td style={{color:'gold', fontWeight:'bold', fontSize:'15pt'}}>★★★★</td>;
            case '3':
                return  <td style={{color:'gold', fontWeight:'bold', fontSize:'15pt'}}>★★★</td>;
            case '2':
                return  <td style={{color:'gold', fontWeight:'bold', fontSize:'15pt'}}>★★</td>;
            case '1':
                return  <td style={{color:'gold', fontWeight:'bold', fontSize:'15pt'}}>★</td>;
            default :
                break;
        }
    }*/
    
    return (
        <div>
            <Frame>
                    <div className="row">
                        <div className="col-12">
                            <div className="card">
                                <RoadView />
                                <div className="card-body">
                                    <p className="card-title" style={{fontSize:'22pt', fontWeight:'bold', color:'black'}}>{hospital.dutyName}</p>
                                    <p className="card-text" style={{fontSize:'22pt', fontWeight:'bold', color:'#454545'}}>{hospital.dutyAddr}</p>
                                    <p className="card-text" style={{fontSize:'22pt', fontWeight:'bold', color:'#454545'}}><b>{hospital.dutyTel1}</b></p>
                                    <p className="card-text" style={{fontSize:'22pt', fontWeight:'bold', color:'#454545'}}>
                                        {/*<DutyDiv/>*/}
                                    </p>
                                    
                                </div>
                                <div className="card-body d-flex justify-content-center">
                                    <a href="/kids/receipt?hpid={hospital.hpid}&dutyName={hospital.dutyName}" className="btn btn-outline-primary">접수하기</a>
                                    <a href='/kids/reservation?hpid={hospital.hpid}&dutyName={hospital.dutyName}&dutyTime1s={hospital.dutyTime1s}&dutyTime1c={hospital.dutyTime1c}
                                    &dutyTime2s={hospital.dutyTime2s}&dutyTime2c={hospital.dutyTime2c}&dutyTime3s={hospital.dutyTime3s}&dutyTime3c={hospital.dutyTime3c}&dutyTime4s={hospital.dutyTime4s}&dutyTime4c={hospital.dutyTime4c}
                                    &dutyTime5s={hospital.dutyTime5s}&dutyTime5c={hospital.dutyTime5c}&dutyTime6s={hospital.dutyTime6s}&dutyTime6c={hospital.dutyTime6c}&dutyTime7s=${hospital.dutyTime7c}&dutyTime8s={hospital.dutyTime8s}
                                    &dutyTime8c={hospital.dutyTime8c}' className="btn btn-outline-primary">예약하기</a>
                
                                    <button type="button" className="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#basicModal">
                                        전화하기
                                    </button>
                                        <div className="modal fade" id="basicModal" tabindex="-1" style={{display: 'none'}} aria-hidden="true">
                                            <div className="modal-dialog" role="document">
                                            <div className="modal-content">
                                                <div className="modal-header">
                                                <h5 className="modal-title" id="exampleModalLabel1" ></h5>
                                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div className="modal-body">
                                                <div className="row">
                                                    <div className="col mb-3" style={{textAlign:'center'}}>
                                                    <label for="nameBasic" className="form-label" style={{fontSize:'15pt', color:'#454545', fontWeight:'bold'}}>{hospital.dutyName}에 전화하시겠습니까?</label>
                                                    </div>
                                                </div>
                                                </div>
                                                <div className="modal-footer d-flex justify-content-center">					       
                                                <a href="tel:{hospital.dutyTel1}" className="btn btn-primary" >전화하기</a>
                                                <button type="button" className="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                        <a href="/kids/writeReview?hpid={hospital.hpid}&dutyName={hospital.dutyName}" className="btn btn-outline-primary">리뷰 작성</a>					         
                                </div>
                            </div>
                        </div>
                        <div className="col-md-12">
                            <br/><br/>
                            <p style={{fontSize:'20pt', color:'black', fontWeight:'bold'}}> {hospital.dutyName} ({hospital.hpid}) 리뷰</p>
                            {
                            review === null ? 
                                (<div>
                                    <br/>
                                    <p style={{textAlign:'center', fontWeight:'bold', fontSize:'15pt', color:'#454545'}}>등록된 리뷰가 없습니다.</p>
                                </div>)
                            : review.map((review) => {
                                return(
                                <div className="row">
                                <div className="col-md-12">
                                    <table  className="table card-table">
                                        <thead>
                                            <tr>
                                                <th style={{color:'black', fontWeight:'bold', fontSize:'13pt'}}>번호</th>
                                                <th style={{color:'black', fontWeight:'bold', fontSize:'13pt'}}>작성일</th>
                                                <th style={{color:'black', fontWeight:'bold', fontSize:'13pt'}}>작성자</th>
                                                <th style={{color:'black', fontWeight:'bold', fontSize:'13pt'}}>이메일</th>
                                                <th style={{color:'black', fontWeight:'bold', fontSize:'13pt'}}>제목</th>
                                                <th style={{color:'black', fontWeight:'bold', fontSize:'13pt'}}>별점</th>
                                                <th style={{color:'black', fontWeight:'bold', fontSize:'13pt'}}>내용</th>
                                            </tr>
                                        </thead>
                                        <tbody className="table-border-bottom-0">
                                            <tr>
                                                <td style={{color:'#454545', fontWeight:'bold', fontSize:'12pt'}}>{review.seqno}</td>
                                                <td style={{color:'#454545', fontWeight:'bold', fontSize:'12pt'}}>{review.regdate}</td>
                                                <td style={{color:'#454545', fontWeight:'bold', fontSize:'12pt'}}>{review.writer}</td>
                                                <td style={{color:'#454545', fontWeight:'bold', fontSize:'12pt'}}>{review.email}</td>
                                                <td style={{color:'#454545', fontWeight:'bold', fontSize:'12pt'}}>{review.title}</td>
                                                {/*<Ranking/>*/}                        
                                                <td style={{color:'#454545', fontWeight:'bold', fontSize:'12pt'}}>{review.content}</td>
                                            </tr>
                                        </tbody>
                                    
                                    </table>
                                </div>				    		
                            </div>);
                                
                            })}
                        </div>
                    </div>
            </Frame>
        </div>
                    
            
  );
}

export default ViewHospital;