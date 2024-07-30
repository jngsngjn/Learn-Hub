import React, { useState } from 'react'; // useState 훅-*백 연동할때 useEffect 추가 해야될듯*
import './Lecture.css';

const Lecture = () => {
  const [activeButton, setActiveButton] = useState('NCP'); //상태 버튼 변수

  const handleButtonClick = (buttonName) => {  //버튼 클릭시 상태 업데이트
    setActiveButton(buttonName);
  };

  const lectures = [ // 임시 데이터값 넣어서 화면단 생성 - 백연동 필요
    { id: 9, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
    { id: 10, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
    { id: 11, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
    { id: 12, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
    { id: 13, title: '네이버 클라우드 데브옵스 과정', teacher: '신지원', th: '20주차', participants: '28/30' },
  ];

  return (
    <div className="lecture-progress">
      <div className="lecture-progress-header">
        <h2 className="lecture-progress-title">교육 과정 현황</h2>
        <div className="lecture-progress-buttons">
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
      <ul className="lecture-list">
        {lectures.map((lecture) => (
          <li key={lecture.id} className="lecture-item">
            <div className="lecture-info">
              <span className="lecture-id">{lecture.id}기</span>
              <span className="lecture-title">{lecture.title}</span>
            </div>
            <div className="lecture-details">
              <div className="lecture-teacher">
                <span className="lecture-teacher-name">강사 {lecture.teacher}</span>
                <span className="lecture-duration">{lecture.th}</span>
              </div>
              <span className="lecture-participants">
                <i className="fas fa-user"></i> {lecture.participants}
              </span>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Lecture;
