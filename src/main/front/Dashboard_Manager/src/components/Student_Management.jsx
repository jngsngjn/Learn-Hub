import React, { useState } from 'react';
import './Student_Management.css';

const initialStudents = [
  { 번호: 1, 교육과정명: 'AWS', 기수: '1기', 이름: '신수정', 성별: '여', 이메일: 'betwiwd25@gmail.com', 전화번호: '010-5092-2594', 출석여부: '결석' },
  { 번호: 2, 교육과정명: '네이버 데브옵스', 기수: '2기', 이름: '대성진', 성별: '남', 이메일: 'wjdtjdwl58@gmail.com', 전화번호: '010-3102-9650', 출석여부: '출석' },
  { 번호: 3, 교육과정명: '네이버 데브옵스', 기수: '3기', 이름: '안성민', 성별: '남', 이메일: 'smahn4069@gmail.com', 전화번호: '010-9722-5739', 출석여부: '출석' },
];

const StudentManagement = () => {
  const [students, setStudents] = useState(initialStudents);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCourse, setSelectedCourse] = useState('1기');

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleCourseChange = (event) => {
    setSelectedCourse(event.target.value);
  };

  const filteredStudents = students.filter(student =>
    student.이름.includes(searchTerm) && student.기수 === selectedCourse
  );

  const handleAddStudent = () => {
    // 학생 추가 기능 구현
  };

  const handleDeleteStudent = () => {
    // 학생 삭제 기능 구현
  };

  return (
    <div className="student-management">
      <h1>학생 관리</h1>
      <div className="controls">
        <input
          type="text"
          placeholder="학생 이름 검색"
          value={searchTerm}
          onChange={handleSearch}
        />
        <select value={selectedCourse} onChange={handleCourseChange}>
          <option value="1기">1기</option>
          <option value="2기">2기</option>
          <option value="3기">3기</option>
        </select>
      </div>
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
            <tr key={index}>
              <td><input type="checkbox" /></td>
              <td>{student.번호}</td>
              <td>{student.교육과정명}</td>
              <td>{student.기수}</td>
              <td>{student.이름}</td>
              <td>{student.성별}</td>
              <td>{student.이메일}</td>
              <td>{student.전화번호}</td>
              <td>
                {student.출석여부 === '출석' ? (
                  <span className="status present">✔</span>
                ) : (
                  <span className="status absent">✘</span>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="actions">
        <button onClick={handleAddStudent}>학생 등록</button>
        <button onClick={handleDeleteStudent}>학생 삭제</button>
      </div>
    </div>
  );
};

export default StudentManagement;
