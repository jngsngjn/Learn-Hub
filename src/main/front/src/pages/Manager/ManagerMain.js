import React from "react";
import { Route, Routes } from "react-router-dom";
import ManagerSideBar from "../../components/SideBar/ManagerSideBar";
import ManagerCalendar from "../../components/Calendar/ManagerCalendar/ManagerCalendar";
import CalendarDetail from "../../components/Calendar/ManagerCalendar/ManagerCalendarDetail";
import Lecture from "./Lecture"; // 경로 수정
import SettingList from "./Survey"; // 경로 수정
import MessageBox from "./MessageBox"; // 경로 수정
import StudentManagement from "./StudentManagement"; // 경로 수정
import CurriculumManagement from "./CurriculumManagement"; // 경로 수정
import TeacherManagement from "./TeacherManagement"; // 경로 수정
import Notice from "./Notice"; // 경로 수정
import StudentContact from "./StudentContact"; // 경로 수정
import TeacherContact from "./TeacherContact"; // 경로 수정
import CurriculumDetail from "./CurriculumDetail"; // 경로 수정
import "./ManagerMain.css";





function Dashboard() {
  return (
    <>
      <h1>대시보드</h1>
      <div className="dashboard-grid">
        <Lecture />
        <ManagerCalendar />
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
        <ManagerSideBar />
        <div className="content-area">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="manage-students" element={<StudentManagement />} />
            <Route
              path="manage-curriculums"
              element={<CurriculumManagement />}
            />
            <Route
              path="manage-curriculums/:id"
              element={<CurriculumDetail />}
            />
            <Route path="manage-teachers" element={<TeacherManagement />} />
            <Route path="notice" element={<Notice />} />
            <Route path="contact-students" element={<StudentContact />} />
            <Route path="contact-teachers" element={<TeacherContact />} />
            <Route path="calendar/:eventId" element={<CalendarDetail />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default ManagerMain;
