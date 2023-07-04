import {useState, useEffect} from 'react';
import axios from 'axios';
import React from 'react';
import Login from './pages/login';
import Home from './pages/home';
import Pediatric from './pages/pediatric';
import Emergency from './pages/emergency';
import PublicHealth from './pages/publicHealth';
import ModifyInfo from './pages/modifyInfo';
import HealthInfo from './pages/healthInfo';
import Reservation from './pages/reservation';
import Receipt from './pages/receipt';
import MyReceipt from './pages/myreceipt';
import ViewHospital from './pages/viewhospital';
import Babycard from './pages/babycard';
import ViewBabyCard from './pages/viewbabycard';
import WriteBabyCard from './pages/writebabycard';
import ModifyBabyCard from './pages/modifybabycard';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './static/assets/img/favicon/favicon.ico';
import './static/assets/vendor/fonts/boxicons.css';
import './static/assets/vendor/css/core.css';
import './static/assets/vendor/css/theme-default.css';
import './static/assets/css/demo.css';
import './static/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css';


import './static/assets/vendor/libs/jquery/jquery';
import './static/assets/vendor/libs/popper/popper';
import './static/assets/vendor/js/bootstrap';
import './static/assets/vendor/js/helpers';
import './static/assets/js/config';
import './static/assets/js/menu';
import './static/assets/js/pages-account-settings-account';
import './static/js/main';

import 'https://buttons.github.io/buttons.js';


const App = () => {
  

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/home" element ={<Home/>} />
        <Route path="/" element={<Login />}/>
        <Route path="/login" element={<Login />}/>
        <Route path="/pediatric" element={<Pediatric />} />
        <Route path="/emergency" element={<Emergency />} />
        <Route path="/publicHealth" element={<PublicHealth/>} />
        <Route path="/modifyInfo" element={<ModifyInfo />} />
        <Route path="/reservation" element={<Reservation />} />
        <Route path="/receipt" element={<Receipt />} />
        <Route path="/myreceipt" element={<MyReceipt />} />
        <Route path="/viewHospital" element={<ViewHospital />} />
        <Route path="/healthInfo" element={<HealthInfo/>} />
        <Route path="/mybabycard" element={<Babycard />} />
        <Route path="/viewbabycard" element={<ViewBabyCard />} />
        <Route path="/writebabycard" element={<WriteBabyCard />} />
        <Route path="/modifybabycard" element={<ModifyBabyCard />} />
      </Routes>
    </BrowserRouter>
  );
};






export default App;