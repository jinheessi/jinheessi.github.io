import {useState, useEffect} from 'react';
import axios from 'axios';
import img1 from '../../static/images/10275_11147_3721.jpg';
import img2 from '../../static/images/98579_48432_261.jpg';
import img3 from '../../static/images/fbcf4f11-4056-4189-861e-611c6e10fef7.png';
import img4 from '../../static/images/영유아질식사고예방법1.jpg';
import img5 from '../../static/images/57541_60588_2055.jpg';
import img6 from '../../static/images/0228_이달의_건강소식1.jpg';
const Hero = () => {
	return (
		<section id="hero" className="d-flex align-items-center"  style={{backgroundImage:'../static/images/hero-bg.jpg', backgroundSize:'cover'}} >
			<div className="container">
			<div className="row">
				<div className="col-12 col-md-6">
				<h1 style={{color:'black', textAlign:'center', fontFamily:'GODOM'}}><b>소아과&nbsp;/&nbsp;응급실 예약 플랫폼</b></h1>
				<br />
				<h2 style={{color:'black', fontSize:'20pt', textAlign: 'center', fontFamily:'GODOM'}}><b>실시간 국내 병원 데이터를 이용한 간편한 예약 서비스</b></h2>
				</div>
			</div>
			</div>
	  	</section>
	);
};

const Whyus = () => {
	return (
		<section id="why-us" className="why-us">
	      <div className="container">
	        <div className="row">
	          <div className="col-md-4">
	          	<br /><br /><br />
	            <div className="content">
	              <p style={{textAlign: 'center', fontSize:'25pt'}}><b>소아과가 필요한 이유</b></p>
	              <div className="text-center">
	                <a href="https://news.mt.co.kr/mtview.php?no=2022122615084144365" className="more-btn" style={{fontSize:'15pt'}} target="_blank" rel="noopener noreferrer"><b>기사 링크</b></a>
	              </div>
	            </div>
	          </div>
	          <div className="col-lg-8">
	            <div className="icon-boxes">
	              <div className="row">
	                <div className="col-xl-4">
	                  <div className="icon-box">
	                    <p style={{fontSize: '25pt'}}>🏥</p>
	                    <h4 style={{color:'black', fontSize:'15pt', fontFamily:'GODOM'}}>소아청소년과 진료 축소</h4>
	                     <p style={{color:'#454545', fontSize:'12pt'}}><b>머니투데이 기사에 따르면, 최근 대학병원에서 소청과 진료 중단·축소가 잇따르며 의사 부족 문제가 현실화되고 있다.</b></p>
	                  </div>
	                </div>
	                <div className="col-xl-4 d-flex align-items-stretch">
	                  <div className="icon-box mt-4 mt-xl-0">
	                    <p style={{fontSize:'25pt'}}>📉</p>
	                    <h4 style={{color:'black', fontSize:'15pt', fontFamily:'GODOM'}}>소아청소년과 지원 부족</h4>
		                <p style={{color:'#454545', fontSize:'12pt'}}><b>길병원 소아청소년과는 내년 전반기 전공의 1년차로 4명을 모집했으나 단 한명의 지원자도 받지 못했다.</b></p>
	                  </div>
	                </div>
	                <div className="col-xl-4 d-flex align-items-stretch">
	                  <div className="icon-box mt-4 mt-xl-0">
	                    <p style={{fontSize:'25pt'}}>📝</p>
	                    <h4 style={{color:'black', fontSize:'15pt', fontFamily:'GODOM'}}>충격적인 설문 결과</h4>
	                    <p style={{color:'#454545', fontSize:'12pt'}}><b>대한소아청소년과학회에 따르면 소아청소년과 전공의 수련병원 96곳 중 75%는 내년에 진료를 줄일 예정이라고 답했다.</b></p> 
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	    </section>
	);
};

