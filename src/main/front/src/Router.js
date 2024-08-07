import React from "react";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import Nav from "./components/Nav/Nav";
import Footer from "./components/Footer/Footer";
import Main from "./pages/Main/Main";
import Login from "./components/Login/Login";
import Signup from "./components/Login/Register";
import LoginEmail from "./components/Login/Login_email";
import ManagerMain from "./pages/Manager/ManagerMain";
import ViewBoardImage from "./components/Test/ViewBoardImage";
import ImageBoard from "./components/Editor/ImageBoard";
import StudentMain from "./pages/Student/StudentMain";

const Router = () => {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" element={<Navigate replace to="/login" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/email" element={<LoginEmail />} />
        <Route path="/main" element={<Main />} />
        <Route path="/managers/*" element={<ManagerMain />} />
        <Route path="/students/*" element={<StudentMain />} />
        {/*
        <Route path="/students/:subject/board" element={<StudentLecture />} />
        <Route path="/students/lectureLists" element={<StudentLectureList />} />
        <Route
          path="/students/:subject/lectures/:id"
          element={<StudentLectureDetail />}
        />
        <Route
          path="/students/subjectBoard"
          element={<StudentSubjectBoard />}
        />
        <Route
          path="/Students/SubjectBoardDetail/:id"
          element={<StudentSubjectBoardDetail />}
        />
        <Route
          path="/students/inquiryBoard"
          element={<StudentInquiryBoard />}
        />
        <Route
          path="/students/inquiryDetail/:id"
          element={<StudentInquiryDetail />}
        />
        <Route path="/students/assignment" element={<StudentAssignment />} />
        <Route
          path="/students/assignmentDetail/:id"
          element={<StudentAssignmentDetail />}
        /> */}
        <Route path="/image-board" element={<ImageBoard />} />
        <Route path="/test-view" element={<ViewBoardImage />} />
        <Route path="/teacher/*" element={<TeacherMain/>}/>

      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
