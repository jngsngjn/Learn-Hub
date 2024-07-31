import React, { useState } from 'react';
import Modal from './Modal';
import './Student_Management.css';

const initialStudents = [
  { id: 1, curriculum: 'AWS', th: '1기', name: '신수정', gender: '여', email: 'betwiwd25@gmail.com', phone: '010-5092-2594', attendance: '결석' },
  { id: 2, curriculum: '네이버 데브옵스', th: '2기', name: '대성진', gender: '남', email: 'wjdtjdwl58@gmail.com', phone: '010-3102-9650', attendance: '출석' },
  { id: 3, curriculum: '네이버 데브옵스', th: '3기', name: '안성민', gender: '남', email: 'smahn4069@gmail.com', phone: '010-9722-5739', attendance: '출석' },
];

const StudentManagement = () => {
  const [students, setStudents] = useState(initialStudents); // 학생 목록
  const [searchTerm, setSearchTerm] = useState(''); // 검색어
  const [selectedCourse, setSelectedCourse] = useState('전체'); // 선택 교육 과정
  const [selectedGeneration, setSelectedGeneration] = useState('전체'); // 선택된 기수
  const [isModalOpen, setIsModalOpen] = useState(false); // 모달 열림
  const [newStudent, setNewStudent] = useState({
    name: '',
    gender: '남',
    email: '',
    phone: '',
    curriculum: 'AWS',
    th: '1기',
    attendance: '결석',
  }); // 새로운 학생 상태
  const [selectedFile, setSelectedFile] = useState(null); // 선택된 파일
  const [selectedStudents, setSelectedStudents] = useState([]); // 선택된 학생 번호

  const handleSearch = (event) => setSearchTerm(event.target.value); // 검색어 변경
  const handleCourseChange = (course) => setSelectedCourse(course); // 교육 과정 변경
  const handleGenerationChange = (event) => setSelectedGeneration(event.target.value); // 기수 변경
  const handleRefresh = () => {
    setSearchTerm('');
    setSelectedCourse('전체');
    setSelectedGeneration('전체');
  }; // 검색 및 필터 초기화

  // 필터링된 학생 목록
  const filteredStudents = students.filter(student =>
    student.name.includes(searchTerm) &&
    (selectedCourse === '전체' || student.curriculum === selectedCourse) &&
    (selectedGeneration === '전체' || student.th === selectedGeneration)
  );

  // 학생 추가
  const handleAddStudent = () => {
    setStudents([...students, { ...newStudent, id: students.length + 1 }]);
    setNewStudent({
      name: '',
      gender: '남',
      email: '',
      phone: '',
      curriculum: 'AWS',
      th: '1기',
      attendance: '결석',
    });
    setIsModalOpen(false);
    setSelectedFile(null);
  };

  // 입력 변경
  const handleInputChange = (e) => setNewStudent({ ...newStudent, [e.target.name]: e.target.value });
  // 파일 선택
  const handleFileChange = (e) => setSelectedFile(e.target.files[0]);
  // 학생 삭제
  const handleDeleteStudent = () => setStudents(students.filter(student => !selectedStudents.includes(student.id)));
  // 체크박스 변경
  const handleCheckboxChange = (studentId) => setSelectedStudents(
    selectedStudents.includes(studentId)
      ? selectedStudents.filter(id => id !== studentId)
      : [...selectedStudents, studentId]
  );
  // 행 클릭
  const handleRowClick = (studentId) => handleCheckboxChange(studentId);
  // 파일 제거
  const handleRemoveFile = () => setSelectedFile(null);

  return (
    <div className="student-management">
      <h1>학생 관리</h1>
      <div className="student-controls">
        <div className="program-buttons">
          <button className={selectedCourse === '네이버 데브옵스' ? 'selected' : ''} onClick={() => handleCourseChange('네이버 데브옵스')}>네이버 데브옵스</button>
          <button className={selectedCourse === 'AWS' ? 'selected' : ''} onClick={() => handleCourseChange('AWS')}>AWS</button>
          <select value={selectedGeneration} onChange={handleGenerationChange}>
            <option value="전체">전체</option>
            <option value="1기">1기</option>
            <option value="2기">2기</option>
            <option value="3기">3기</option>
          </select>
        </div>
        <div className="search-container">
          <div className="search-wrapper">
            <input type="text" placeholder="검색" value={searchTerm} onChange={handleSearch} />
            <i className="fas fa-search search-icon"></i>
          </div>
          <button onClick={handleRefresh} className="refresh-button">
            <i className="fas fa-sync"></i>
          </button>
        </div>
      </div>
      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th></th>
              <th>번호</th>
              <th>교육 과정명</th>
              <th>기수</th>
              <th>이름</th>
              <th>성별</th>
              <th>이메일</th>
              <th>전화번호</th>
              <th>출석 여부</th>
            </tr>
          </thead>
          <tbody>
            {filteredStudents.map((student, index) => (
              <tr key={index} onClick={() => handleRowClick(student.id)} className={selectedStudents.includes(student.id) ? 'selected' : ''}>
                <td>
                  <input type="checkbox" checked={selectedStudents.includes(student.id)} onChange={() => handleCheckboxChange(student.id)} onClick={(e) => e.stopPropagation()} />
                </td>
                <td>{student.id}</td>
                <td>{student.curriculum}</td>
                <td>{student.th}</td>
                <td>{student.name}</td>
                <td>{student.gender}</td>
                <td>{student.email}</td>
                <td>{student.phone}</td>
                <td>{student.attendance === '출석' ? <span className="status present">✔</span> : <span className="status absent">✘</span>}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="student-actions">
        <button onClick={() => setIsModalOpen(true)}>학생 등록</button>
        <button onClick={handleDeleteStudent}>학생 삭제</button>
      </div>
      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
        <span className="add_title">학생 등록</span>
        <div className="form-container">
          <label className="excel-label">엑셀 파일 첨부 </label>
          <div className="file-upload">
            {selectedFile ? (
              <div className="file-preview">
                <i className="fa-regular fa-file"></i>
                <span>{selectedFile.name}</span>
                <button className="file-remove-button" onClick={handleRemoveFile}>삭제</button>
              </div>
            ) : (
              <>
                <label htmlFor="file-upload" className="custom-file-upload">파일 선택</label>
                <input id="file-upload" type="file" onChange={handleFileChange} style={{ display: 'none' }} />
              </>
            )}
          </div>
          <div className="course-selection">
            <button className={`course-button ${newStudent.curriculum === '네이버 데브옵스' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, curriculum: '네이버 데브옵스' })}>네이버 데브옵스</button>
            <button className={`course-button ${newStudent.curriculum === 'AWS' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, curriculum: 'AWS' })}>AWS</button>
          </div>
          <div className="generation-selection">
            <select name="th" value={newStudent.th} onChange={handleInputChange}>
              <option value="1기">1기</option>
              <option value="2기">2기</option>
              <option value="3기">3기</option>
            </select>
          </div>
          <div className="student-input-group">
            <label>이름</label>
            <input type="text" name="name" value={newStudent.name} onChange={handleInputChange} />
          </div>
          <div className="gender-selection">
            <label className="gender-label">성별</label>
            <div className="gender-buttons">
              <button className={`gender-button ${newStudent.gender === '남' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, gender: '남' })}>남</button>
              <button className={`gender-button ${newStudent.gender === '여' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, gender: '여' })}>여</button>
            </div>
          </div>
          <div className="student-input-group">
            <label>이메일</label>
            <input type="email" name="email" value={newStudent.email} onChange={handleInputChange} />
          </div>
          <div className="student-input-group">
            <label>전화번호</label>
            <input type="text" name="phone" value={newStudent.phone} onChange={handleInputChange} />
          </div>
          <div className="student-modal-actions">
            <button className="student-modal-button" onClick={handleAddStudent}>학생 등록</button>
            <button className="student-modal-button" onClick={() => setIsModalOpen(false)}>등록 취소</button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default StudentManagement;
