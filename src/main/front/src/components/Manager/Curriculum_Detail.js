import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import Calendar from './Calendar';
import './Curriculum_Detail.css';

const CurriculumDetail = () => {
  const { curriculumId } = useParams();
  const [curriculum, setCurriculum] = useState({
    name: '',
    th: '',
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
    if (!curriculumId) {
      console.error('Invalid curriculum ID');
      return;
    }

    const fetchData = async () => {
      try {
        const token = getToken();
        const config = {
          headers: { access: token },
        };

        console.log('Fetching curriculum details');
        const [basicResponse, attendanceResponse, teacherResponse, calendarResponse, surveyResponse] = await Promise.all([
          axios.get(`/managers/curriculum/${curriculumId}/basic`, config),
          axios.get(`/managers/curriculum/${curriculumId}/attendance`, config),
          axios.get(`/managers/curriculum/${curriculumId}/teacher`, config),
          axios.get(`/managers/curriculum/${curriculumId}/calendar`, config),
          axios.get(`/managers/curriculum/${curriculumId}/survey`, config),
        ]);

        console.log('Basic Response:', basicResponse.data);
        console.log('Attendance Response:', attendanceResponse.data);
        console.log('Teacher Response:', teacherResponse.data);
        console.log('Calendar Response:', calendarResponse.data);
        console.log('Survey Response:', surveyResponse.data);

        setCurriculum(basicResponse.data);
        setAttendance(attendanceResponse.data);
        setTeacher(teacherResponse.data);
        setSchedules(calendarResponse.data);
        setSurvey(surveyResponse.data);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchData();
  }, [curriculumId]);

  return (
    <div className="curriculum-detail">
      <div className="header">
        <h2>교육 과정</h2>
      </div>
      <div className="curriculum-detail-container">
        <div className="title-progress-bar">
          <h2>{curriculum.name} {curriculum.th}제목제목</h2>
          <div className="progress-container">
            <div className="progress-bar">
              <div className="progress" style={{ width: `${curriculum.progress}%` }}></div>
              <span className="progress-text">{curriculum.progress.toFixed(1)}% / 100%</span>
            </div>
          </div>
        </div>
        <div className="content-container">
          <div className="left-container">
            <div className="info-boxes-top">
              <div className="info-box">
                <div className="detail-attendance-title">
                <span className="detail-subtitle">학생 출결 현황</span>
                <Link to={`/attendance/${curriculumId}`} className="detail-link">자세히 보기 ></Link>
                </div>
                <p>{attendance.attendance} / {attendance.total}</p>
                <p>{attendance.ratio}%</p>
              </div>
              <div className="info-box">
                <div className="detail-teacher-title">
                <span>강사 정보</span>
                <Link to={`/teacher/${curriculumId}`} className="detail-link">자세히 보기 ></Link>
                </div>
                <p>{teacher.name}</p>
                <p>{teacher.email}</p>
                <p>{teacher.phone}</p>

              </div>
            </div>
            <div className="info-box survey-box">
              <div className="detail-survey-title">
              <span>설문 조사</span>
              <Link to={`/survey/${curriculumId}`} className="detail-link">자세히 보기 ></Link>
              </div>
              <p>{survey.title}</p>
              <p>{survey.completed} / {survey.total}</p>
            </div>
          </div>
          <div className="right-container">
            <span className="curriculum-detail-calendar">캘린더</span>
            <Calendar events={schedules} />
          </div>
        </div>
        <div className="detail-update-button-container">
         <button className="curriculum-detail-update-button">교육 과정 수정</button>
        </div>
      </div>
    </div>
  );
};

export default CurriculumDetail;