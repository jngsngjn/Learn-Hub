import React from 'react';
import Sidebar from './components/Sidebar';
import Calendar from './components/Calendar';
import CourseProgress from './components/Lecture';
import SettingList from './components/Survey';
import MessageBox from './components/MessageBox';
import './App.css';

function App() {
  return (
    <div className="App">
      <div className="main-content">
        <Sidebar />
        <div className="content-area">
          <h1>대시보드</h1>
          <div className="dashboard-grid">
            <CourseProgress />
            <Calendar />
            <SettingList />
            <MessageBox />
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
