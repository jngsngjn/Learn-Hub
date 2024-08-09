import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./DefaultHeader.css";
import { jwtDecode } from "jwt-decode";
import StudentHeader from "./StudentHeader";
import ManagerHeader from "./ManagerHeader";
import TeacherHeader from "./TeacherHeader";

const DefaultHeader = () => {
  const [token, setToken] = useState(localStorage.getItem("access-token"));
  const navigate = useNavigate();

  useEffect(() => {
    const handleStorageChange = () => {
      const updatedToken = localStorage.getItem("access-token");
      setToken(updatedToken);
    };

    window.addEventListener("storage", handleStorageChange);

    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  }, []);

  useEffect(() => {
    const updatedToken = localStorage.getItem("access-token");
    setToken(updatedToken);
  }, [token]);

  if (!token) {
    return (
      <header id="default_header">
        <div className="default_h-inner">
          <div className="default_h-left">
            <h1 className="default_h-logo">
              <a className="default_navbar-brand" href="/">
                <img
                  src="/images/logo/HomeLearn_Default_Header_Logo.png"
                  alt="로고"
                />
              </a>
            </h1>
          </div>
          <div className="default_h-right">
            <Link to="/login" className="default_h-login_btn">
              로그인
            </Link>
          </div>
        </div>
      </header>
    );
  }

  const decodedToken = jwtDecode(token);
  const userRole = decodedToken.role;

  const roleHeader =
    userRole === "ROLE_STUDENT" ? (
      <StudentHeader />
    ) : userRole === "ROLE_TEACHER" ? (
      <TeacherHeader />
    ) : userRole === "ROLE_MANAGER" ? (
      <ManagerHeader />
    ) : (
      <div>응 없어</div>
    );

  return <div>{roleHeader}</div>;
};

export default DefaultHeader;