const About = () => {
	return (
		<section id="about" className="about">
	      <div className="container">
	      	&emsp;
	        <div className="row">
	          <div className="col-12 col-md-5 mb-5 video-box align-items-stretch position-relative" style={{backgroundImage:'../static/images/close-up-doctor-kid-wearing-masks.jpg', backgroundSize: 'cover'}}>
	            <a href="https://www.youtube.com/watch?v=uzHRD0BvGCo" className="glightbox play-btn mb-4"></a>
	          </div>
	          <div className="col-12 col-md-7" style={{paddingTop:'5%', paddingLeft:'4%'}}>
	    
		          	<p style={{fontSize:'23pt', color:'black'}}><strong>지원자 ‘0명’ 소아과, 의사 부족 ‘비상’ 원인과 해법은?</strong></p>
		          	
	          		<div className="col-12">
	          			<div className="icon-box">
			              <div className="icon"><i className="fas fa-hospital"></i></div>
			              <p className="title" style={{color:'black', fontSize:'16pt'}}><b>소아과의 현실</b></p>
			              <p className="description" style={{color:'#454545', fontSize:'13pt'}}><b>소아청소년과 의사가 없어, 많은 병원들이 폐업</b></p>
			            </div>
	          		</div>
	          		<div className="col-12">
		          		<div className="icon-box">
			              <div className="icon"><i className="fas fa-user-md"></i></div>
			              <p className="title" style={{color:'black', fontSize:'16pt'}}><b>전공의 수 감소</b></p>
			              <p className="description" style={{color:'#454545', fontSize:'13pt'}}><b>소아청소년과에 지원하는 전공의 수가 지속적으로 감소</b></p>
			            </div>
	          		</div>
	          		
	          		<div className="col-12">
	          			<div className="icon-box">
			              <div className="icon"><i className="bx bx-book"></i></div>
			              <p className="title" style={{color:'black', fontSize:'16pt'}}><b>의대 지원 부족</b></p>
			              <p className="description" style={{color:'#454545', fontSize:'13pt'}}><b>전국 주요 종합병원 66곳 중 56곳에서 지원자 ‘0’명을 기록</b></p>
			            </div>
	          		</div>
	            
	          </div>
	        </div>
	
	      </div>
	    </section>
	);
};

const Counts = () => {
	return (
		<section id="counts" className="counts">
	      <div className="container">
	
	        <div className="row d-flex justify-content-center">
	
	          <div className="col-lg-3 col-md-6">
	            <div className="count-box">
	              <i className="fas fa-user-md"></i>
	              <span data-purecounter-start="0" data-purecounter-end="6912" data-purecounter-duration="1" data-purecounter-seperator="," className="purecounter">6912</span>
	              <p style={{color:'black', fontFamily:'GODOM',fontSize:'13pt'}}>소아청소년과 전문의 수<br /><small>(출처: 건강보험심사평가원, 2023)</small></p>
	            </div>
	          </div>
	
	          <div className="col-lg-3 col-md-6 mt-5 mt-md-0">
	            <div className="count-box">
	              <i className="far fa-hospital"></i>
	              <span data-purecounter-start="0" data-purecounter-end="2147" data-purecounter-duration="1" data-purecounter-seperator="," className="purecounter">2147</span>
	              <p style={{color:'black', fontFamily:'GODOM',fontSize:'13pt'}}>전국 소아청소년과 병·의원 수<br /><small>(출처: 건강보험심사평가원, 2023)</small></p>
	            </div>
	          </div>
	
	          <div className="col-lg-3 col-md-6 mt-5 mt-lg-0">
	            <div className="count-box">
	              <i className="fas fa-flask"></i>
	              <span data-purecounter-start="0" data-purecounter-end="15.9" data-purecounter-duration="1" className="purecounter"></span>
	              <p style={{color:'black', fontFamily:'GODOM',fontSize:'13pt'}}>의대 전공의 모집률<br /><small>(전국 수련병원 전공의 모집률, 2023)</small></p>
	            </div>
	          </div>
			 
	        </div>
	
	      </div>
	    </section>
	);
};

