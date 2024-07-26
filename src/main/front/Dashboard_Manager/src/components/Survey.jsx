import React from 'react';
import './Survey.css';

const SettingList = () => {
  return (
    <div className="setting-list">
      <div className="setting-list-header">
        <h2>설문 조사</h2>
      </div>
      <ul className="survey-list">
        <li className="survey-item">
          <div className="survey-info">
            <span className="survey-id">9기</span>
            <div className="survey-details">
              <p className="survey-title">네이버 클라우드 데브옵스 만족도 설문조사 3차</p>
            </div>
          </div>
          <p className="survey-participants">25/30</p>
        </li>
        <li className="survey-item">
          <div className="survey-info">
            <span className="survey-id">10기</span>
            <div className="survey-details">
              <p className="survey-title">네이버 클라우드 데브옵스 만족도 설문조사 2차</p>
            </div>
          </div>
          <p className="survey-participants">25/30</p>
        </li>
      </ul>
    </div>
  );
};

export default SettingList;
