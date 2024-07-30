import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Sidebar from '../../components/Manager/Sidebar';
import Calendar from '../../components/Manager/Calendar';
import CourseProgress from '../../components/Manager/Lecture';
import SettingList from '../../components/Manager/Survey';
import MessageBox from '../../components/Manager/MessageBox';
import StudentManagement from '../../components/Manager/Student_Management';
import CurriculumManagement from '../../components/Manager/Curriculum_Management';
import './ManagerMain.css';

function Dashboard() {
  return (
    <>
      <h1>대시보드</h1>
      <div className="dashboard-grid">
        <CourseProgress />
        <Calendar />
        <SettingList />
        <MessageBox />
      </div>
    </>
  );
}

function ManagerMain() {
  return (
    <div className="App">
      <div className="main-content">
        <Sidebar />
        <div className="content-area">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="student-management" element={<StudentManagement />} />
            <Route path="manager-curriculum" element={<CurriculumManagement />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default ManagerMain;
