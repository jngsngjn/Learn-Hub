import "./TeacherMain.css";
import Question from "../../components/Teacher/Question";
import Lecture_State from "../../components/Teacher/Lecture_State";
import Calendar from "../../components/Teacher/Calendar";
import SettingList from "../../components/Teacher/Today_It";
import Faq from "../../components/Teacher/Faq";
import Sidebar from "../../components/Manager/Sidebar";
import {Route, Routes} from "react-router-dom";
import StudentManagement from "../../components/Manager/Student_Management";
import CurriculumManagement from "../../components/Manager/Curriculum_Management";
import TeacherManagement from "../../components/Manager/Teacher_Management";
import Notice from "../../components/Manager/Notice";
import StudentContact from "../../components/Manager/Student_Contact";
import TeacherContact from "../../components/Manager/Teacher_Contact";
import React from "react";

function Dashboard() {
  return (
      <>
        <h1>대시보드</h1>
          <div className="teacher-dashboard-grid-container">
              <div className="teacher-dashboard-grid">
                  <Lecture_State/>

                  <Question/>

                  <Faq/>
              </div>
              <div className="teacher-dashboard-grid2">
                  <Calendar/>
                  <SettingList/>
              </div>
          </div>

      </>
  );
}

function TeacherMain() {
    return (
        <div className="teacher-App">
            <div className="teacher-main-content">
                <Sidebar/>
                <div className="teacher-content-area">
                    <Routes>
                        <Route path="/" element={<Dashboard/>} />
              <Route path="manage-students" element={<StudentManagement />} />
              <Route path="manage-curriculums" element={<CurriculumManagement />} />
              <Route path="manage-teachers" element={<TeacherManagement />} />
              <Route path="notice" element={<Notice />} />
              <Route path="contact-students" element={<StudentContact />} />
              <Route path="contact-teachers" element={<TeacherContact />} />
            </Routes>
          </div>
        </div>
      </div>
  );
}
export default TeacherMain;
