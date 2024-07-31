import React, { useState } from 'react';
import Modal from './Modal';
import './Teacher_Management.css';

const initialTeachers = [
  { 번호: 1, 이름: '서준명', 교육과정명: '네이버클라우드 데브옵스', 기수: '10기', 이메일: '123@gmail.com', 전화번호: '010-1234-1234' },
  { 번호: 2, 이름: '김승민', 교육과정명: '네이버클라우드 데브옵스', 기수: '10기', 이메일: '123@naver.com', 전화번호: '010-1234-1234' },
];

const TeacherManagement = () => {
  const [teachers, setTeachers] = useState(initialTeachers);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCourse, setSelectedCourse] = useState('');
  const [selectedGeneration, setSelectedGeneration] = useState('1기');
  const [isAddModalOpen, setIsAddModalOpen] = useState(false);
  const [isInfoModalOpen, setIsInfoModalOpen] = useState(false);
  const [newTeacher, setNewTeacher] = useState({
    이름: '',
    이메일: '',
    전화번호: '',
  });
  const [selectedTeachers, setSelectedTeachers] = useState([]);
  const [selectedTeacher, setSelectedTeacher] = useState(null);

  const handleSearch = (event) => setSearchTerm(event.target.value);
  const handleCourseChange = (course) => setSelectedCourse(course);
  const handleGenerationChange = (event) => setSelectedGeneration(event.target.value);
  const handleRefresh = () => setSearchTerm('');

  const handleAddTeacher = () => {
    setTeachers([...teachers, { ...newTeacher, 번호: teachers.length + 1, 교육과정명: selectedCourse, 기수: selectedGeneration }]);
    setNewTeacher({ 이름: '', 이메일: '', 전화번호: '' });
    setIsAddModalOpen(false);
  };

  const handleInputChange = (e) => setNewTeacher({ ...newTeacher, [e.target.name]: e.target.value });

  const handleDeleteTeacher = () => {
    setTeachers(teachers.filter(teacher => !selectedTeachers.includes(teacher.번호)));
    setSelectedTeacher(null);
    setIsInfoModalOpen(false);
  };

  const handleCheckboxChange = (teacherNumber) => setSelectedTeachers(
    selectedTeachers.includes(teacherNumber)
      ? selectedTeachers.filter(number => number !== teacherNumber)
      : [...selectedTeachers, teacherNumber]
  );

  const handleRowClick = (teacher) => {
    setSelectedTeacher(teacher);
    setIsInfoModalOpen(true);
  };

  const handleTeacherUpdate = () => {
    const updatedTeachers = teachers.map(teacher =>
      teacher.번호 === selectedTeacher.번호 ? { ...selectedTeacher } : teacher
    );
    setTeachers(updatedTeachers);
    setIsInfoModalOpen(false);
  };

  const handleTeacherChange = (e) => setSelectedTeacher({ ...selectedTeacher, [e.target.name]: e.target.value });

  const filteredTeachers = teachers.filter(teacher =>
    teacher.이름.includes(searchTerm)
  );

  return (
    <div className="teacher-management">
      <h1>강사 관리</h1>
      <div className="teacher-controls">
        <div className="teacher-program-buttons">
          <button className={selectedCourse === '네이버클라우드 데브옵스' ? 'selected' : ''} onClick={() => handleCourseChange('네이버클라우드 데브옵스')}>네이버클라우드 데브옵스</button>
          <button className={selectedCourse === 'AWS' ? 'selected' : ''} onClick={() => handleCourseChange('AWS')}>AWS</button>
        </div>
        <div className="teacher-search-container">
          <div className="teacher-search-wrapper">
            <input type="text" placeholder="검색" value={searchTerm} onChange={handleSearch} />
            <i className="fas fa-search teacher-search-icon"></i>
          </div>
          <button onClick={handleRefresh} className="teacher-refresh-button">
            <i className="fas fa-sync"></i>
          </button>
        </div>
      </div>
      <div className="teacher-table-container">
        <table>
          <thead>
            <tr>
              <th></th>
              <th>번호</th>
              <th>이름</th>
              <th>교육 과정명</th>
              <th>기수</th>
              <th>이메일</th>
              <th>전화번호</th>
            </tr>
          </thead>
          <tbody>
            {filteredTeachers.map((teacher, index) => (
              <tr key={index} onClick={() => handleRowClick(teacher)} className={selectedTeachers.includes(teacher.번호) ? 'selected' : ''}>
                <td>
                  <input type="checkbox" checked={selectedTeachers.includes(teacher.번호)} onChange={() => handleCheckboxChange(teacher.번호)} onClick={(e) => e.stopPropagation()} />
                </td>
                <td>{teacher.번호}</td>
                <td>{teacher.이름}</td>
                <td>{teacher.교육과정명}</td>
                <td>{teacher.기수}</td>
                <td>{teacher.이메일}</td>
                <td>{teacher.전화번호}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="teacher-actions">
        <button onClick={() => setIsAddModalOpen(true)}>강사 등록</button>
        <button onClick={handleDeleteTeacher}>강사 삭제</button>
      </div>

      {/* 강사 등록 모달 */}
      <Modal isOpen={isAddModalOpen} onClose={() => setIsAddModalOpen(false)}>
        <span className="teacher-add-title">강사 등록</span>
        <div className="teacher-form-container">
          <div className="teacher-course-selection">
            <button className={`teacher-course-button ${selectedCourse === '네이버클라우드 데브옵스' ? 'selected' : ''}`} onClick={() => handleCourseChange('네이버클라우드 데브옵스')}>네이버클라우드 데브옵스</button>
            <button className={`teacher-course-button ${selectedCourse === 'AWS' ? 'selected' : ''}`} onClick={() => handleCourseChange('AWS')}>AWS</button>
          </div>
          <div className="teacher-generation-selection">
            <select name="기수" value={selectedGeneration} onChange={handleGenerationChange}>
              <option value="1기">1기</option>
              <option value="2기">2기</option>
              <option value="3기">3기</option>
            </select>
          </div>
          <div className="teacher-input-group">
            <label>이름</label>
            <input type="text" name="이름" value={newTeacher.이름} onChange={handleInputChange} />
          </div>
          <div className="teacher-input-group">
            <label>이메일</label>
            <input type="email" name="이메일" value={newTeacher.이메일} onChange={handleInputChange} />
          </div>
          <div className="teacher-input-group">
            <label>전화번호</label>
            <input type="text" name="전화번호" value={newTeacher.전화번호} onChange={handleInputChange} />
          </div>
          <div className="teacher-modal-actions">
            <button className="add-teacher-modal-button" onClick={handleAddTeacher}>강사 등록</button>
            <button className="add-teacher-modal-button" onClick={() => setIsAddModalOpen(false)}>등록 취소</button>
          </div>
        </div>
      </Modal>

      {/* 강사 정보 모달 */}
      <Modal isOpen={isInfoModalOpen} onClose={() => setIsInfoModalOpen(false)}>
        <span className="teacher-add-title">강사 정보</span>
        <div className="teacher-form-container">
          <div className="teacher-course-selection">
            <button className={`teacher-course-button ${selectedTeacher?.교육과정명 === '네이버클라우드 데브옵스' ? 'selected' : ''}`} onClick={() => handleCourseChange('네이버클라우드 데브옵스')}>네이버클라우드 데브옵스</button>
            <button className={`teacher-course-button ${selectedTeacher?.교육과정명 === 'AWS' ? 'selected' : ''}`} onClick={() => handleCourseChange('AWS')}>AWS</button>
          </div>
          <div className="teacher-generation-selection">
            <select name="기수" value={selectedTeacher?.기수 || selectedGeneration} onChange={handleTeacherChange}>
              <option value="1기">1기</option>
              <option value="2기">2기</option>
              <option value="3기">3기</option>
            </select>
          </div>
          <div className="teacher-input-group">
            <label>이름</label>
            <input type="text" name="이름" value={selectedTeacher?.이름 || ''} onChange={handleTeacherChange} />
          </div>
          <div className="teacher-input-group">
            <label>이메일</label>
            <input type="email" name="이메일" value={selectedTeacher?.이메일 || ''} onChange={handleTeacherChange} />
          </div>
          <div className="teacher-input-group">
            <label>전화번호</label>
            <input type="text" name="전화번호" value={selectedTeacher?.전화번호 || ''} onChange={handleTeacherChange} />
          </div>
          <div className="teacher-modal-actions">
            <button className="add-teacher-modal-button" onClick={handleTeacherUpdate}>강사 정보 수정</button>
            <button className="add-teacher-modal-button" onClick={handleDeleteTeacher}>강사 정보 삭제</button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default TeacherManagement;
