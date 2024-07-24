import React from 'react';
import './SettingList.css';

const SettingList = () => {
  return (
    <div>
      <h2>설문 조사</h2>
      <div className="survey-item">
        <p>설문 제목</p>
        <p>내용 미리보기</p>
        <p>날짜</p>
      </div>
      {/* 추가적인 설문 항목들 */}
    </div>
  );
};

export default SettingList;
