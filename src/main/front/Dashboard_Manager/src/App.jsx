import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Calendar from './components/Calendar';
import CourseProgress from './components/Lecture';
import SettingList from './components/Survey';
import MessageBox from './components/MessageBox';
import StudentManagement from './components/Student_Management';
import './App.css';

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

function App() {
  return (
    <Router>
      <div className="App">
        <div className="main-content">
          <Sidebar />
          <div className="content-area">
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/student-management" element={<StudentManagement />} />
            </Routes>
          </div>
        </div>
      </div>
    </Router>
  );
}

export default App;
