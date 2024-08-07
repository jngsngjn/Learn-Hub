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
        <Route path="/image-board" element={<ImageBoard />} />
        <Route path="/test-view" element={<ViewBoardImage />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
