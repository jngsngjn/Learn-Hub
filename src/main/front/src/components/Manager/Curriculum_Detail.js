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
        <h1>교육 과정</h1>
      </div>
      <div className="curriculum-container">
        <div className="left-container">
          <div className="progress-container">
            <div className="progress-bar">
              <div className="progress" style={{ width: `${curriculum.progress}%` }}></div>
              <span className="progress-text">{curriculum.progress.toFixed(1)}% / 100%</span>
            </div>
          </div>

          <div className="info-box">
            <h2>학생 출결 현황</h2>
            <p>{attendance.attendance} / {attendance.total}</p>
            <p>{attendance.ratio}%</p>
            <Link to={`/attendance/${curriculumId}`} className="detail-link">자세히 보기</Link>
          </div>

          <div className="info-box">
            <h2>강사 정보</h2>
            <p>{teacher.name}</p>
            <p>{teacher.email}</p>
            <p>{teacher.phone}</p>
            <Link to={`/teacher/${curriculumId}`} className="detail-link">자세히 보기</Link>
          </div>

          <div className="info-box survey-box">
            <h2>설문 조사</h2>
            <p>{survey.title}</p>
            <p>{survey.completed} / {survey.total}</p>
            <Link to={`/survey/${curriculumId}`} className="detail-link">자세히 보기</Link>
          </div>
        </div>

        <div className="right-container">
          <div className="calendar-box">
            <h2>캘린더</h2>
            <Calendar events={schedules} />
          </div>
        </div>
      </div>
      <button className="update-button">교육 과정 수정</button>
    </div>
  );
};

export default CurriculumDetail;
