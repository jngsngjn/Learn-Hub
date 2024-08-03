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
import StudentLecture from "./pages/Student/StudentLecture";
import StudentAssignment from "./pages/Student/StudentAssignment";
import StudentSubjectBoard from "./pages/Student/StudentSubjectBoard";
import StudentInquiryBoard from "./pages/Student/StudentInquiryBoard";
import StudentAssignmentDetail from "./pages/Student/StudentAssignmentDetail";
import StudentInquiryDetail from "./pages/Student/StudentInquiryDetail";

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
        <Route path="/students" element={<StudentMain />} />
        <Route path="/students/lecture" element={<StudentLecture />} />
        <Route
          path="/students/subjectBoard"
          element={<StudentSubjectBoard />}
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
        />
        <Route path="/image-board" element={<ImageBoard />} />
        <Route path="/test-view" element={<ViewBoardImage />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
