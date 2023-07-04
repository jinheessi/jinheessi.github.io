
import styled from 'styled-components';
import {useState, useEffect} from 'react';


const Paragraph = styled.p`
    font-weight:bold;
    font-size:30pt;
    color:black;
    margin-top:20%;
    margin-bottom:10%;
`;
const DivColor = styled.div`
    color:black;
`;
const SpanColor =styled.span`
  color:black;
`;
const Menu = () => {
    const reload = () => {
        window.location.reload();
    }

    const [activeItem, setActiveItem] = useState('');

    const handleItemClick = (event) => {
      const clickedItem = event.target.closest('li');
      setActiveItem(clickedItem.id);
    };
        
  

    return (
        <aside id="layout-menu" className="layout-menu menu-vertical menu bg-menu-theme">
          <div className="app-brand demo">
          	<br></br>
          	<Paragraph>응애응애</Paragraph>

            <a href="#" className="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none" onClick={reload}>
              <i className="bx bx-chevron-left bx-sm align-middle"></i>
            </a>
          </div>

          <div className="menu-inner-shadow"></div>
	      	<br></br><br></br>
          <ul className="menu-inner py-1">
            <li id="home" className={`menu-item ${activeItem === 'home' ? 'active' : ''}`}>
              <a href="/home" className="menu-link" onClick={handleItemClick}>
                <i className="menu-icon tf-icons bx bx-home-circle"></i>
                <DivColor data-i18n="Analytics">홈 페이지</DivColor>
              </a>
            </li>

            <li id="hospital" className={`menu-item ${activeItem === 'hospital' ? 'active' : ''}`}>
              <a href="#" className="menu-link menu-toggle" onClick={handleItemClick}>
                <i className="menu-icon tf-icons bx bx-layout"></i>
                <DivColor data-i18n="Layouts">예약</DivColor>
              </a>

              <ul className="menu-sub">
                <li className="menu-item">
                  <a href="/pediatric" className="menu-link">
                    <DivColor data-i18n="Without menu">소아과</DivColor>
                  </a>
                </li>
                <li className="menu-item">
                  <a href="/emergency" className="menu-link">
                    <DivColor data-i18n="Without navbar">응급실</DivColor>
                  </a>
                </li>
              </ul>
            </li>

            <li className="menu-header small text-uppercase">
              <SpanColor className="menu-header-text" >회원 정보 관리 & 회원 인증</SpanColor>
            </li>
            <li id="mypage"className={`menu-item ${activeItem === 'mypage' ? 'active' : ''}`}>
              <a href="#" className="menu-link menu-toggle" onClick={handleItemClick}>
                <i className="menu-icon tf-icons bx bx-dock-top"></i>
                <DivColor data-i18n="Account Settings" >마이 페이지</DivColor>
              </a>
              <ul className="menu-sub">
                <li className="menu-item">
                  <a href="/modifyInfo" className="menu-link">
                    <DivColor data-i18n="Account">내 정보 관리/수정</DivColor>
                  </a>
                </li>
                <li className="menu-item">
                  <a href="/myreservation" className="menu-link">
                    <DivColor data-i18n="Notifications" >내 예약 관리/수정</DivColor>
                  </a>
                </li>
                <li className="menu-item">
                  <a href="/myreceipt" className="menu-link">
                    <DivColor data-i18n="Notifications" >내 접수 관리/수정</DivColor>
                  </a>
                </li>
                
                <li className="menu-item">
                  <a href="/mybabycard" className="menu-link">
                    <i className="menu-icon tf-icons bx bx-dock-top"></i>
                    <DivColor data-i18n="Account Settings" >아기 수첩</DivColor>
                  </a>
                </li>
              </ul>
            </li>
            
            <li className="menu-header small text-uppercase"><SpanColor className="menu-header-text" >유용한 정보</SpanColor></li>
            <li id='healthInfo' className={`menu-item ${activeItem === 'healthInfo' ? 'active' : ''}`}>
              <a href="/healthInfo" className="menu-link" onClick={handleItemClick}>
                <i className="menu-icon tf-icons bx bx-collection"></i>
                <DivColor data-i18n="Basic" >건강 정보</DivColor>
              </a>
            </li>

            <li id="publicHealth" className={`menu-item ${activeItem === 'publicHealth' ? 'active' : ''}`}>
              <a href="/publicHealth" className="menu-link" onClick={handleItemClick}>
                <i className="menu-icon tf-icons bx bx-home-heart"></i>
                <DivColor data-i18n="Boxicons" >전국 보건소 찾기</DivColor>
              </a>
            </li>

            <li className="menu-header small text-uppercase"><SpanColor className="menu-header-text" >기타</SpanColor></li>
            
            <li id="notice" className={`menu-item ${activeItem === 'notice' ? 'active' : ''}`}>
              <a href="/notice" className="menu-link" onClick={handleItemClick}>
                <i className="menu-icon tf-icons bx bx-file"></i>
                <DivColor data-i18n="Documentation" >공지사항</DivColor>
              </a>
            </li>
            <li className="menu-item">
              <a
                href="https://github.com/themeselection/sneat-html-admin-template-free/issues"
                target="_blank"
                className="menu-link"
                rel="noopener noreferrer"
              >
                <i className="menu-icon tf-icons bx bx-support"></i>
                <DivColor data-i18n="Support" >고객 지원</DivColor>
              </a>
            </li>
            <li className="menu-item">
              <a
                href="https://themeselection.com/demo/sneat-bootstrap-html-admin-template/documentation/"
                target="_blank"
                className="menu-link"
                rel="noopener noreferrer"
              >
                <i className="menu-icon tf-icons bx bx-file"></i>
                <DivColor data-i18n="Documentation" >이용 약관</DivColor>
              </a>
            </li>
          </ul>
        </aside>
    );
};

export default Menu;