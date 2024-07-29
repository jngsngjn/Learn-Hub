import React, { useState } from 'react';
import './Curriculum_Management.css';

const curriculums = [
  { id: 1, name: '네이버 클라우드 데브옵스 과정', batch: '1기', students: '10/20', teacher: '신지원' },
  { id: 2, name: '네이버 클라우드 데브옵스 과정', batch: '2기', students: '10/20', teacher: '신지원' },
  { id: 3, name: 'AWS 클라우드 데브옵스 과정', batch: '1기', students: '10/20', teacher: '신지원' },
  { id: 4, name: 'AWS 클라우드 데브옵스 과정', batch: '2기', students: '10/20', teacher: '신지원' },
];

const CurriculumManagement = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newCurriculum, setNewCurriculum] = useState({
    name: '네이버 클라우드 데브옵스 과정',
    startDate: '',
    endDate: '',
    batch: '',
    teacher: '',
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewCurriculum({ ...newCurriculum, [name]: value });
  };

  const handleCourseChange = (courseName) => {
    setNewCurriculum({ ...newCurriculum, name: courseName });
  };

  const handleAddCurriculum = () => {
    // Add the new curriculum to the list (backend integration would go here)
    setIsModalOpen(false);
    setNewCurriculum({
      name: '네이버 클라우드 데브옵스 과정',
      startDate: '',
      endDate: '',
      batch: '',
      teacher: '',
    });
  };

  return (
    <div className="curriculum-management">
      <h1>교육 과정</h1>
      <div className="button-container">
        <button className="curriculum-add-button" onClick={() => setIsModalOpen(true)}>교육 과정 추가</button>
      </div>
      <div className="curriculum-container">
        <div className="curriculum-column">
          <img src="/path/to/naver-logo.png" alt="Naver" className="curriculum-logo" />
          <div className="curriculum-list">
            {curriculums.filter(curriculum => curriculum.name.includes('네이버')).map(curriculum => (
              <div key={curriculum.id} className="curriculum-card">
                <div className="curriculum-header">
                  <span className="curriculum-batch">{curriculum.batch}</span>
                  <span className="curriculum-name">{curriculum.name}</span>
                </div>
                <div className="curriculum-footer">
                  <span className="curriculum-teacher">강사 {curriculum.teacher}</span>
                  <span className="curriculum-students">
                    <i className="fas fa-user"></i> {curriculum.students}
                  </span>
                </div>
              </div>
            ))}
          </div>
        </div>
        <div className="curriculum-column">
          <img src="/path/to/aws-logo.png" alt="AWS" className="curriculum-logo" />
          <div className="curriculum-list">
            {curriculums.filter(curriculum => curriculum.name.includes('AWS')).map(curriculum => (
              <div key={curriculum.id} className="curriculum-card">
                <div className="curriculum-header">
                  <span className="curriculum-batch">{curriculum.batch}</span>
                  <span className="curriculum-name">{curriculum.name}</span>
                </div>
                <div className="curriculum-footer">
                  <span className="curriculum-teacher">강사 {curriculum.teacher}</span>
                  <span className="curriculum-students">
                    <i className="fas fa-user"></i> {curriculum.students}
                  </span>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <button className="close-button" onClick={() => setIsModalOpen(false)}>×</button>
            <span className="curriculum-submit">교육 과정 등록</span>
            <div className="course-selection">
              <button className={`course-button ${newCurriculum.name === '네이버 클라우드 데브옵스 과정' ? 'selected' : ''}`} onClick={() => handleCourseChange('네이버 클라우드 데브옵스 과정')}>네이버 클라우드</button>
              <button className={`course-button ${newCurriculum.name === 'AWS 클라우드 데브옵스 과정' ? 'selected' : ''}`} onClick={() => handleCourseChange('AWS 클라우드 데브옵스 과정')}>AWS</button>
            </div>
            <div className="input-group">
              <label>시작일</label>
              <input type="date" name="startDate" value={newCurriculum.startDate} onChange={handleInputChange} />
            </div>
            <div className="input-group">
              <label>종료일</label>
              <input type="date" name="endDate" value={newCurriculum.endDate} onChange={handleInputChange} />
            </div>
            <div className="input-group">
              <label>기수</label>
              <input type="text" name="batch" value={newCurriculum.batch} onChange={handleInputChange} />
            </div>
            <div className="input-group">
              <label>강사</label>
              <input type="text" name="teacher" value={newCurriculum.teacher} onChange={handleInputChange} />
            </div>
            <div className="modal-actions">
              <button className="modal-button" onClick={handleAddCurriculum}>교육 과정 등록</button>
              <button className="modal-button" onClick={() => setIsModalOpen(false)}>등록 취소</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default CurriculumManagement;
