import Frame from '../components/Frame';
import Baby_Info_1 from '../static/images/0228_이달의_건강소식1.jpg';
import Baby_Info_2 from '../static/images/98579_48432_261.jpg';
import Baby_Info_3 from '../static/images/fbcf4f11-4056-4189-861e-611c6e10fef7.png';
import Baby_Info_4 from '../static/images/57541_60588_2055.jpg';
import Baby_Info_5 from '../static/images/10275_11147_3721.jpg';
import Baby_Info_6 from '../static/images/영유아질식사고예방법1.jpg';

const HealthInfo = () => {
    return (
        <div>
          <Frame>
            <p style={{color:'black', fontSize:'22pt'}}><b>건강 정보</b></p>
            <hr></hr>
            <div className="row row-cols-2 row-cols-md-3 g-4 mb-5">
              <div className="col">
                <div className="card h-100" style={{border:'none'}}>
                  <a href="https://kdca.go.kr/gallery.es?mid=a20509000000&bid=0007&b_list=9&act=view&list_no=145568&nPage=2&vlist_no_npage=3&keyField=&keyWord=&orderby=" target="_blank"><img className="card-img-top" src={Baby_Info_1} alt="Card image cap" style={{height:'100%'}}></img></a>
                  <div className="card-body">
                    <h4 className="card-title" style={{color:'black', fontWeight:'bold', fontFamily:'GODOM'}}><b>어린이 국가예방접종 지원 사업</b></h4>
                    <p className="card-text mt-2" style={{color: '#454545', fontSize:'12pt'}}><b>국가에서 백신(18종) 예방접종비용을 지원하는 사업</b></p>
                  </div>
                </div>
              </div>
              <div className="col">
                <div className="card h-100" style={{border:'none'}}>
                <a href="https://www.ibabynews.com/news/articleView.html?idxno=98579" target="_blank"><img className="card-img-top" src={Baby_Info_2} alt="Card image cap"></img></a>
                  <div className="card-body">
                    <h4 className="card-title" style={{color:'black', fontWeight:'bold', fontFamily:'GODOM'}}><b>영유아 눈건강 수칙</b></h4>
                    <p className="card-text" style={{color:'#454545', fontSize:'12pt'}}><b>취학 전 아동 실명예방 사업 영유아 눈 건강 수칙</b></p>
                  </div>
                </div>
              </div>
              <div className="col">
                <div className="card h-100" style={{border:'none'}}>
                  <a href="https://www.mkhealth.co.kr/news/articleView.html?idxno=57541" target="_blank"><img className="card-img-top" src={Baby_Info_3} alt="Card image cap"></img></a>
                  <div className="card-body">
                    <h4 className="card-title" style={{color:'black', fontWeight:'bold', fontFamily:'GODOM'}}><b>어린이 의약품 보관 수칙</b></h4>
                    <p className="card-text" style={{color:'#454545', fontSize:'12pt'}}><b>어린이 의약품 보관법 5대 수칙</b></p>
                  </div>
                </div>
              </div>
              <div className="col">
                <div className="card h-100" style={{border:'none'}}>
                  <a href="https://www.mkhealth.co.kr/news/articleView.html?idxno=57541" target="_blank"><img className="card-img-top" src={Baby_Info_4} alt="Card image cap" style={{height:'100%'}}></img></a>
                  <div className="card-body">
                    <h4 className="card-title" style={{color:'black', fontWeight:'bold', fontFamily:'GODOM'}}><b>봄철, 어린이 감염병 주의!</b></h4>
                    <p className="card-text" style={{color:'#454545', fontSize:'12pt'}}><b>봄철, 어린이들에게 감염병이 쉽게 감염될 수 있으므로 주의</b></p>
                  </div>
                </div>
              </div>
              <div className="col">
                <div className="card h-100" style={{border:'none'}}>
                <a href="https://www.ibabynews.com/news/articleView.html?idxno=98579" target="_blank"><img className="card-img-top" src={Baby_Info_5} alt="Card image cap"></img></a>
                  <div className="card-body">
                    <h4 className="card-title" style={{color:'black', fontWeight:'bold', fontFamily:'GODOM'}}><b>6월 9일! 구강 보건의 날</b></h4>
                    <p className="card-text" style={{color:'#454545', fontSize:'12pt'}}><b>구강 보건의 날의 의미는?</b></p>
                  </div>
                </div>
              </div>
              <div className="col">
                <div className="card h-100" style={{border:'none'}}>
                  <a href="https://blog.naver.com/kfdazzang/222481987412" target="_blank"><img className="card-img-top" src={Baby_Info_6} alt="Card image cap"></img></a>
                  <div className="card-body">
                    <h4 className="card-title" style={{color:'black', fontWeight:'bold', fontFamily:'GODOM'}}><b>영유아 질식사고 예방법</b></h4>
                    <p className="card-text" style={{color:'#454545', fontSize:'12pt'}}><b>영유아는 삼킬 수 있는 능력이 부족하여 세심한 주의가 필요</b></p>
                  </div>
                </div>
              </div>
            </div>
            <div className="content-backdrop fade"></div>
          </Frame>
        </div>
            
       
    );
};

export default HealthInfo;