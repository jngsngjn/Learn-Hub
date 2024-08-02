import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Modal from './Modal';
import './Student_Management.css';

axios.defaults.baseURL = 'http://localhost:8080';

const StudentManagement = () => {
  const [students, setStudents] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCourse, setSelectedCourse] = useState('전체');
  const [selectedGeneration, setSelectedGeneration] = useState('전체');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newStudent, setNewStudent] = useState({
    name: '',
    gender: '',
    email: '',
    phone: '',
    curriculum: '',
    generation: '',
    curriculumFullName: '',
  });
  const [selectedFile, setSelectedFile] = useState(null);
  const [selectedStudents, setSelectedStudents] = useState([]);
  const getToken = () => localStorage.getItem('access-token');

  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    try {
      const token = getToken();
      const response = await axios.get('/managers/manage-students', {
        headers: { access: token },
      });
      setStudents(response.data.content || []);
    } catch (error) {
      console.error('응답에러:', error);
      setStudents([]);
    }
  };

  const handleSearch = (event) => setSearchTerm(event.target.value);

  const handleCourseChange = (course) => {
    const fullCourseName = course === 'NCP' ? '네이버 클라우드 데브옵스 과정' : 'AWS 데브옵스 과정';
    setSelectedCourse(fullCourseName);
  };

  const handleGenerationChange = (event) => setSelectedGeneration(event.target.value);

  const handleRefresh = () => {
    setSearchTerm('');
    setSelectedCourse('전체');
    setSelectedGeneration('전체');
  };

  const filteredStudents = students.filter(student =>
    student.name.includes(searchTerm) &&
    (selectedCourse === '전체' || student.curriculumName === selectedCourse) &&
    (selectedGeneration === '전체' || student.curriculumTh === parseInt(selectedGeneration))
  );

  const handleAddStudent = async () => {
    try {
      const token = getToken();
      console.log('토큰:', token);

      const studentData = {
        name: newStudent.name,
        gender: newStudent.gender,
        email: newStudent.email,
        phone: newStudent.phone,
        curriculumFullName: `${newStudent.curriculum} ${newStudent.generation}`
      };

      console.log('전송할 학생 데이터:', studentData);

      const response = await axios.post('/managers/manage-students/enroll', studentData, {
        headers: { access: token },
      });
      console.log('Response:', response);
      if (response.status === 200) {
        setIsModalOpen(false);
        fetchStudents();
      } else {
        console.error('학생 등록 실패');
      }
    } catch (error) {
      console.error('이건 오류메시지:', error);
    }
  };

  const handleFileUpload = async () => {
    if (!selectedFile) {
      return;
    }
    try {
      const token = getToken();
      const formData = new FormData();
      formData.append('file', selectedFile);

      const response = await axios.post('/managers/manage-students/enroll-file', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'access': token,
        },
      });

      if (response.status === 200) {
        setSelectedFile(null);
        fetchStudents();
      } else {
        console.error('파일 업로드 실패');
      }
    } catch (error) {
      console.error('파일 업로드 에러:', error);
    }
  };

  const handleGenderChange = (gender) => {
    setNewStudent({ ...newStudent, gender: gender === '남' ? 'MALE' : 'FEMALE' });
  };

  const handleInputChange = (e) => setNewStudent({ ...newStudent, [e.target.name]: e.target.value });

  const handleFileChange = (e) => setSelectedFile(e.target.files[0]);

  const handleDeleteStudent = async () => {
    try {
      const token = getToken();
      const deletePromises = selectedStudents.map(studentId =>
        axios.delete(`/managers/manage-students/${studentId}`, {
          headers: { access: token },
        })
      );
      await Promise.all(deletePromises);
      fetchStudents();
      setSelectedStudents([]);
    } catch (error) {
      console.error('오류메시지:', error);
    }
  };

  const handleCheckboxChange = (studentId) => setSelectedStudents(
    selectedStudents.includes(studentId)
      ? selectedStudents.filter(id => id !== studentId)
      : [...selectedStudents, studentId]
  );

  const handleRowClick = (studentId) => handleCheckboxChange(studentId);

  const handleRemoveFile = () => setSelectedFile(null);

  return (
    <div className="student-management">
      <h1>학생 관리</h1>
      <div className="student-controls">
        <div className="program-buttons">
          <button className={selectedCourse === '네이버 클라우드 데브옵스 과정' ? 'selected' : ''} onClick={() => handleCourseChange('NCP')}>NCP</button>
          <button className={selectedCourse === 'AWS 데브옵스 과정' ? 'selected' : ''} onClick={() => handleCourseChange('AWS')}>AWS</button>
          <select value={selectedGeneration} onChange={handleGenerationChange}>
            <option value="전체">전체</option>
            <option value="1기">1기</option>
            <option value="2기">2기</option>
            <option value="3기">3기</option>
            <option value="4기">4기</option>
            <option value="5기">5기</option>
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
              <tr key={index} onClick={() => handleRowClick(student.studentId)} className={selectedStudents.includes(student.studentId) ? 'selected' : ''}>
                <td>
                  <input type="checkbox" checked={selectedStudents.includes(student.studentId)} onChange={() => handleCheckboxChange(student.studentId)} onClick={(e) => e.stopPropagation()} />
                </td>
                <td>{student.studentId}</td>
                <td>{student.curriculumName}</td>
                <td>{student.curriculumTh}</td>
                <td>{student.name}</td>
                <td>{student.gender === 'MALE' ? '남' : '여'}</td>
                <td>{student.email}</td>
                <td>{student.phone}</td>
                <td>{student.isAttend ? <span className="status present">✔</span> : <span className="status absent">✘</span>}</td>
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
            <button className={`course-button ${newStudent.curriculum === '네이버 클라우드 데브옵스 과정' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, curriculum: '네이버 클라우드 데브옵스 과정' })}>네이버 클라우드 데브옵스 과정</button>
            <button className={`course-button ${newStudent.curriculum === 'AWS 데브옵스 과정' ? 'selected' : ''}`} onClick={() => setNewStudent({ ...newStudent, curriculum: 'AWS 데브옵스 과정' })}>AWS 데브옵스 과정</button>
          </div>
          <div className="generation-selection">
            <select name="generation" value={newStudent.generation} onChange={handleInputChange}>
              <option value="1기">1기</option>
              <option value="2기">2기</option>
              <option value="3기">3기</option>
              <option value="4기">4기</option>
              <option value="5기">5기</option>
            </select>
          </div>
          <div className="student-input-group">
            <label>이름</label>
            <input type="text" name="name" value={newStudent.name} onChange={handleInputChange} />
          </div>
          <div className="gender-selection">
            <label className="gender-label">성별</label>
            <div className="gender-buttons">
              <button className={`gender-button ${newStudent.gender === 'MALE' ? 'selected' : ''}`} onClick={() => handleGenderChange('남')}>남</button>
              <button className={`gender-button ${newStudent.gender === 'FEMALE' ? 'selected' : ''}`} onClick={() => handleGenderChange('여')}>여</button>
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
            <button className="student-modal-button" onClick={handleFileUpload}>파일 업로드</button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default StudentManagement;
