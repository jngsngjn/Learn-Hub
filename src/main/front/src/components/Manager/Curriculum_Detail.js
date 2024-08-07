import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import { CircularProgressbar } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';
import Calendar from './Calendar';
import './Curriculum_Detail.css';

const CurriculumDetail = () => {
  const { id } = useParams();
  const [curriculum, setCurriculum] = useState({
    name: '',
    th: 0,
    progress: 0,
  });
  const [attendance, setAttendance] = useState({
    attendance: 0,
    total: 0,
    ratio: 0,
  });
  const [teacher, setTeacher] = useState({
    name: '',
    email: '',
    phone: '',
  });
  const [schedules, setSchedules] = useState([]);
  const [survey, setSurvey] = useState({
    title: '',
    th: '',
    completed: 0,
    total: 0,
  });

  const getToken = () => localStorage.getItem('access-token');

  useEffect(() => {
    if (!id) {
      console.error('Invalid curriculum ID');
      return;
    }

    const fetchData = async () => {
      try {
        const token = getToken();
        console.log('Token:', token);
        const config = {
          headers: { access: token },
        };

        console.log('ID 값:', id);

        const basicResponse = await axios.get(`/managers/curriculum/${id}/basic`, config);
        console.log('Basic:', basicResponse);
        setCurriculum(basicResponse.data);

        const attendanceResponse = await axios.get(`/managers/curriculum/${id}/attendance`, config);
        console.log('출석:', attendanceResponse);
        setAttendance(attendanceResponse.data);

        const teacherResponse = await axios.get(`/managers/curriculum/${id}/teacher`, config);
        console.log('강사:', teacherResponse);
        setTeacher(teacherResponse.data);

        const calendarResponse = await axios.get(`/managers/curriculum/${id}/calendar`, config);
        console.log('달력:', calendarResponse);
        setSchedules(calendarResponse.data);

        // survey는 없기 때문에 주석처리
        // const surveyResponse = await axios.get(`/managers/curriculum/${id}/survey`, config);
        // console.log('설문조사 Response:', surveyResponse);
        // setSurvey(surveyResponse.data);

      } catch (error) {
        console.error('response 오류:', error.response);
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
          <h2 className="curriculum-detail-title">{curriculum.name} {curriculum.th}기</h2>
          <div className="curriculum-detail-progress-container">
            <span className="curriculum-detail-progress-title">과정 현황</span>
            <div className="curriculum-detail-progress-bar">
              <div className="curriculum-detail-progress" style={{ width: `${curriculum.progress}%` }}></div>
            </div>
            <span className="curriculum-detail-progress-text">{curriculum.progress?.toFixed(1)}% / 100%</span>
          </div>
        </div>
        <div className="curriculum-detail-content-container">
          <div className="curriculum-detail-left-container">
            <div className="curriculum-detail-info-box">
              <div className="curriculum-detail-info-box-title">
                <span className="curriculum-detail-subtitle">학생 출결 현황</span>
                <Link to={`/attendance/${id}`} className="curriculum-detail-link">자세히 보기 ></Link>
              </div>
              <div className="curriculum-detail-info-box-content">
                <div className="curriculum-detail-circular-progress">
                <p className="curriculum-detail-attendance-count"><i className="fas fa-user-graduate"></i>{attendance.attendance} / {attendance.total}</p>
                  <CircularProgressbar value={attendance.ratio} text={`${attendance.ratio}%`} />
                </div>
              </div>
            </div>
            <div className="curriculum-detail-info-box">
              <div className="curriculum-detail-info-box-title">
                <span className="curriculum-detail-subtitle">강사 정보</span>
                <Link to={`/teacher/${id}`} className="curriculum-detail-link">자세히 보기 ></Link>
              </div>
              <div className="curriculum-detail-info-box-content-second">
                <p><i className="fas fa-user"></i> {teacher.name}</p>
                <p><i className="fas fa-envelope"></i> {teacher.email}</p>
                <p><i className="fas fa-phone"></i> {teacher.phone}</p>
              </div>
            </div>
            <div className="curriculum-detail-info-box curriculum-detail-survey-box">
              <div className="curriculum-detail-survey-header">
                <span className="curriculum-detail-subtitle">설문 조사</span>
                <Link to={`/survey/${id}`} className="curriculum-detail-link">자세히 보기 ></Link>
              </div>
              {survey.title ? (
                <div className="curriculum-detail-survey-content">
                  <div className="curriculum-detail-survey-info">
                    <span className="curriculum-detail-survey-th">{curriculum.th}기</span>
                    <span className="curriculum-detail-survey-count">{survey.completed} / {survey.total}</span>
                  </div>
                  <p className="curriculum-detail-survey-name">{survey.title}</p>
                  <div className="curriculum-detail-survey-status">
                    <span className="curriculum-detail-survey-status-text">진행 중</span>
                    <button className="curriculum-detail-survey-button">설문 마감</button>
                  </div>
                </div>
              ) : (
                <div className="curriculum-detail-survey-content curriculum-detail-no-survey">
                  설문 조사가 없습니다.
                  <button className="curriculum-detail-survey-button">설문 등록</button>
                </div>
              )}
            </div>
          </div>
          <div className="curriculum-detail-right-container">
            <span className="curriculum-detail-calendar-title">캘린더</span>
            <Calendar events={schedules} />
          </div>
        </div>
        <div className="curriculum-detail-update-button-container">
          <button className="curriculum-detail-update-button">교육 과정 수정</button>
        </div>
      </div>
    </div>
  );
};

export default CurriculumDetail;
