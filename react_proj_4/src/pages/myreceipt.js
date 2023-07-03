import {useState, useEffect} from 'react';
import Frame from "../components/Frame";
import axios from 'axios';


const MyReceipt = () => {
    const [receiptList, setReceiptList] = useState(null);
    
    useEffect(() => {
        axios.get('/kids/myreceipt')
        .then((data) => setReceiptList(data))
        .then((response) => response.json())
        .catch((error) => console.log(error))
    }, []);

    return (
        <div>
            <Frame>
                <p style={{color:'black', fontSize:'22pt'}}><b>내 접수 관리</b></p>
                <hr />
                <div class="card">
                <div class="table-responsive text-nowrap">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>번호</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>병원</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>신청일</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>신청자</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>환자</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>생년월일</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>성별</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>증상</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>접수 상태</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>수정</th>
                                    <th style={{textAlign: 'center',fontSize: '12pt', color:'black'}}>취소</th>
                                </tr>
                            </thead>

                            <tbody>
                                {{receiptList} === null && 
                                    <td colspan="8">
                                        <p style={{textAlign: 'center',fontSize: '11pt', color:'#454545'}}><br /><b>접수가 존재하지 않습니다.</b></p>
                                    </td>
                                }
                                {{receiptList} !== null && receiptList.map((receipt) => {
                                    <tr>
                                        <td>{receipt.seqno}</td>
                                        <td>{receipt.duty_name} ({receipt.hpid})</td>
                                        <td>{receipt.regdate}</td>
                                        <td>{receipt.rname}</td>
                                        <td>{receipt.pname}</td>
                                        <td>{receipt.birth}</td>
                                        <td>{receipt.gender}</td>
                                        <td>{receipt.symptom}</td>
                                        <td><a href="/kids/modifyReceipt?seqno={list.seqno}" class="btn btn-outline-success">수정</a></td>
                                        <td><a href="/kids/deleteReceipt?seqno={list.seqno}" class="btn btn-outline-danger">취소</a></td> 
                                    </tr>
                                })} 
                            </tbody>
                        </table>
                    </div>
                </div>
                    
            </Frame>
        </div>
    );
};

export default MyReceipt;