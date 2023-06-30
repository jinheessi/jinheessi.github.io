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
import Babycard from './pages/babycard';
import ViewHospital from './pages/viewhospital';

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import WriteBabyCard from './pages/writebabycard';


import './static/vendor/fontawesome-free/css/all.min.css';
import './static/vendor/animate.css/animate.min.css';
import './static/vendor/bootstrap/css/bootstrap.min.css';
import './static/vendor/bootstrap-icons/bootstrap-icons.css';
import './static/vendor/boxicons/css/boxicons.min.css';
import './static/vendor/glightbox/css/glightbox.min.css';
import './static/vendor/remixicon/remixicon.css';
import './static/vendor/swiper/swiper-bundle.min.css';
import './static/css/style.css';




import './static/assets/vendor/libs/jquery/jquery.js';
import './static/assets/vendor/js/helpers.js';
import './static/assets/vendor/js/bootstrap.js';
import './static/assets/vendor/libs/popper/popper.js';
import './static/assets/js/menu.js';

import 'https://buttons.github.io/buttons.js';





const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element ={<Home/>} />
        <Route path="/kids/login" element={<Login />}/>
        <Route path="/kids/pediatric" element={<Pediatric />} />
        <Route path="/kids/emergency" element={<Emergency />} />
        <Route path="/kids/publicHealth" element={<PublicHealth/>} />
        <Route path="/kids/modifyInfo" element={<ModifyInfo />} />
        <Route path="/kids/reservation" element={<Reservation />} />
        <Route path="/kids/receipt" element={<Receipt />} />
        <Route path="/kids/viewHospital" element={<ViewHospital />} />
        <Route path="/kids/healthInfo" element={<HealthInfo/>} />
        <Route path="/kids/mybabycard" element={<Babycard />} />
        <Route path="/kids/writebabycard" element={<WriteBabyCard />} />
      </Routes>
    </BrowserRouter>
  );
};






export default App;