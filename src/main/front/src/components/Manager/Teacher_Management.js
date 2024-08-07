import React, { useState, useEffect } from 'react';
import axios from '../../utils/axios';
import Modal from './Modal';
import './Teacher_Management.css';
import swal from 'sweetalert';

const TeacherManagement = () => {
  const [teachers, setTeachers] = useState([]);
  const [curriculums, setCurriculums] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCourse, setSelectedCourse] = useState('전체');
  const [selectedGeneration, setSelectedGeneration] = useState('전체');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newTeacher, setNewTeacher] = useState({
    name: '',
    email: '',
    phone: '',
    curriculum: '',
    generation: '',
    curriculumFullName: '',
  });
  const [selectedTeachers, setSelectedTeachers] = useState([]);

  // 강사와 커리큘럼 데이터를 가져옴
  useEffect(() => {
    fetchTeachers();
    fetchCurriculums();
  }, []);

  // 강사 목록 서버에서 가져오기
  const fetchTeachers = async () => {
    try {
      const response = await axios.get('/managers/manage-teachers');
      const teachersData = response.data && response.data.content ? response.data.content : [];
      setTeachers(teachersData);
    } catch (error) {
      console.error('응답 에러:', error);
      setTeachers([]);
    }
  };

  // 커리큘럼 목록
  const fetchCurriculums = async () => {
    try {
      const response = await axios.get('/managers/enroll-ready');
      setCurriculums(response.data || []);
    } catch (error) {
      console.error('기수 가져오기 에러:', error);
      setCurriculums([]);
    }
  };

  // 검색어
  const handleSearch = (event) => setSearchTerm(event.target.value);

  // 선택된 커리큘럼
  const handleCourseChange = (course) => {
    const fullCourseName = course === 'NCP' ? '네이버 클라우드 데브옵스 과정' : 'AWS 데브옵스 과정';
    setSelectedCourse(fullCourseName);
    setSelectedGeneration('전체');
    setNewTeacher({ ...newTeacher, curriculum: fullCourseName });
  };

  // 선택된 기수
  const handleGenerationChange = (event) => setSelectedGeneration(event.target.value);

  // 검색어와 선택된 강의
  const handleRefresh = () => {
    setSearchTerm('');
    setSelectedCourse('전체');
    setSelectedGeneration('전체');
  };

  // 필터링된 강사
  const filteredTeachers = (Array.isArray(teachers) ? teachers : []).filter(teacher =>
    (teacher.name?.includes(searchTerm) || teacher.email?.includes(searchTerm)) &&
    (selectedCourse === '전체' || teacher.curriculumName === selectedCourse) &&
    (selectedGeneration === '전체' || teacher.curriculumTh === parseInt(selectedGeneration))
  );

  // 새로운 강사 등록
  const handleAddTeacher = async () => {
    try {
      const teacherData = {
        name: newTeacher.name,
        email: newTeacher.email,
        phone: newTeacher.phone,
        curriculumFullName: `${newTeacher.curriculum} ${newTeacher.generation}기`
      };

      console.log("강사 등록 데이터:", teacherData); // 디버깅용 로그

      const response = await axios.post('/managers/manage-teachers/enroll', teacherData);
      if (response.status === 200) {
        setIsModalOpen(false);
        fetchTeachers();
      } else {
        console.error('강사 등록 실패');
      }
    } catch (error) {
      console.error('등록 에러:', error);
    }
  };

  // 입력 필드 값
  const handleInputChange = (e) => setNewTeacher({ ...newTeacher, [e.target.name]: e.target.value });

  // 선택된 강사 삭제
  const handleDeleteTeachers = async () => {
    try {
      const deletePromises = selectedTeachers.map(teacherId =>
        axios.delete(`/managers/manage-teachers/${teacherId}`)
      );
      await Promise.all(deletePromises);
      fetchTeachers();
      setSelectedTeachers([]);
    } catch (error) {
      console.error('삭제 에러:', error);
    }
  };

  // 체크박스 상태
  const handleCheckboxChange = (teacherId) => setSelectedTeachers(
    selectedTeachers.includes(teacherId)
      ? selectedTeachers.filter(id => id !== teacherId)
      : [...selectedTeachers, teacherId]
  );

  // 행 클릭
  const handleRowClick = (teacherId) => handleCheckboxChange(teacherId);

  // 선택된 기수를 필터링
  const filteredGenerations = curriculums.find(curriculum => curriculum.type === (selectedCourse === '네이버 클라우드 데브옵스 과정' ? 'NCP' : 'AWS'))?.th || [];

  return (
    <div className="teacher-management">
      <h1>강사 관리</h1>
      <div className="teacher-controls">
        <div className="teacher-program-buttons">
          <button className={selectedCourse === '네이버 클라우드 데브옵스 과정' ? 'selected' : ''} onClick={() => handleCourseChange('NCP')}>NCP</button>
          <button className={selectedCourse === 'AWS 데브옵스 과정' ? 'selected' : ''} onClick={() => handleCourseChange('AWS')}>AWS</button>
          <select value={selectedGeneration} onChange={handleGenerationChange}>
            <option value="전체">전체</option>
            {filteredGenerations.map(th => (
              <option key={`${th}`} value={th}>{`${th}기`}</option>
            ))}
          </select>
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
            {filteredTeachers.length > 0 ? (
              filteredTeachers.map((teacher, index) => (
                <tr key={index} onClick={() => handleRowClick(teacher.teacherId)} className={selectedTeachers.includes(teacher.teacherId) ? 'selected' : ''}>
                  <td>
                    <input type="checkbox" checked={selectedTeachers.includes(teacher.teacherId)} onChange={() => handleCheckboxChange(teacher.teacherId)} onClick={(e) => e.stopPropagation()} />
                  </td>
                  <td>{teacher.teacherId}</td>
                  <td>{teacher.name}</td>
                  <td>{teacher.curriculumName}</td>
                  <td>{teacher.curriculumTh}</td>
                  <td>{teacher.email}</td>
                  <td>{teacher.phone}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="7">강사 데이터가 없습니다.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      <div className="teacher-actions">
        <button onClick={() => setIsModalOpen(true)}>강사 등록</button>
        <button onClick={handleDeleteTeachers}>강사 삭제</button>
      </div>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
        <span className="add_title">강사 등록</span>
        <div className="teacher-form-container">
          <div className="teacher-course-selection">
            <button className={`teacher-course-button ${newTeacher.curriculum === '네이버 클라우드 데브옵스 과정' ? 'selected' : ''}`} onClick={() => setNewTeacher({ ...newTeacher, curriculum: '네이버 클라우드 데브옵스 과정' })}>NCP</button>
            <button className={`teacher-course-button ${newTeacher.curriculum === 'AWS 데브옵스 과정' ? 'selected' : ''}`} onClick={() => setNewTeacher({ ...newTeacher, curriculum: 'AWS 데브옵스 과정' })}>AWS</button>
          </div>
          <div className="teacher-generation-selection">
            <select name="generation" value={newTeacher.generation} onChange={handleInputChange}>
              {(newTeacher.curriculum === '네이버 클라우드 데브옵스 과정' ? curriculums.find(curriculum => curriculum.type === 'NCP')?.th : curriculums.find(curriculum => curriculum.type === 'AWS')?.th || []).map(th => (
                <option key={`${th}`} value={th}>{`${th}기`}</option>
              ))}
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
            <button className="add-teacher-modal-button" onClick={() => setIsModalOpen(false)}>등록 취소</button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default TeacherManagement;
