import React, { useState } from 'react';
import Modal from './Modal';
import './Student_Management.css';
const initialStudents = [
  { 번호: 1, 교육과정명: 'AWS', 기수: '1기', 이름: '신수정', 성별: '여', 이메일: 'betwiwd25@gmail.com', 전화번호: '010-5092-2594', 출석여부: '결석' },
  { 번호: 2, 교육과정명: '네이버 데브옵스', 기수: '2기', 이름: '대성진', 성별: '남', 이메일: 'wjdtjdwl58@gmail.com', 전화번호: '010-3102-9650', 출석여부: '출석' },
  { 번호: 3, 교육과정명: '네이버 데브옵스', 기수: '3기', 이름: '안성민', 성별: '남', 이메일: 'smahn4069@gmail.com', 전화번호: '010-9722-5739', 출석여부: '출석' },
];

const StudentManagement = () => {
  const [students, setStudents] = useState(initialStudents);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCourse, setSelectedCourse] = useState('전체');
  const [selectedGeneration, setSelectedGeneration] = useState('전체');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newStudent, setNewStudent] = useState({
    이름: '',
    성별: '남',
    이메일: '',
    전화번호: '',
    교육과정명: 'AWS',
    기수: '1기',
    출석여부: '결석',
  });
  const [selectedFile, setSelectedFile] = useState(null);
  const [selectedStudents, setSelectedStudents] = useState([]);

  const handleSearch = (event) => setSearchTerm(event.target.value);
  const handleCourseChange = (course) => setSelectedCourse(course);
  const handleGenerationChange = (event) => setSelectedGeneration(event.target.value);
  const handleRefresh = () => {
    setSearchTerm('');
    setSelectedCourse('전체');
    setSelectedGeneration('전체');
  };

  const filteredStudents = students.filter(student =>
    student.이름.includes(searchTerm) &&
    (selectedCourse === '전체' || student.교육과정명 === selectedCourse) &&
    (selectedGeneration === '전체' || student.기수 === selectedGeneration)
  );

  const handleAddStudent = () => {
    setStudents([...students, { ...newStudent, 번호: students.length + 1 }]);
    setNewStudent({
      이름: '',
      성별: '남',
      이메일: '',
      전화번호: '',
      교육과정명: 'AWS',
      기수: '1기',
      출석여부: '결석',
    });
    setIsModalOpen(false);
    setSelectedFile(null);
  };

  const handleInputChange = (e) => setNewStudent({ ...newStudent, [e.target.name]: e.target.value });
  const handleFileChange = (e) => setSelectedFile(e.target.files[0]);
  const handleDeleteStudent = () => setStudents(students.filter(student => !selectedStudents.includes(student.번호)));
  const handleCheckboxChange = (studentNumber) => setSelectedStudents(
    selectedStudents.includes(studentNumber)
      ? selectedStudents.filter(number => number !== studentNumber)
      : [...selectedStudents, studentNumber]
  );
  const handleRowClick = (studentNumber) => handleCheckboxChange(studentNumber);
  const handleRemoveFile = () => setSelectedFile(null);

  return (
    <div className="student-management">
      <h1>학생 관리</h1>
      <div className="controls">
        <div className="program-buttons">
          <button className={selectedCourse === 'AWS' ? 'selected' : ''} onClick={() => handleCourseChange('AWS')}>AWS</button>
          <button className={selectedCourse === '네이버 데브옵스' ? 'selected' : ''} onClick={() => handleCourseChange('네이버 데브옵스')}>네이버 데브옵스</button>
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
              <tr key={index} onClick={() => handleRowClick(student.번호)} className={selectedStudents.includes(student.번호) ? 'selected' : ''}>
                <td>
                  <input type="checkbox" checked={selectedStudents.includes(student.번호)} onChange={() => handleCheckboxChange(student.번호)} onClick={(e) => e.stopPropagation()} />
                </td>
                <td>{student.번호}</td>
                <td>{student.교육과정명}</td>
                <td>{student.기수}</td>
                <td>{student.이름}</td>
                <td>{student.성별}</td>
                <td>{student.이메일}</td>
                <td>{student.전화번호}</td>
                <td>{student.출석여부 === '출석' ? <span className="status present">✔</span> : <span className="status absent">✘</span>}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="actions">
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
            <button className={`course-button ${newStudent.교육과정명 === '네이버 데브옵스' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, 교육과정명: '네이버 데브옵스' })}>네이버 데브옵스</button>
            <button className={`course-button ${newStudent.교육과정명 === 'AWS' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, 교육과정명: 'AWS' })}>AWS</button>
          </div>
          <div className="generation-selection">
            <select name="기수" value={newStudent.기수} onChange={handleInputChange}>
              <option value="1기">1기</option>
              <option value="2기">2기</option>
              <option value="3기">3기</option>
            </select>
          </div>
          <div className="student-input-group">
            <label>이름</label>
            <input type="text" name="이름" value={newStudent.이름} onChange={handleInputChange} />
          </div>
          <div className="gender-selection">
            <label className="gender-label">성별</label>
            <div className="gender-buttons">
              <button className={`gender-button ${newStudent.성별 === '남' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, 성별: '남' })}>남</button>
              <button className={`gender-button ${newStudent.성별 === '여' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, 성별: '여' })}>여</button>
            </div>
          </div>
          <div className="student-input-group">
            <label>이메일</label>
            <input type="email" name="이메일" value={newStudent.이메일} onChange={handleInputChange} />
          </div>
          <div className="student-input-group">
            <label>전화번호</label>
            <input type="text" name="전화번호" value={newStudent.전화번호} onChange={handleInputChange} />
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
