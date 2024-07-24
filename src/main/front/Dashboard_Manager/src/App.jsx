import React from 'react';
import Calendar from './components/Calendar';
import CourseProgress from './components/CourseProgress';
import Dashboard from './components/Dashboard';
import './App.css';

function App() {
  return (
    <div className="App">
      <div className="sidebar">
        <h2>대시보드</h2>
        <ul>
          <li>교육 과정</li>
          <li>학생 관리</li>
          <li>강사 관리</li>
          <li>공지사항</li>
          <li>문의</li>
        </ul>
      </div>
      <div className="main-content">
        <h1>대시보드</h1>
        <div className="dashboard-grid">
          <CourseProgress />
          <Calendar />
          <Dashboard />
        </div>
      </div>
    </div>
  );
}

export default App;