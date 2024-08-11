import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import axios from "../../utils/axios";
import { CircularProgressbar } from "react-circular-progressbar";
import "react-circular-progressbar/dist/styles.css";
import ManagerCalendar from "../../components/Calendar/ManagerCalendar/ManagerCalendar";
import { CirclePicker } from "react-color";
import "./CurriculumDetail.css";
import swal from "sweetalert";

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
  const [teacher, setTeacher] = useState(null);
  const [schedules, setSchedules] = useState([]);
  const [survey, setSurvey] = useState({
    title: "",
    th: "",
    completed: 0,
    total: 0,
  });
  const [isWeekend, setIsWeekend] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isColorPickerOpen, setIsColorPickerOpen] = useState(false);
  const [updatedCurriculum, setUpdatedCurriculum] = useState({
    teacherId: "",
    startDate: "",
    endDate: "",
    color: "",
  });

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
       setUpdatedCurriculum({
         teacherId: basicResponse.data.teacherId || "",
         startDate: basicResponse.data.startDate || "",
         endDate: basicResponse.data.endDate || "",
         color: basicResponse.data.color || "",
       });

       // 강사 정보를 먼저 불러오기
       try {
         const teacherResponse = await axios.get(
           `/managers/curriculum/${id}/teacher`,
           config
         );
         if (teacherResponse.data && teacherResponse.data.name) {
           setTeacher(teacherResponse.data);
         } else {
           setTeacher(null);
         }
       } catch (error) {
         console.error("강사 정보 조회 오류:", error);
         setTeacher(null);
       }

       // 출결 정보를 그다음에 불러오기
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


  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUpdatedCurriculum({ ...updatedCurriculum, [name]: value });
  };

  const handleColorChange = (color) => {
    setUpdatedCurriculum({ ...updatedCurriculum, color: color.hex });
    setIsColorPickerOpen(false);
  };

  const handleUpdateCurriculum = async () => {
    try {
      const token = getToken();
      const response = await axios.patch(
        `/managers/manage-curriculums/${id}`,
        updatedCurriculum,
        {
          headers: {
            "Content-Type": "application/json",
            access: token,
          },
        }
      );

      if (response.status === 200) {
        setIsModalOpen(false);
        swal("수정 성공", "교육 과정이 성공적으로 수정되었습니다.", "success");
        // Refresh the curriculum data
        const updatedCurriculumResponse = await axios.get(
          `/managers/curriculum/${id}/basic`,
          {
            headers: { access: token },
          }
        );
        setCurriculum(updatedCurriculumResponse.data);
      } else {
        swal("수정 실패", "교육 과정 수정에 실패했습니다. 다시 시도해주세요.", "error");
      }
    } catch (error) {
      console.error("교육 과정 수정 중 오류 발생:", error);
      swal("수정 실패", "교육 과정 수정 중 오류가 발생했습니다. 다시 시도해주세요.", "error");
    }
  };

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
                {teacher && teacher.name ? (
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
              {teacher && teacher.name && (
                <Link to={`/teacher/${id}`} className="curriculum-detail-link teacher-link">
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
          <button
            className="curriculum-detail-update-button"
            onClick={() => setIsModalOpen(true)}
          >
            교육 과정 수정
          </button>
        </div>
      </div>

      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <button
              className="modal-close"
              onClick={() => setIsModalOpen(false)}
            >
              ×
            </button>
            <span className="curriculum-submit">교육 과정 수정</span>
            <div className="curriculum-input-group">
              <label>시작일</label>
              <input
                className="curriculum-start-date-input"
                type="date"
                name="startDate"
                value={updatedCurriculum.startDate}
                onChange={handleInputChange}
              />
            </div>
            <div className="curriculum-input-group">
              <label>종료일</label>
              <input
                className="curriculum-end-date-input"
                type="date"
                name="endDate"
                value={updatedCurriculum.endDate}
                onChange={handleInputChange}
              />
            </div>
            <div className="curriculum-input-group">
              <label>기수 색상</label>
              <div className="color-input-wrapper">
                <input
                  className="color-input"
                  type="text"
                  name="color"
                  value={updatedCurriculum.color}
                  readOnly
                />
                <div
                  className="color-input-select"
                  onClick={() => setIsColorPickerOpen(true)}
                >
                  <div
                    className="color-box"
                    style={{ backgroundColor: updatedCurriculum.color }}
                  ></div>
                </div>
              </div>
            </div>
            {isColorPickerOpen && (
              <div
                className="color-picker-modal-overlay"
                onClick={() => setIsColorPickerOpen(false)}
              >
                <div
                  className="color-picker-modal-content"
                  onClick={(e) => e.stopPropagation()}
                >
                  <CirclePicker
                    color={updatedCurriculum.color}
                    onChangeComplete={handleColorChange}
                    colors={[
                      "#F3C41E", "#F58D11", "#B85B27", "#A90C57",
                      "#F45CE5", "#AE59F0", "#0A8735", "#6F961E",
                      "#19E308", "#1D1AA6", "#20CFF5", "#98B3E5",
                    ]}
                  />
                </div>
              </div>
            )}
            <div className="modal-actions">
              <button className="modal-button" onClick={handleUpdateCurriculum}>
                교육 과정 수정
              </button>
              <button
                className="modal-button"
                onClick={() => setIsModalOpen(false)}
              >
                수정 취소
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default CurriculumDetail;