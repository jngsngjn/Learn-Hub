import React from 'react';
import CourseProgress from './CourseProgress';
import Calendar from './Calendar';
import MessageBox from './MessageBox';
import SettingList from './SettingList';
import './Dashboard.css';

const Dashboard = () => {
  return (
    <div className="dashboard">
      <div className="sidebar">
        <ul>
          <li>대시보드</li>
          <li>교육 과정</li>
          <li>학생 관리</li>
          <li>강사 관리</li>
          <li>공지사항</li>
          <li>문의
            <ul>
              <li>학생 문의</li>
              <li>강사 문의</li>
            </ul>
          </li>
        </ul>
      </div>
      <div className="content">
        <div className="header">
          <div className="date">2024.08.26 (월)</div>
        </div>
        <div className="main">
          <div className="course-progress">
            <CourseProgress />
          </div>
          <div className="calendar">
            <Calendar />
          </div>
          <div className="survey">
            <SettingList />
          </div>
          <div className="messages">
            <MessageBox />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
