import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav/Nav";
import Footer from "./components/Footer/Footer";
import Main from "./pages/Main/Main";
import StudentLecture from "./pages/Student/StudentLecture";
import StudentInquiry from "./pages/Student/StudentInquiry";

const Router = () => {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/students/lecture/" element={<StudentLecture />} />
        <Route path="/students/inquriy" element={<StudentInquiry />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
