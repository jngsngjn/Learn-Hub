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
      <div className="sidebar">
        <div className="profile_box">프로필 컴포넌트가 들어올 자리</div>
        <h3 className="sidebar_title" onClick={() => handleSectionClick("/")}>
          대쉬보드
        </h3>
        <ul className="student_dashboard_list_container" id="inner_box">
          <ul
            className={`student_dashboard_section ${
              showSection === "subject" ? "show" : ""
            }`}
            onClick={() => toggleOpen("subject")}
          >
            과목
            <i
              className={`bi ${
                showSection === "subject"
                  ? "bi-caret-up-fill"
                  : "bi-caret-down-fill"
              } stu_sidebar_toogle_btn`}
            ></i>
            {subject.subject?.map((el, idx) => (
              <li key={idx} onClick={() => handleSubjectClick(el.name)}>
                {el.name}
              </li>
            ))}
          </ul>
          <ul
            className={`student_dashboard_section ${
              showSection === "assignment" ? "show" : ""
            }`}
            onClick={() => handleSectionClick("assignment")}
          >
            과제
          </ul>
          <ul
            className={`student_dashboard_section ${
              showSection === "lecture" ? "show" : ""
            }`}
            onClick={() => handleSectionClick("lectureLists")}
          >
            강의
          </ul>
          <ul
            className={`student_dashboard_section ${
              showSection === "board" ? "show" : ""
            }`}
            onClick={() => toggleOpen("board")}
          >
            게시판
            <i
              className={`bi ${
                showSection === "board"
                  ? "bi-caret-up-fill"
                  : "bi-caret-down-fill"
              } stu_sidebar_toogle_btn`}
            ></i>
            <li onClick={() => handleSectionClick("freeBoard")}>자유 게시판</li>
            <li onClick={() => handleSectionClick("questionBoard")}>
              질문 게시판
            </li>
          </ul>
          <ul
            className={`student_dashboard_section ${
              showSection === "notice" ? "show" : ""
            }`}
            onClick={() => toggleOpen("notice")}
          >
            공지사항
            <i
              className={`bi ${
                showSection === "notice"
                  ? "bi-caret-up-fill"
                  : "bi-caret-down-fill"
              } stu_sidebar_toogle_btn`}
            ></i>
            <li onClick={() => handleSectionClick("teacherNotice")}>
              강사 공지사항
            </li>
            <li onClick={() => handleSectionClick("managerNotice")}>
              매니저 공지사항
            </li>
          </ul>
          <ul
            className={`student_dashboard_section ${
              showSection === "question" ? "show" : ""
            }`}
            onClick={() => toggleOpen("question")}
          >
            문의
            <i
              className={`bi ${
                showSection === "question"
                  ? "bi-caret-up-fill"
                  : "bi-caret-down-fill"
              } stu_sidebar_toogle_btn`}
            ></i>
            <li onClick={() => handleSectionClick("teacherQuestion")}>
              강사 문의
            </li>
            <li onClick={() => handleSectionClick("managerQuestion")}>
              매니저 문의
            </li>
          </ul>
          <ul
            className={`student_dashboard_section ${
              showSection === "vote" ? "show" : ""
            }`}
            onClick={() => handleSectionClick("vote")}
          >
            투표
          </ul>
        </ul>
      </div>
      <div className="contents">
        <Routes>
          <Route path="/" element={<StudentDashBoard />} />
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
          {/* <Route path="/teacherNotice" element={<TeacherAssignmentDetail />} /> */}
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
