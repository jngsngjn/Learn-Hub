import React from 'react';
import './CourseProgress.css';

const CourseProgress = () => {
  return (
    <div>
      <h2>교육 과정 현황</h2>
      <div className="course-item">
        <p>네이버 클라우드 데브옵스 과정</p>
        <p>강사 신성진</p>
        <p>20주차</p>
        <p>28/30</p>
      </div>
      {/* 추가적인 과정 항목들 */}
    </div>
  );
};

export default CourseProgress;