const Services = () => {
	return (
		<section id="services" className="services">
	      <div className="container">
	
	        <div className="section-title">
	          <h2  style={{color:'black', fontFamily:'GODOM'}}>서비스</h2>
	          <p style={{color:'#454545', fontSize:'15pt'}}><b>실시간 데이터를 이용한 응애응애의 소아과/응급실 종합 예약 서비스</b></p>
	        </div>
	
	        <div className="row">
	          <div className="col-12 col-lg-4 col-md-6 d-flex align-items-stretch">
	            <div className="icon-box" style={{paddingLeft:'20%',paddingRight:'20%'}}>
	              <div className="icon"><i className="fas fa-heartbeat"></i></div>
	              <h4><a href="" style={{color:'black', fontFamily:'GODOM'}}>소아과</a></h4>
	              <p style={{color:'#454545', fontSize:'13pt', textAlign:'center'}}>현재 접속한 위치 정보 기준 가장 가까운 소아과 병의원 추천</p>
	            </div>
	          </div>
	
	          <div className="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 mt-md-0">
	            <div className="icon-box" style={{paddingLeft:'20%',paddingRight:'20%'}}>
	              <div className="icon"><i className="fas fa-hospital"></i></div>
	              <h4><a href="" style={{color:'black', fontFamily:'GODOM'}}>응급실</a></h4>
	              <p style={{textAlign:'center',  color:'#454545', fontSize:'13pt', textAlign:'center'}}>현재 접속한 위치 정보와 실시간 데이터 체크, 방문하기 적합한 응급실 추천</p>
	            </div>
	          </div>
	
	          <div className="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 mt-lg-0">
	            <div className="icon-box" style={{paddingLeft:'20%',paddingRight:'20%'}}>
	              <div className="icon"><i className="fas fa-hospital-user"></i></div>
	              <h4><a href="" style={{color:'black', fontFamily:'GODOM'}}>예약 관리</a></h4>
	              <p style={{textAlign:'center', color:'#454545', fontSize:'13pt', textAlign:'center'}}>소아과/응급실 All-In-One 예약 관리</p>
	            </div>
	          </div>
	
	          <div className="col-lg-4 col-md-6 d-flex align-items-stretch mt-4">
	            <div className="icon-box" style={{paddingLeft:'20%',paddingRight:'20%'}}>
	              <div className="icon"><i className="fas fa-map"></i></div>
	              <h4><a href="" style={{color:'black',fontFamily:'GODOM'}}>카카오 맵 API 이용</a></h4>
	              <p style={{textAlign:'center',  color:'#454545', fontSize:'13pt', textAlign:'center'}}>카카오 맵 API를 이용하여 지역별 병의원 목록 출력</p>
	            </div>
	          </div>
	
	          <div className="col-lg-4 col-md-6 d-flex align-items-stretch mt-4">
	            <div className="icon-box" style={{paddingLeft:'20%',paddingRight:'20%'}}>
	              <div className="icon"><i className="fas fa-database"></i></div>
	              <h4><a href="" style={{color:'black', fontFamily:'GODOM'}}>공공데이터 API</a></h4>
	              <p style={{textAlign:'center',  color:'#454545', fontSize:'13pt', textAlign:'center'}}>공공데이터포털의 국립중앙의료원전국 병·의원 검색 서비스 API 사용</p>
	            </div>
	          </div>
	
	          <div className="col-lg-4 col-md-6 d-flex align-items-stretch mt-4">
	            <div className="icon-box" style={{paddingLeft:'20%',paddingRight:'20%'}}>
	              <div className="icon"><i className="fas fa-notes-medical"></i></div>
	              <h4><a href="" style={{color:'black', fontFamily:'GODOM'}}>건강 정보</a></h4>
	              <p style={{textAlign:'center',  color:'#454545', fontSize:'13pt', textAlign:'center'}}>영유아에게 유익한 건강 정보 카드 뉴스 제공</p>
	            </div>
	          </div>
	
	        </div>
	
	      </div>
	    </section>
	);
};

const Appointment = ({session}) => {
	return (
		<section id="appointment" className="appointment section-bg">
	      
          {/*session.email === null && 
            <div className="container">
                <div className="section-title">
	          <h2 style={{color:'black'}}>회원 가입하여 예약 서비스를 이용해보세요</h2>
	        </div>
	          <div className="text-center"><a href="/master/signup" className="btn-primary" style={{fontSize:'16pt', backgroundColor:'rgba(25,119,204)', paddingTop: '17px', paddingBottom:'17px', paddingLeft:'25px', paddingRight:'25px', borderRadius:'40px', color:'white'}}>회원 가입</a>
              </div>
	        </div>
	*/}
	    </section>
	);
};

