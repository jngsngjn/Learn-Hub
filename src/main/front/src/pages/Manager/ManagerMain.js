import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Sidebar from '../../components/Manager/Sidebar';
import Calendar from '../../components/Manager/Calendar';
import CourseProgress from '../../components/Manager/Lecture';
import SettingList from '../../components/Manager/Survey';
import MessageBox from '../../components/Manager/MessageBox';
import StudentManagement from '../../components/Manager/Student_Management';
import CurriculumManagement from '../../components/Manager/Curriculum_Management';
import TeacherManagement from '../../components/Manager/Teacher_Management';
import Notice from '../../components/Manager/Notice';
import StudentContact from '../../components/Manager/Student_Contact';
import TeacherContact from '../../components/Manager/Teacher_Contact';
import Froala from '../../components/Manager/froala';
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
            <Route path="manage-students" element={<StudentManagement />} />
            <Route path="manage-curriculums" element={<CurriculumManagement />} />
            <Route path="manage-teachers" element={<TeacherManagement />} />
            <Route path="notice" element={<Notice />} />
            <Route path="contact-students" element={<StudentContact />} />
            <Route path="contact-teachers" element={<TeacherContact />} />
            <Route path="froala" element={<Froala />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default ManagerMain;
