import {useState, useEffect} from 'react';
import Frame from '../components/Frame';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import "../../node_modules/react-datepicker/dist/react-datepicker.css";
import styled from 'styled-components';
import { ko } from "date-fns/esm/locale";

const Reservation = () => {
    const [hospital, setHospital] = useState(null);
    const[email, setEmail] = useState(null);
    const currentDate = new Date();
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    const formattedDate = currentDate.toLocaleDateString('ko-KR', options);

    useEffect(() => {
        axios.get('/kids/reservation')
        .then((response)=>response.json())
        .then((data) => setHospital(data.hospital))
        .catch((error) => console.log(error));
    }, []);

    useEffect(() => {
        axios.get('/kids/reservation')
        .then((response)=>response.json())
        .then((data) => setEmail(data.email))
        .catch((error) => console.log(error));
    }, []);
	
	

	const Datepicker = () => {
		const [selectedDateTime, setSelectedDateTime] = useState(new Date());
		
		

		return (
			<div>
				<DatePicker
					showIcon
					locale={ko}
					selected={selectedDateTime} 
					onChange={date => setSelectedDateTime(date)}
					showTimeSelect
					timeFormat="HH:mm"
					timeIntervals={30}
					timeCaption="시간"
					dateFormat="yyyy년 MM월 dd일 HH:mm"
					className="react-datepicker"
				/>
				<p>{selectedDateTime.toLocaleString()}</p>
			</div>
		  
		);
	};

	
    return (
        <div>
            <Frame>
            <div className="row">
	              <div className="col-12">
					<input type="hidden" /*value={hospital.dutyName}*/ id="dutyName" />
	              	<p style={{fontSize:'23pt', color:'black', fontWeight:'bold'}}>{/*hospital.dutyName*/} ({/*hospital.hpid*/}) 예약</p>
          	      </div>
          	      <hr/>
              </div>
              <br/><br/><br/>
            <div className="row d-flex justify-content-center">
            	<div className="col-md-5">
            		<div className="card mb-4" style={{border:'none'}}>
            		<p style={{fontSize:'18pt'}}><b>예약 필수 정보</b></p>
				      <div className="card-body">
				        <form>
				          <div className="row mb-3">
				            <label className="col-sm-2 col-form-label" for="email" style={{color:'#454545', fontSize:'11pt'}}><b>이메일</b></label>
				            <div className="col-sm-10">
				              <input type="text" className="form-control" id="email" value={email} readonly/>
				            </div>
				          </div>
				          <div className="row mb-3">
				            <label className="col-sm-2 col-form-label" for="birth" style={{color:'#454545', fontSize:'11pt'}}><b>생년월일</b></label>
				            <div className="col-sm-10">
				              <input type="text" className="form-control" id="birth" />
				            </div>
				          </div>
				           <div className="row mb-3">
				           <div className="col-12">
				           		<p style={{display:'inline-block', color:'#454545'}}><b>성별 &emsp;&emsp;&emsp;&emsp;</b></p>
				            <div className="form-check form-check-inline">
							  <input className="form-check-input" type="radio" id="gender1" name="gender" value="남성" style={{display:'inline-block'}}/>
							  <label className="form-check-label" for="gender1" style={{color:'#454545'}}><b>남성</b></label>
							</div>
							<div className="form-check form-check-inline">
							  <input className="form-check-input" type="radio" id="gender2" name="gender" value="여성"style={{display:'inline-block'}}/>
							  <label className="form-check-label" for="gender2" style={{color:'#454545'}}><b>여성</b></label>
							</div>
				           </div>
				          </div>
				          <div className="row mb-3">
				            <label className="col-sm-2 col-form-label" for="weight" style={{color:'#454545', fontSize:'11pt'}}><b>키/체중</b></label>
				            <div className="col-sm-10">
				              <input type="text" id="height" name="height" className="form-control"/>
				              <input type="text" id="weight" name="weight" className="form-control"/>
				            </div>
				          </div>
				          <div className="row mb-3">
				            <label className="col-sm-2 col-form-label" for="userinfo" style={{color:'#454545', fontSize:'11pt'}}><b>증상</b></label>
				            <div className="col-sm-10">
				              <textarea id="userinfo" name="userinfo" className="form-control"></textarea>
				            </div>
				          </div>
				          <input type="hidden" id="hpid" name="hpid" /*value={hospital.hpid}*/ />
				          <input type="hidden" id="regdate" name="regdate" value={formattedDate}/>
				          </form>
				      </div>
				    </div>
            	
            	</div>
            	<div className="col-md-7 mb-2" >
                  <div className="row card"  style={{boxShadow:'none', border:'none'}}>
                    <div className="d-flex justify-content-center">
                      <div className="col-md-12 col-12">
                      <p style={{fontSize:'18pt'}}><b>날짜 선택</b></p>
                      	<form autocomplete="off">
							<Datepicker/>
				    	</form>
                       </div>
                      </div>
                    </div>
                 </div>
            	</div>
                <div className="row">
                    <div className="col-md-12 d-flex justify-content-start">
                        <input id="rbtn" name="rbtn" type="button" className="btn btn-outline-primary"  value="예약하기" />
                    </div>
                </div>
                <div className="content-backdrop fade"></div>
            </Frame>
        </div>
    );
};

export default Reservation;