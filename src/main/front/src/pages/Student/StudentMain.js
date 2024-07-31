import React from "react";
import "./StudentMain.css";
import RandomVideo from "../../components/Lectures/RandomVideo";
import LectureVideo from "../../components/Lectures/LectureVideo";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import useGetFetch from "../../hooks/useGetFetch";
import {
  CircularProgressbarWithChildren,
  CircularProgressbar,
  buildStyles,
} from "react-circular-progressbar";
import "react-circular-progressbar/dist/styles.css";
import { useNavigate } from "react-router-dom";

const StudentMain = () => {
  const navigate = useNavigate();

  const { data: recentLecture, error: recentLectureError } = useGetFetch(
    "/data/student/recentLecture.json",
    ""
  );

  const { data: question, error: questionError } = useGetFetch(
    "/data/student/question.json",
    []
  );

  const { data: subject, error: subjectError } = useGetFetch(
    "/data/student/subject.json",
    []
  );

  const { data: badge, error: badgeError } = useGetFetch(
    "/data/student/badge.json",
    []
  );

  const { data: adminNotice, error: adminNoticeError } = useGetFetch(
    "/data/student/adminNotice.json",
    []
  );

  const { data: teacherNotice, error: teacherNoticeError } = useGetFetch(
    "/data/student/teacherNotice.json",
    []
  );

  if (
    recentLectureError ||
    questionError ||
    subjectError ||
    badgeError ||
    adminNoticeError ||
    teacherNoticeError
  ) {
    return <div>Error loading data</div>;
  }

  //임시 이동 페이지 - hook 분리? 임시 강의페이지
  const goToPage = async () => {
    const token = localStorage.getItem("access");

    if (token) {
      const response = await fetch("/data/student/stu", {
        // method: "GET",
        // headers: {
        //   Authorization: `Bearer ${token}`,
        // },
      });

      if (response.ok) {
        navigate("/경로");
      } else {
        console.error("Failed to fetch");
      }
    } else {
      console.error("cannot find token");
    }
  };

  return (
    <div className="page_body">
      <div className="side_bar">
        <h3>옆 카테고리 컴포넌트 자리</h3>
      </div>
      <div className="main_container">
        <h1 className="page_title">대시보드</h1>
        <div className="divide_right_container">
          <div className="left_container">
            <div className="recent_lecture_container">
              <div className="title_box">
                <h3 className="components_title" onClick={goToPage}>
                  최근 학습 강의
                </h3>
                <span className="go_to_main_lecture_page show-more-button">
                  학습 목록 ⟩
                </span>
              </div>
              <div className="recent_contents_box">
                <h3 className="recent_lecture_type">{recentLecture.subject}</h3>
                <div className="recent_video_box">
                  <i className="bi bi-play-btn play_recent_video_icon"></i>
                  <p className="recent_video_title">{recentLecture.title}</p>
                  <div className="progress_bar">
                    <div className="progress_container">
                      <CircularProgressbar
                        value={recentLecture.progress}
                        text={`${recentLecture.progress}%`}
                        styles={buildStyles({
                          textColor: "#74b49b",
                          pathColor: "#A7D7C5",
                          trailColor: "#F4F9F4",
                          strokeLinecap: "butt",
                          textSize: "30px",
                        })}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="video_container">
              <h3 className="components_title">오늘의 IT</h3>
              <div className="random_video_box">{/* <RandomVideo /> */}</div>
              <h3 className="components_title">보충 강의</h3>
              <div className="lecture_videohttp://localhost:3000/_box">
                {/* <LectureVideo /> */}
              </div>
            </div>
            <div className="question_container">
              <div className="title_box">
                <h3 className="components_title">질문사항</h3>
                <span className="go_to_subject_page show-more-button">
                  더보기 ⟩
                </span>
              </div>
              <div className="question_list_container">
                {question?.map((el, idx) => (
                  <div className="question_list" key={idx}>
                    <div className="question_type_box">
                      <span className="question_type_tag">질문</span>
                      <span className="question_type_nage">{el.type}</span>
                      <span className="recomment_button">답글 달기⟩</span>
                    </div>
                    <div className="question_box">
                      <div className="qusetion_subject_name">
                        {el.lectureName}
                      </div>
                      <p className="question_title">{el.questionTitle}</p>
                      <div className="question_icon_box">
                        <i className="bi bi-eye watch_icon"></i>
                        {el.watchCount}
                        <i className="bi bi-chat comment_icon"></i>
                        {el.commentCount}
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </div>
            {/* 배지 컨테이너 */}
            <div className="badge_container">
              <div className="title_box">
                <h3 className="components_title">배지</h3>
                <span className="go_to_badge_page show-more-button">
                  더보기 ⟩
                </span>
              </div>
              <div className="badge_list_box">
                {badge.map((el, idx) => (
                  <div className="badge_list" key={idx}>
                    <img
                      src={el.image_path}
                      alt="badge"
                      className="badge_image"
                    />
                  </div>
                ))}
              </div>
            </div>
          </div>
          {/* 오른쪽 메인 부분 */}
          <div className="right_container">
            <div className="calander-container">
              <h3 className="components_title">캘린더</h3>
              <div className="calander"> {/* 캘린더 컴포넌트 */}</div>
            </div>
            <div className="subject_container">
              <div className="title_box">
                <h3 className="components_title"> 과제 목록</h3>
                <span>더보기</span>
              </div>
              <div className="subject_list_container">
                {subject.map((el, idx) => (
                  <div className="list_box" key={idx}>
                    <h3>과제</h3>
                    <h4 className="subject_name">{el.title}</h4>
                    <p className="subject_description">{el.content}</p>
                    <button className="subject_submit_button">제출하기</button>
                    {/* subject response의 timeline이 뭔지 모르겠음 물어보기 */}
                    {/* <div className="show_subject_complete">
                      {subject.timeout.some((el) => el.status === "true")
                        ? "제출 여부 ✅"
                        : "제출 여부 ❌"}
                    </div> */}
                    <div className="addtional_info_box">
                      <p className="subejct_deadline">
                        ~ {el.timeout[0]?.timeline}
                      </p>
                      <p className="go_to_subject_page">자세히 보기 ⟩</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>
            <div className="notice_container">
              <div className="admin_notice_container">
                <h3 className="notice_components_title">관리자 공지사항</h3>
                {adminNotice.map((el, idx) => (
                  <div className="notice_list" key={idx}>
                    <div className={`notice_type ${el.type}_notice`}>
                      {el.type === "alert" ? "긴급" : "공지"}
                    </div>
                    <div className="notice_title">{el.title}</div>
                    <span className="notice_date">{el.writeDate}</span>
                  </div>
                ))}
                <span className="go_to_admin_notice_page show-more-button">
                  자세히 보기 ⟩
                </span>
              </div>
              <div className="teacher_notice_container">
                <h3 className="notice_components_title">강사 공지사항</h3>
                {teacherNotice.map((notice) => (
                  <div key={notice.id} className="notice_list">
                    <div className={`notice_type ${notice.type}_notice`}>
                      {notice.type === "alert" ? "긴급" : "공지"}
                    </div>
                    <div className="notice_title">{notice.title}</div>
                    <span className="notice_date">{notice.writeDate}</span>
                  </div>
                ))}
                <span className="go_to_teacher_notice_page show-more-button">
                  자세히 보기 ⟩
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentMain;
