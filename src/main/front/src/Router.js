import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav/Nav";
import Footer from "./components/Footer/Footer";
import Main from "./pages/Main/Main";
import StudentLecture from "./pages/Student/StudentLecture";
import StudentInquiry from "./pages/Student/StudentInquiry";
import StudentSubject from "./pages/Student/StudentSubject";

const Router = () => {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/students/lecture" element={<StudentLecture />} />
        <Route path="/students/inquiry" element={<StudentInquiry />} />
        <Route path="/students/subject" element={<StudentSubject />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
