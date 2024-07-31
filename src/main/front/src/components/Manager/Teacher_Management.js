import React, { useState } from 'react';
import Modal from './Modal';
import './Teacher_Management.css';

const initialTeachers = [
  { id: 1, name: '서준명', curriculum: '네이버 데브옵스', th: '10기', email: '123@gmail.com', phone: '010-1234-1234' },
  { id: 2, name: '김승민', curriculum: '네이버 데브옵스', th: '10기', email: '123@naver.com', phone: '010-1234-1234' },
];

const TeacherManagement = () => {
  const [teachers, setTeachers] = useState(initialTeachers);
  const [searchTerm, setSearchTerm] = useState('');
  const [mainSelectedCourse, setMainSelectedCourse] = useState('');
  const [mainSelectedGeneration, setMainSelectedGeneration] = useState('1기');
  const [isAddModalOpen, setIsAddModalOpen] = useState(false);
  const [isInfoModalOpen, setIsInfoModalOpen] = useState(false);
  const [addSelectedCourse, setAddSelectedCourse] = useState('');
  const [infoSelectedCourse, setInfoSelectedCourse] = useState('');
  const [addSelectedGeneration, setAddSelectedGeneration] = useState('1기');
  const [infoSelectedGeneration, setInfoSelectedGeneration] = useState('1기');
  const [newTeacher, setNewTeacher] = useState({
    name: '',
    email: '',
    phone: '',
  });
  const [selectedTeachers, setSelectedTeachers] = useState([]);
  const [selectedTeacher, setSelectedTeacher] = useState(null);

  const handleSearch = (event) => setSearchTerm(event.target.value);
  const handleMainCourseChange = (course) => setMainSelectedCourse(course);
  const handleMainGenerationChange = (event) => setMainSelectedGeneration(event.target.value);
  const handleAddCourseChange = (course) => setAddSelectedCourse(course);
  const handleInfoCourseChange = (course) => setInfoSelectedCourse(course);
  const handleAddGenerationChange = (event) => setAddSelectedGeneration(event.target.value);
  const handleInfoGenerationChange = (event) => setInfoSelectedGeneration(event.target.value);
  const handleRefresh = () => {
    setSearchTerm('');
    setMainSelectedCourse('');
  };

  // 강사 추가
  const handleAddTeacher = () => {
    setTeachers([...teachers, { ...newTeacher, id: teachers.length + 1, curriculum: addSelectedCourse, th: addSelectedGeneration }]);
    setNewTeacher({ name: '', email: '', phone: '' });
    setIsAddModalOpen(false);
  };

  // 입력 변경
  const handleInputChange = (e) => setNewTeacher({ ...newTeacher, [e.target.name]: e.target.value });

  // 강사 삭제
  const handleDeleteTeachers = () => {
    setTeachers(teachers.filter(teacher => !selectedTeachers.includes(teacher.id)));
    setSelectedTeacher(null);
    setIsInfoModalOpen(false);
  };

  // 선택된 강사 삭제
  const handleDeleteSelectedTeacher = () => {
    if (selectedTeacher) {
      setTeachers(teachers.filter(teacher => teacher.id !== selectedTeacher.id));
      setSelectedTeacher(null);
      setIsInfoModalOpen(false);
    }
  };

  // 체크박스 변경
  const handleCheckboxChange = (teacherId) => setSelectedTeachers(
    selectedTeachers.includes(teacherId)
      ? selectedTeachers.filter(id => id !== teacherId)
      : [...selectedTeachers, teacherId]
  );

  // 행 클릭
  const handleRowClick = (teacher) => {
    setSelectedTeacher(teacher);
    setInfoSelectedCourse(teacher.curriculum);
    setInfoSelectedGeneration(teacher.th);
    setIsInfoModalOpen(true);
  };

  // 강사 정보 수정
  const handleTeacherUpdate = () => {
    const updatedTeachers = teachers.map(teacher =>
      teacher.id === selectedTeacher.id ? { ...selectedTeacher, curriculum: infoSelectedCourse, th: infoSelectedGeneration } : teacher
    );
    setTeachers(updatedTeachers);
    setIsInfoModalOpen(false);
  };

  const handleTeacherChange = (e) => setSelectedTeacher({ ...selectedTeacher, [e.target.name]: e.target.value, });

  // 검색 필터 (이름 이메일)
  const filteredTeachers = teachers.filter(teacher =>
    (teacher.name.includes(searchTerm) || teacher.email.includes(searchTerm)) &&
    (mainSelectedCourse === '' || teacher.curriculum === mainSelectedCourse)
  );

  return (
    <div className="teacher-management">
      <h1>강사 관리</h1>
      <div className="teacher-controls">
        <div className="teacher-program-buttons">
          <button
            className={mainSelectedCourse === '네이버 데브옵스' ? 'selected' : ''}
            onClick={() => handleMainCourseChange('네이버 데브옵스')}
          >
            네이버 데브옵스
          </button>
          <button
            className={mainSelectedCourse === 'AWS' ? 'selected' : ''}
            onClick={() => handleMainCourseChange('AWS')}
          >
            AWS
          </button>
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
              <tr key={index} onClick={() => handleRowClick(teacher)} className={selectedTeachers.includes(teacher.id) ? 'selected' : ''}>
                <td>
                  <input type="checkbox" checked={selectedTeachers.includes(teacher.id)} onChange={() => handleCheckboxChange(teacher.id)} onClick={(e) => e.stopPropagation()} />
                </td>
                <td>{teacher.id}</td>
                <td>{teacher.name}</td>
                <td>{teacher.curriculum}</td>
                <td>{teacher.th}</td>
                <td>{teacher.email}</td>
                <td>{teacher.phone}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="teacher-actions">
        <button onClick={() => setIsAddModalOpen(true)}>강사 등록</button>
        <button onClick={handleDeleteTeachers}>강사 삭제</button>
      </div>

      {/* 강사 등록 모달 */}
      <Modal isOpen={isAddModalOpen} onClose={() => setIsAddModalOpen(false)}>
        <span className="teacher-add-title">강사 등록</span>
        <div className="teacher-form-container">
          <div className="teacher-course-selection">
            <button className={`teacher-course-button ${addSelectedCourse === '네이버 데브옵스' ? 'selected' : ''}`} onClick={() => handleAddCourseChange('네이버 데브옵스')}>네이버 데브옵스</button>
            <button className={`teacher-course-button ${addSelectedCourse === 'AWS' ? 'selected' : ''}`} onClick={() => handleAddCourseChange('AWS')}>AWS</button>
          </div>
          <div className="teacher-generation-selection">
            <select name="th" value={addSelectedGeneration} onChange={handleAddGenerationChange}>
              <option value="1기">1기</option>
              <option value="2기">2기</option>
              <option value="3기">3기</option>
            </select>
          </div>
          <div className="teacher-input-group">
            <label>이름</label>
            <input type="text" name="name" value={newTeacher.name} onChange={handleInputChange} />
          </div>
          <div className="teacher-input-group">
            <label>이메일</label>
            <input type="email" name="email" value={newTeacher.email} onChange={handleInputChange} />
          </div>
          <div className="teacher-input-group">
            <label>전화번호</label>
            <input type="text" name="phone" value={newTeacher.phone} onChange={handleInputChange} />
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
            <button className={`teacher-course-button ${infoSelectedCourse === '네이버 데브옵스' ? 'selected' : ''}`} onClick={() => handleInfoCourseChange('네이버 데브옵스')}>네이버 데브옵스</button>
            <button className={`teacher-course-button ${infoSelectedCourse === 'AWS' ? 'selected' : ''}`} onClick={() => handleInfoCourseChange('AWS')}>AWS</button>
          </div>
          <div className="teacher-generation-selection">
            <select name="th" value={infoSelectedGeneration} onChange={handleInfoGenerationChange}>
              <option value="1기">1기</option>
              <option value="2기">2기</option>
              <option value="3기">3기</option>
            </select>
          </div>
          <div className="teacher-input-group">
            <label>이름</label>
            <input type="text" name="name" value={selectedTeacher?.name || ''} onChange={handleTeacherChange} />
          </div>
          <div className="teacher-input-group">
            <label>이메일</label>
            <input type="email" name="email" value={selectedTeacher?.email || ''} onChange={handleTeacherChange} />
          </div>
          <div className="teacher-input-group">
            <label>전화번호</label>
            <input type="text" name="phone" value={selectedTeacher?.phone || ''} onChange={handleTeacherChange} />
          </div>
          <div className="teacher-modal-actions">
            <button className="add-teacher-modal-button" onClick={handleTeacherUpdate}>강사 정보 수정</button>
            <button className="add-teacher-modal-button" onClick={handleDeleteSelectedTeacher}>강사 정보 삭제</button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default TeacherManagement;
