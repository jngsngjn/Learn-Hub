import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Login from './components/Login';
import Signup from './components/Signup';
import LoginEmail from './components/Login_email';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        {/*기본 경로로 설정 나중에 수정 필요 */}
        <Route path="/" element={<Navigate replace to="/login" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/email" element={<LoginEmail />} />
      </Routes>
    </Router>
  );
}

export default App;
