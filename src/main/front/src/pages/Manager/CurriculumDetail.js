import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import axios from "../../utils/axios";
import { CircularProgressbar } from "react-circular-progressbar";
import "react-circular-progressbar/dist/styles.css";
import ManagerCalendar from "../../components/Calendar/ManagerCalendar/ManagerCalendar";
import "./CurriculumDetail.css";

const CurriculumDetail = () => {
  const { id } = useParams();
  const [curriculum, setCurriculum] = useState({
    name: "",
    th: 0,
    progress: 0,
  });
  const [attendance, setAttendance] = useState({
    attendance: 0,
    total: 0,
    ratio: 0,
  });
  const [teacher, setTeacher] = useState(null); // 초기 상태를 null로 설정
  const [schedules, setSchedules] = useState([]);
  const [survey, setSurvey] = useState({
    title: "",
    th: "",
    completed: 0,
    total: 0,
  });
  const [isWeekend, setIsWeekend] = useState(false);

  const getToken = () => localStorage.getItem("access-token");

  useEffect(() => {
    if (!id) {
      console.error("잘못된 커리큘럼 ID입니다.");
      return;
    }

    const fetchData = async () => {
      try {
        const token = getToken();
        const config = {
          headers: { access: token },
        };

        const basicResponse = await axios.get(
          `/managers/curriculum/${id}/basic`,
          config
        );
        setCurriculum(basicResponse.data);

        try {
          const attendanceResponse = await axios.get(
            `/managers/curriculum/${id}/attendance`,
            config
          );
          setAttendance(attendanceResponse.data);
          setIsWeekend(false);
        } catch (error) {
          if (error.response && error.response.status === 409) {
            console.log("주말에는 출석 정보를 조회할 수 없습니다.");
            setAttendance({ attendance: 0, total: 0, ratio: 0 });
            setIsWeekend(true);
          } else {
            console.error("출석 정보 조회 오류:", error);
          }
        }

        try {
          const teacherResponse = await axios.get(
            `/managers/curriculum/${id}/teacher`,
            config
          );
          // 강사 정보가 제대로 있다면, 상태를 업데이트하고, 그렇지 않으면 null로 설정
          if (teacherResponse.data && teacherResponse.data.name) {
            setTeacher(teacherResponse.data);
          } else {
            setTeacher(null);
          }
        } catch (error) {
          console.error("강사 정보 조회 오류:", error);
          setTeacher(null); // 오류 발생 시 강사 정보를 null로 설정
        }

        const calendarResponse = await axios.get(
          `/managers/curriculum/${id}/calendar`,
          config
        );
        setSchedules(calendarResponse.data);
      } catch (error) {
        console.error("응답 오류:", error.response);
      }
    };

    fetchData();
  }, [id]);

  return (
    <div className="curriculum-detail">
      <div className="curriculum-detail-header">
        <h2 className="curriculum-detail-title">교육 과정</h2>
      </div>
      <div className="curriculum-detail-container">
        <div className="curriculum-detail-title-progress-bar">
          <h2 className="curriculum-detail-title">
            {curriculum.name} {curriculum.th}기
          </h2>
          <div className="curriculum-detail-progress-container">
            <span className="curriculum-detail-progress-title">과정 현황</span>
            <div className="curriculum-detail-progress-bar">
              <div
                className="curriculum-detail-progress"
                style={{ width: `${curriculum.progress}%` }}
              ></div>
            </div>
            <span className="curriculum-detail-progress-text">
              {curriculum.progress?.toFixed(1)} / 100%
            </span>
          </div>
        </div>
        <div className="curriculum-detail-content-container">
          <div className="curriculum-detail-left-container">
            <div className="curriculum-detail-info-box">
              <div className="curriculum-detail-info-box-title">
                <span className="curriculum-detail-subtitle">학생 출결 현황</span>
              </div>
              <div className="curriculum-detail-info-box-content">
                {isWeekend ? (
                  <p>휴무일입니다</p>
                ) : (
                  <div className="curriculum-detail-circular-progress">
                    <p className="curriculum-detail-attendance-count">
                      <i className="fas fa-user-graduate"></i>
                      {attendance.attendance} / {attendance.total}
                    </p>
                    <CircularProgressbar
                      value={attendance.ratio}
                      text={`${attendance.ratio}%`}
                    />
                  </div>
                )}
              </div>
              <Link
                to={`/attendance/${id}`}
                className="curriculum-detail-link attendance-link"
              >
                자세히 보기{" "}
              </Link>
            </div>
            <div className="curriculum-detail-info-box">
              <div className="curriculum-detail-info-box-title">
                <span className="curriculum-detail-subtitle">강사 정보</span>
              </div>
              <div className="curriculum-detail-info-box-content-second">
                {teacher ? (
                  <>
                    <p>
                      <i className="fas fa-user"></i> {teacher.name}
                    </p>
                    <p>
                      <i className="fas fa-envelope"></i> {teacher.email}
                    </p>
                    <p>
                      <i className="fas fa-phone"></i> {teacher.phone}
                    </p>
                  </>
                ) : (
                  <p>강사 정보가 없습니다.</p>
                )}
              </div>
              {teacher && (
                <Link
                  to={`/teacher/${id}`}
                  className="curriculum-detail-link teacher-link"
                >
                  자세히 보기{" "}
                </Link>
              )}
            </div>
            <div className="curriculum-detail-info-box curriculum-detail-survey-box">
              <div className="curriculum-detail-survey-header">
                <span className="curriculum-detail-subtitle">설문 조사</span>
                <Link to={`/survey/${id}`} className="survey-link">
                  자세히 보기{" "}
                </Link>
              </div>
              {survey.title ? (
                <div className="curriculum-detail-survey-content">
                  <div className="curriculum-detail-survey-info">
                    <span className="curriculum-detail-survey-th">
                      {curriculum.th}기
                    </span>
                    <span className="curriculum-detail-survey-count">
                      {survey.completed} / {survey.total}
                    </span>
                  </div>
                  <p className="curriculum-detail-survey-name">
                    {survey.title}
                  </p>
                  <div className="curriculum-detail-survey-status">
                    <span className="curriculum-detail-survey-status-text">
                      진행 중
                    </span>
                    <button className="curriculum-detail-survey-button">
                      설문 마감
                    </button>
                  </div>
                </div>
              ) : (
                <div className="curriculum-detail-survey-content curriculum-detail-no-survey">
                  <p>진행중인 설문 조사가 없습니다.</p>
                  <button className="curriculum-detail-survey-button">
                    설문 등록
                  </button>
                </div>
              )}
            </div>
          </div>
          <div className="curriculum-detail-right-container">
            <span className="curriculum-detail-calendar-title">캘린더</span>
            <ManagerCalendar events={schedules} />
          </div>
        </div>
        <div className="curriculum-detail-update-button-container">
          <button className="curriculum-detail-update-button">
            교육 과정 수정
          </button>
        </div>
      </div>
    </div>
  );
};

export default CurriculumDetail;
