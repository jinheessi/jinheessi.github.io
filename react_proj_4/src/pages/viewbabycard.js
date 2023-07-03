import {useState, useEffect} from 'react';
import Frame from '../components/Frame';
import axios from 'axios';


const ViewBabyCard = () => {
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
				<div className="row row-cols-2 row-cols-md-3 g-4 mb-5">
				  <div className="col">
				    <div className="card h-100">
				      
				      <div className="card-body">
				        <h4 className="card-title" style={{color:'black'}}><b>어린이 국가예방접종 지원 사업</b></h4><hr />
				        <p className="card-text" style={{color:'#454545', fontSize:'12pt'}}><b>국가에서 백신(18종) 예방접종비용을 지원하는 사업</b></p>
				      </div>
				    </div>
				  </div>
				</div>
            </Frame>
        </div>
    );
};

export default ViewBabyCard;