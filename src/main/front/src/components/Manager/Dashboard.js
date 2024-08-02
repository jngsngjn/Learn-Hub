import React from 'react';
import './Dashboard.css';

const Dashboard = () => {
  return (
    <div className="dashboard">
      <div className="dashboard-item">
        <h3>설문 조사</h3>
        <ul>
          <li>네이버 클라우드 네트워크 만족도 설문조사</li>
          <li>네이버 클라우드 네트워크 만족도 설문조사</li>
        </ul>
      </div>
      <div className="dashboard-item">
        <h3>1:1 문의 내역</h3>
        <div className="message-count">12</div>
      </div>
      <div className="dashboard-item">
        <h3>1:1 문의 내역</h3>
        <div className="message-count">12</div>
      </div>
    </div>
  );
};

export default Dashboard;