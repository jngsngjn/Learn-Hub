import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { CirclePicker } from "react-color";
import axios from "../../utils/axios";
import "./CurriculumManagement.css";
import "../../components/Modal/ManagerModal/ManagerModal.css";
import swal from "sweetalert";

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
    console.log(`Input Changed - ${name}: ${value}`);
  };

  const handleCourseChange = (courseName) => {
    setNewCurriculum({ ...newCurriculum, type: courseName, color: courseName }); // NCP 또는 AWS 클릭 시 기수 색상에도 반영
    console.log(`Course Changed: ${courseName}`);
  };

  const handleColorChange = (color) => {
    setNewCurriculum({ ...newCurriculum, color: color.hex });
    setIsColorPickerOpen(false);
    console.log(`Color Selected: ${color.hex}`);
  };

  const handleAddCurriculum = async () => {
    const newCurriculumItem = {
      type: newCurriculum.type,
      startDate: newCurriculum.startDate,
      endDate: newCurriculum.endDate,
      color: newCurriculum.color,
    };

    // teacherId가 유효한 값일 때만 숫자로 변환
    if (newCurriculum.teacherId && !isNaN(newCurriculum.teacherId)) {
      newCurriculumItem.teacherId = parseInt(newCurriculum.teacherId, 10);
    } else {
      console.log("No valid teacher selected, skipping teacherId.");
    }

    console.log("Preparing to send curriculum:", newCurriculumItem);

    if (new Date(newCurriculum.endDate) <= new Date(newCurriculum.startDate)) {
      swal('등록 실패', '종료일은 시작일 이후여야 합니다.', 'warning');
      return;
    }

    try {
      const token = getToken();
      const response = await axios.post('/managers/manage-curriculums/enroll', newCurriculumItem, {
        headers: {
          'Content-Type': 'application/json', // JSON 형식으로 전송
          access: token,
        },
      });

      console.log("Response received:", response.data);

      if (response.status === 200) {
        setIsModalOpen(false);
        fetchCurriculums(newCurriculum.type);
      } else {
        console.error('교육 과정 등록 실패');
      }
    } catch (error) {
      console.error('Error during curriculum enrollment:', error);
    }
  };

  const fetchCurriculums = async (type) => {
    try {
      const token = getToken();
      console.log(`Fetching ${type} curriculums with token: ${token}`);
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
      console.error(`Error fetching ${type} curriculums:`, error);
    }
  };

  const fetchTeachers = async () => {
    try {
      const token = getToken();
      console.log(`Fetching teachers with token: ${token}`);
      const response = await axios.get('/managers/manage-teachers', {
        headers: { access: token },
      });
      const teachersData = response.data?.content || [];
      setTeachers(teachersData);
      console.log("Teachers fetched:", teachersData);
    } catch (error) {
      console.error("Error fetching teachers:", error);
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
              <button className={`course-button ${newCurriculum.type === 'NCP' ? 'selected' : ''}`} onClick={() => handleCourseChange('NCP')}>NCP</button>
              <button className={`course-button ${newCurriculum.type === 'AWS' ? 'selected' : ''}`} onClick={() => handleCourseChange('AWS')}>AWS</button>
            </div>
            <div className="curriculum-input-group">
              <label>시작일</label>
              <input className="start-date-input" type="date" name="startDate" value={newCurriculum.startDate} onChange={handleInputChange} />
            </div>
            <div className="curriculum-input-group">
              <label>종료일</label>
              <input className="end-date-input" type="date" name="endDate" value={newCurriculum.endDate} onChange={handleInputChange} />
            </div>
            <div className="curriculum-input-group">
              <label>기수 색상</label>
              <div className="color-input-wrapper">
                <input
                  className="color-input"
                  type="text"
                  name="color"
                  value={newCurriculum.color} // NCP나 AWS 클릭 시 과정명이 기수 색상 필드에 표시됩니다.
                  readOnly
                />
                <div
                  className="color-input-select"
                  onClick={() => setIsColorPickerOpen(true)}
                >
                  <div className="color-box" style={{ backgroundColor: newCurriculum.color }}></div>
                </div>
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
              <select
                name="teacherId"
                value={newCurriculum.teacherId}
                onChange={handleInputChange}
                className="teacher-select"
              >
                <option value="">선택 안 함</option>
                {teachers.length > 0 ? (
                  teachers.map(teacher => (
                    <option key={teacher.id} value={teacher.id}>
                      {teacher.name}
                    </option>
                  ))
                ) : (
                  <option disabled>강사 정보 없음</option>
                )}
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
