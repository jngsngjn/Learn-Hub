import React, { useState } from "react";
import { useNavigate, Routes, Route } from "react-router-dom";
import "./StudentDashBoard.css";
import StudentLecture from "./StudentLecture";
import StudentAssignment from "./StudentAssignment";
import useGetFetch from "../../hooks/useGetFetch";
import StudentLectureList from "./StudentLectureList";
import StudentFreeBoard from "./StudentFreeBoard";
import StudentQuestionBoard from "./StudentQuestionBoard";
import StudentDashBoard from "./StudentDashBoard";
import StudentSubjectBoardList from "./StudentSubjectBoardList";
import StudentSubjectBoardDetail from "./StudentSubjectBoardDetail";
import StudentLectureDetail from "./StudentLectureDetail";
import StudentAssignmentDetail from "./StudentAssignmentDetail";
import StudentSideBar from "../../components/SideBar/StudentSideBar";
import StudentHeader from "../../components/Nav/StudentHeader";

const StudentMain = () => {
  const [showSection, setShowSection] = useState(null);
  const [selectedSubject, setSelectedSubject] = useState(null);
  const navigate = useNavigate();

  const toggleOpen = (section) => {
    setShowSection(showSection === section ? null : section);
  };

  const handleSubjectClick = (subjectName) => {
    setSelectedSubject(subjectName);
    navigate(`/students/${subjectName}/board`);
  };

  const handleSectionClick = (section) => {
    navigate(`/students/${section}`);
    setShowSection(null);
  };

  const { data: subject, error: subjectError } = useGetFetch(
    "/data/student/mainpage/sidebar.json",
    []
  );

  return (
    <div className="student_dashboard_body" id="container">
      <StudentHeader />
      <StudentSideBar />
      <div className="contents">
        <Routes>
          <Route path="" element={<StudentDashBoard />} />
          <Route
            path=":subjectName/board"
            element={<StudentLecture subject={selectedSubject} />}
          />
          <Route path="assignment" element={<StudentAssignment />} />
          <Route
            path="/:subjectName/boardList"
            element={<StudentSubjectBoardList />}
          />
          <Route
            path="/:subjectName/boardDetail/:id"
            element={<StudentSubjectBoardDetail />}
          />
          <Route path="/lectureLists" element={<StudentLectureList />} />
          <Route
            path="/:subjectName/lectures/:id"
            element={<StudentLectureDetail />}
          />
          <Route path="/freeBoard" element={<StudentFreeBoard />} />
          <Route path="/questionBoard" element={<StudentQuestionBoard />} />
          <Route
            path="/assignmentDetail/:id"
            element={<StudentAssignmentDetail />}
          />
          {/* 현재 임시로 선생님 과제 상세 페이지 -> 아래의 페이지가 강사가 봐야할 학생들의 과제제출 페이지 넣을 예정 */}
          {/* 언젠가 들어올 강사 공지사항 페이지 */}
          {/* <Route path="/teacherNotice" element={< />} /> */}
          {/* 언젠가 들어올 매니저 공지사항 페이지 */}
          {/* <Route path="/teacherNotice" element={< />} /> */}
          {/* 언젠가 들어올 투표 페이지 */}
          {/* <Route path="/teacherNotice" element={< />} /> */}
        </Routes>
      </div>
    </div>
  );
};

export default StudentMain;
