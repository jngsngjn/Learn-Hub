import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { CirclePicker } from 'react-color';
import axios from '../../utils/axios';
import './Curriculum_Management.css';
import './Modal.css';
import swal from 'sweetalert';

const CurriculumManagement = () => {
  const [ncpCurriculums, setNcpCurriculums] = useState([]);
  const [awsCurriculums, setAwsCurriculums] = useState([]);
  const [teachers, setTeachers] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isColorPickerOpen, setIsColorPickerOpen] = useState(false);
  const [newCurriculum, setNewCurriculum] = useState({
    type: '',
    startDate: '',
    endDate: '',
    color: '',
    teacherId: '',
  });

  const getToken = () => localStorage.getItem('access-token');

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewCurriculum({ ...newCurriculum, [name]: value });
  };

  const handleCourseChange = (courseName) => {
    const fullCourseName = courseName === 'NCP' ? '네이버 클라우드 데브옵스 과정' : 'AWS 데브옵스 과정';
    setNewCurriculum({ ...newCurriculum, type: fullCourseName });
  };

  const handleColorChange = (color) => {
    setNewCurriculum({ ...newCurriculum, color: color.hex });
    setIsColorPickerOpen(false);
  };

  const handleAddCurriculum = async () => {
    const newCurriculumItem = {
      type: newCurriculum.type,
      startDate: newCurriculum.startDate,
      endDate: newCurriculum.endDate,
      color: newCurriculum.color,
      teacherId: newCurriculum.teacherId,
    };

    if (new Date(newCurriculumItem.endDate) <= new Date(newCurriculumItem.startDate)) {
      swal('등록 실패', '종료일은 시작일 이후여야 합니다.', 'warning');
      return;
    }

    try {
      const token = getToken();
      console.log('전송할 데이터:', newCurriculumItem);
      const response = await axios.post('/managers/manage-curriculums/enroll', newCurriculumItem, {
        headers: { access: token },
      });

      if (response.status === 200) {
        console.log('과정 추가 응답:', response.data);
        setIsModalOpen(false);
        fetchCurriculums(newCurriculum.type);
      } else {
        console.error('교육 과정 등록 실패');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const fetchCurriculums = async (type) => {
    try {
      const token = getToken();
      console.log(`토큰 확인: ${token}`);
      const response = await axios.get(`/managers/manage-curriculums/${type}`, {
        headers: { access: token },
      });
      console.log(`${type} 과정 목록 응답:`, response.data);

      if (type === 'NCP') {
        setNcpCurriculums(response.data || []);
      } else if (type === 'AWS') {
        setAwsCurriculums(response.data || []);
      }
    } catch (error) {
      console.error('에러 메시지:', error);
    }
  };

  const fetchTeachers = async () => {
    try {
      const token = getToken();
      const response = await axios.get('/managers/manage-teachers', {
        headers: { access: token },
      });
      setTeachers(response.data || []);
    } catch (error) {
      console.error('강사 목록 가져오기 에러:', error);
      setTeachers([]);
    }
  };

  useEffect(() => {
    fetchCurriculums('NCP');
    fetchCurriculums('AWS');
    fetchTeachers();
  }, []);

  const renderCurriculumList = (curriculums) => (
    curriculums.map(curriculum => (
      <div key={curriculum.id} className="curriculum-card">
        <div className="curriculum-header">
          <span className="curriculum-th" style={{ backgroundColor: curriculum.color }}>{curriculum.th}기</span>
          <Link to={`/managers/manage-curriculums/${curriculum.id}`} className="curriculum-type">{curriculum.name}</Link>
        </div>
        <div className="curriculum-footer">
          <span className="curriculum-teacher">강사 {curriculum.teacherName}</span>
          <span className="curriculum-students">
            <i className="fas fa-user"></i> {curriculum.attendance} / {curriculum.total}
          </span>
        </div>
      </div>
    ))
  );

  return (
    <div className="curriculum-management">
      <h1>교육 과정</h1>
      <div className="button-container">
        <button className="curriculum-add-button" onClick={() => setIsModalOpen(true)}>교육 과정 추가</button>
      </div>
      <div className="curriculum-container">
        <div className="curriculum-column">
          <h2>NCP 과정</h2>
          <div className="curriculum-list">
            {renderCurriculumList(ncpCurriculums)}
          </div>
        </div>
        <div className="curriculum-column">
          <h2>AWS 과정</h2>
          <div className="curriculum-list">
            {renderCurriculumList(awsCurriculums)}
          </div>
        </div>
      </div>
      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <button className="modal-close" onClick={() => setIsModalOpen(false)}>×</button>
            <span className="curriculum-submit">교육 과정 등록</span>
            <div className="course-selection">
              <button className={`course-button ${newCurriculum.type === '네이버 클라우드 데브옵스 과정' ? 'selected' : ''}`} onClick={() => handleCourseChange('NCP')}>NCP</button>
              <button className={`course-button ${newCurriculum.type === 'AWS 데브옵스 과정' ? 'selected' : ''}`} onClick={() => handleCourseChange('AWS')}>AWS</button>
            </div>
            <div className="curriculum-input-group">
              <label>시작일</label>
              <input type="date" name="startDate" value={newCurriculum.startDate} onChange={handleInputChange} />
            </div>
            <div className="curriculum-input-group">
              <label>종료일</label>
              <input type="date" name="endDate" value={newCurriculum.endDate} onChange={handleInputChange} />
            </div>
            <div className="curriculum-input-group">
              <label>기수 색상</label>
              <input type="text" name="color" value={newCurriculum.color} readOnly />
                <span className="color-input-title">{newCurriculum.type}</span>
              <div className="color-input-select" onClick={() => setIsColorPickerOpen(true)}>

                <div className="color-box" style={{ backgroundColor: newCurriculum.color }}></div>
              </div>

            </div>
            {isColorPickerOpen && (
              <div className="color-picker-modal-overlay" onClick={() => setIsColorPickerOpen(false)}>
                <div className="color-picker-modal-content" onClick={(e) => e.stopPropagation()}>
                  <CirclePicker
                    color={newCurriculum.color}
                    onChangeComplete={handleColorChange}
                    colors={["#F3C41E", "#F58D11", "#B85B27", "#A90C57", "#F45CE5", "#AE59F0", "#0A8735", "#6F961E", "#19E308", "#1D1AA6", "#20CFF5", "#98B3E5"]}
                  />
                </div>
              </div>
            )}
            <div className="curriculum-input-group">
              <label>강사</label>
              <select name="teacherId" value={newCurriculum.teacherId} onChange={handleInputChange}>
                <option value="">강사를 선택하세요</option>
                {(Array.isArray(teachers) ? teachers : []).map(teacher => (
                  <option key={teacher.id} value={teacher.id}>{teacher.name}</option>
                ))}
              </select>
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
