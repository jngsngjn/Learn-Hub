import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
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
      <div className="header">
        <h2>교육 과정</h2>
      </div>
      <div className="curriculum-detail-container">
        <div className="title-progress-bar">
          <h2>{curriculum.name} {curriculum.th}기</h2>
          <div className="progress-container">
            <div className="progress-bar">
              <div className="progress" style={{ width: `${curriculum.progress}%` }}></div>
            </div>
            <span className="progress-text">{curriculum.progress?.toFixed(1)}% / 100%</span>
          </div>
        </div>
        <div className="content-container">
          <div className="left-container">
            <div className="info-box">
              <div className="info-box-title">
                <i className="fas fa-user-graduate"></i>
                <span>학생 출결 현황</span>
                <Link to={`/attendance/${id}`} className="detail-link">자세히 보기 ></Link>
              </div>
              <div className="info-box-content">
                <p className="attendance-ratio">{attendance.ratio}%</p>
                <p className="attendance-count">{attendance.attendance} / {attendance.total}</p>
              </div>
            </div>
            <div className="info-box">
              <div className="info-box-title">
                <i className="fas fa-chalkboard-teacher"></i>
                <span>강사 정보</span>
                <Link to={`/teacher/${id}`} className="detail-link">자세히 보기 ></Link>
              </div>
              <div className="info-box-content">
                <p><i className="fas fa-user"></i> {teacher.name}</p>
                <p><i className="fas fa-envelope"></i> {teacher.email}</p>
                <p><i className="fas fa-phone"></i> {teacher.phone}</p>
              </div>
            </div>
            <div className="info-box survey-box">
              <div className="info-box-title">
                <i className="fas fa-poll"></i>
                <span>설문 조사</span>
                <Link to={`/survey/${id}`} className="detail-link">자세히 보기 ></Link>
              </div>
              <div className="info-box-content">
                <p className="survey-title">{survey.title}</p>
                <p className="survey-count"><i className="fas fa-users"></i> {survey.completed} / {survey.total}</p>
              </div>
            </div>
          </div>
          <div className="right-container">
            <span className="curriculum-detail-calendar">
              <i className="far fa-calendar-alt"></i> 캘린더
            </span>
            <Calendar events={schedules} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default CurriculumDetail;