const Faq = () => {
	return (
		<section id="faq" className="faq section-bg">
	      <div className="container">
	
	        <div className="section-title">
	          <h2 style={{color:'black', fontFamily:'GODOM'}}>자주 묻는 질문</h2>
	        </div>
	
	        <div className="faq-list">
	          <ul>
	            <li data-aos="fade-up">
	              <i className="bx bx-help-circle icon-help"></i> <a style={{fontFamily:'GODOM'}} data-bs-toggle="collapse" className="collapse" data-bs-target="#faq-list-1"><b>&emsp;아이디를 잊었습니다. 어떻게 해야할까요?</b><i className="bx bx-chevron-down icon-show"></i><i className="bx bx-chevron-up icon-close"></i></a>
	              <div id="faq-list-1" className="collapse" data-bs-parent=".faq-list">
	                <p><b>
	                  고객 센터에 문의하시거나, 로그인 페이지의 회원 정보 찾기 기능을 이용하시면 됩니다. 
	                </b></p>
	              </div>
	            </li>
	
	            <li data-aos="fade-up" data-aos-delay="100">
	              <i className="bx bx-help-circle icon-help"></i> <a style={{fontFamily:'GODOM'}} data-bs-toggle="collapse" data-bs-target="#faq-list-2" className="collapsed"><b>&emsp;병원 목록이 뜨지 않아요. 어떻게 해야할까요? </b><i className="bx bx-chevron-down icon-show"></i><i className="bx bx-chevron-up icon-close"></i></a>
	              <div id="faq-list-2" className="collapse" data-bs-parent=".faq-list">
	                <p>
	                  <b>F5 버튼을 눌러 페이지를 새로고침하시거나, 브라우저 상단의 새로고침 버튼을 클릭하여 페이지를 새로고침하시면 됩니다.</b>
	                </p>
	              </div>
	            </li>
	
	            <li data-aos="fade-up" data-aos-delay="200">
	              <i className="bx bx-help-circle icon-help"></i> <a style={{fontFamily:'GODOM'}} data-bs-toggle="collapse" data-bs-target="#faq-list-3" className="collapsed"><b>&emsp;제가 예약한 내역이 나타나지 않아요.</b><i className="bx bx-chevron-down icon-show"></i><i className="bx bx-chevron-up icon-close"></i></a>
	              <div id="faq-list-3" className="collapse" data-bs-parent=".faq-list">
	                <p>
						<b>페이지를 한 번 새로고침하시거나, 그럼에도 예약한 내역이 나타나지 않으시면 고객센터에 문의해주세요.</b>
	                </p>
	              </div>
	            </li>
	
	            <li data-aos="fade-up" data-aos-delay="300">
	              <i className="bx bx-help-circle icon-help"></i> <a style={{fontFamily:'GODOM'}} data-bs-toggle="collapse" data-bs-target="#faq-list-4" className="collapsed"><b>&emsp;지도에 제 위치가 나타나지 않아요.</b><i className="bx bx-chevron-down icon-show"></i><i className="bx bx-chevron-up icon-close"></i></a>
	              <div id="faq-list-4" className="collapse" data-bs-parent=".faq-list">
	                <p>
	                <b>
	                	API 로딩 문제일 수 있으므로, 단순히 새로고침하여 기다려주시기 바랍니다.
	                </b>
	                </p>
	              </div>
	            </li>
	
	          </ul>
	        </div>
	
	      </div>
	    </section>
	);
};

const Gallery = () => {
	return (
		<section id="gallery" className="gallery">
	      <div className="container">
	      	<div className="section-title">
	          <h2 style={{color:'black', fontFamily:'GODOM'}}>건강 정보</h2>
	        </div>
	        <div className="row g-0">
	          <div className="col-6 col-md-4">
	            <div className="gallery-item">
	              <a href="https://www.ibabynews.com/news/articleView.html?idxno=98579" target="_blank" className="galelry-lightbox" rel="noopener noreferrer">
	                <img src={img1} alt="" className="img-fluid"></img>
	              </a>
	            </div>
	          </div>
	          <div className="col-6 col-md-4">
	            <div className="gallery-item">
	              <a href="https://www.ibabynews.com/news/articleView.html?idxno=98579" target="_blank" className="galelry-lightbox" rel="noopener noreferrer">
	                <img src={img2} alt="" className="img-fluid"></img>
	              </a>
	            </div>
	          </div>
	          <div className="col-6 col-md-4">
	            <div className="gallery-item">
	              <a href="https://www.mkhealth.co.kr/news/articleView.html?idxno=57541" target="_blank" className="galelry-lightbox" rel="noopener noreferrer">
	                <img src={img3} alt="" className="img-fluid"></img>
	              </a>
	            </div>
	          </div>
	          <div className="col-6 col-md-4">
	            <div className="gallery-item">
	              <a href="https://blog.naver.com/kfdazzang/222481987412" target="_blank" className="galelry-lightbox" rel="noopener noreferrer">
	                <img src={img4} alt="" className="img-fluid"></img>
	              </a>
	            </div>
	          </div>
	          <div className="col-6 col-md-4">
	            <div className="gallery-item">
	              <a href="https://www.mkhealth.co.kr/news/articleView.html?idxno=57541" target="_blank" className="galelry-lightbox" rel="noopener noreferrer">
	                <img src={img5} alt="" className="img-fluid"></img>
	              </a>
	            </div>
	          </div>
	          <div className="col-6 col-md-4">
	            <div className="gallery-item">
	              <a href="https://kdca.go.kr/gallery.es?mid=a20509000000&bid=0007&b_list=9&act=view&list_no=145568&nPage=2&vlist_no_npage=3&keyField=&keyWord=&orderby=" target="_blank" className="galelry-lightbox" rel="noopener noreferrer"> 
	                <img src={img6} alt="" className="img-fluid"></img>
	              </a>
	            </div>
	          </div>
			</div>
	      </div>
	    </section>
	);
};



const Section = () => {
    const [session, setSession] = useState([]);
    useEffect(() => {
        axios.get('/kids/home')
        .then((response) => response.json())
        .then((data) => setSession(data))
        .catch((error) => console.log(error))
    }, []);

    return (
	  	<main id="main">
			<Hero />
			<Whyus />
			<About />
			<Counts />
			<Services />
			<Appointment />
			<Faq />
			<Gallery />
    	</main>
    );
};

export default Section;