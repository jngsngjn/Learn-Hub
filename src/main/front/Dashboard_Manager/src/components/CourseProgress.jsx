import React, { useState } from 'react'; // useState 훅-*백 연동할때 useEffect 추가 해야될듯*
import './CourseProgress.css';

const CourseProgress = () => {
  const [activeButton, setActiveButton] = useState('NCP'); //상태 버튼 변수

  const handleButtonClick = (buttonName) => {  //버튼 클릭시 상태 업데이트
    setActiveButton(buttonName);
  };

  const courses = [ // 임시 데이터값 넣어서 화면단 생성 - 백연동 필요
    { id: 9, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
    { id: 10, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
    { id: 11, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
    { id: 12, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
    { id: 13, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
  ];

  return (
    <div className="course-progress">
      <div className="course-progress-header">
        <h2 className="course-progress-title">교육 과정 현황</h2>
        <div className="course-progress-buttons">
          <button
            className={activeButton === 'NCP' ? 'active' : ''}
            onClick={() => handleButtonClick('NCP')}
          >
            NCP
          </button>
          <button
            className={activeButton === 'AWS' ? 'active' : ''}
            onClick={() => handleButtonClick('AWS')}
          >
            AWS
          </button>
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
              <div className="course-teacher">
                <span className="course-teacher-name">강사 {course.teacher}</span>
                <span className="course-duration">{course.th}</span>
              </div>
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
