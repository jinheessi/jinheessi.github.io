

const Modal = () => {
    return (
	<div className="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div className="modal-dialog">
            <div className="modal-content">
            <br />
	        <h4 style={{textAlign:'center', fontFamily:'GODOM'}}><b>로그인</b></h4>
	        <br />
	        <p style={{textAlign:'center'}}><b>로그인이 필요합니다. 로그인하시겠습니까?</b></p>
	        <br />
	        <div className="d-flex justify-content-center">
	        	<a href="/kids/login" data-bs-target="#exampleModal_1" className="btn btn-outline-primary" style={{display:'inline-box'}}>사용자로 로그인</a>&nbsp;
	        	<a href="/master/login" data-bs-target="#exampleModal_1" className="btn btn-outline-success" style={{display:'inline-box'}}>관리자로 로그인</a>&nbsp;
	        	<a href="/kids/home" className="btn btn-outline-secondary" data-dismiss="modal" style={{display:'inline-box'}}>닫기</a>
	        </div>
	        <br />
            </div>
        </div>
    </div>
	  
    );
};

export default Modal;