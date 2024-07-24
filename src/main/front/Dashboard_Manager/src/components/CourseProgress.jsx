import React from 'react';
import './CourseProgress.css';

const CourseProgress = () => {
  const courses = [
    { id: 9, title: '네이버 클라우드 데브옵스 과정', duration: '20주차', participants: '28/30' },
    { id: 10, title: '네이버 클라우드 데브옵스 과정', duration: '20주차', participants: '28/30' },
    { id: 11, title: '네이버 클라우드 데브옵스 과정', duration: '20주차', participants: '28/30' },
    { id: 12, title: '네이버 클라우드 데브옵스 과정', duration: '20주차', participants: '28/30' },
    { id: 13, title: '네이버 클라우드 데브옵스 과정', duration: '20주차', participants: '28/30' },
  ];

  return (
    <div className="course-progress">
      <div className="course-progress-header">
        <h2 className="course-progress-title">교육 과정 현황</h2>
        <div className="course-progress-buttons">
          <button className="active">NCP</button>
          <button>AWS</button>
        </div>
      </div>
      <ul className="course-list">
        {courses.map((course) => (
          <li key={course.id} className="course-item">
            <div className="course-info">
              <span className="course-id">{course.id}기</span>
              <span className="course-title">{course.title}</span>
            </div>
            <div className="course-details">
              <span className="course-duration">강사 신지원 {course.duration}</span>
              <span className="course-participants">
                <i className="fas fa-user"></i> {course.participants}
              </span>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CourseProgress;
