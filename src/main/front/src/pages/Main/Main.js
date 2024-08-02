import { jwtDecode } from "jwt-decode";
import "./Main.css";
import StudentMain from "../Student/StudentMain";
import TeacherMain from "../Teacher/TeacherMain";
import ManagerMain from "../Manager/ManagerMain";

// 초기설정

const Main = () => {
  //응답갑 요청해서 받아온 token에서 role을 꺼내오기.
  const token =
    "eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6InJlZnJlc2giLCJ1c2VybmFtZSI6Im1hbmFnZXIiLCJyb2xlIjoiUk9MRV9NQU5BR0VSIiwiaWF0IjoxNzIyMjE0MjY4LCJleHAiOjE3MjIzMDA2Njh9.-Lla7-m2w2ZSSUM-dfUkKPQNfRkGJEj34r_2UuoeMv";

  const decodedToken = jwtDecode(token);
  console.log(decodedToken);
  // const userRole = decodedToken.role;
  const userRole = "ROLE_STUDENT";

  console.log(userRole);

  const roleComponent =
    userRole === "ROLE_STUDENT" ? (
      <StudentMain />
    ) : userRole === "ROLE_TEACHER" ? (
      <TeacherMain />
    ) : userRole === "ROLE_MANAGER" ? (
      <ManagerMain />
    ) : (
      <div>비로그인 시 보여줄 페이지</div>
    );

  return <div className="main-body">{roleComponent}</div>;
};

export default Main;