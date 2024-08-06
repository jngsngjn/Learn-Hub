import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { CirclePicker } from 'react-color';
import axios from 'axios';
import swal from 'sweetalert';
import './Curriculum_Detail.css';

const CurriculumDetail = () => {
  const { id } = useParams(); // useParams 훅을 통해 매개변수 가져오기
  const [curriculum, setCurriculum] = useState({
    id: '',
    type: '',
    startDate: '',
    endDate: '',
    color: '',
    teacherId: '',
    teacherName: '',
    attendance: 0,
    total: 0,
  });
  const [isColorPickerOpen, setIsColorPickerOpen] = useState(false);

  const getToken = () => localStorage.getItem('access-token');

  useEffect(() => {
    const fetchCurriculum = async () => {
      try {
        const token = getToken();
        const response = await axios.get(`/managers/curriculum/${id}/basic`, {
          headers: { access: token },
        });
        setCurriculum(response.data);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchCurriculum();
  }, [id]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCurriculum({ ...curriculum, [name]: value });
  };

  const handleColorChange = (color) => {
    setCurriculum({ ...curriculum, color: color.hex });
    setIsColorPickerOpen(false);
  };

  const handleUpdateCurriculum = async () => {
    const updatedCurriculum = {
      teacherId: curriculum.teacherId,
      startDate: curriculum.startDate,
      endDate: curriculum.endDate,
      color: curriculum.color,
    };

    if (new Date(updatedCurriculum.endDate) <= new Date(updatedCurriculum.startDate)) {
      swal('수정 실패', '종료일은 시작일 이후여야 합니다.', 'warning');
      return;
    }

    try {
      const token = getToken();
      const response = await axios.patch(`/managers/manage-curriculums/${id}`, updatedCurriculum, {
        headers: { access: token },
      });

      if (response.status === 200) {
        swal('수정 성공', '교육 과정이 성공적으로 수정되었습니다.', 'success');
      } else {
        console.error('교육 과정 수정 실패');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div className="curriculum-detail">
      <h1>교육 과정 상세 정보</h1>
      <div className="curriculum-info">
        <div className="info-group">
          <label>교육 과정</label>
          <span>{curriculum.type}</span>
        </div>
        <div className="info-group">
          <label>강사 이름</label>
          <span>{curriculum.teacherName}</span>
        </div>
        <div className="info-group">
          <label>출석 인원</label>
          <span>{curriculum.attendance} / {curriculum.total}</span>
        </div>
      </div>
      <div className="edit-group">
        <label>시작일</label>
        <input type="date" name="startDate" value={curriculum.startDate} onChange={handleInputChange} />
      </div>
      <div className="edit-group">
        <label>종료일</label>
        <input type="date" name="endDate" value={curriculum.endDate} onChange={handleInputChange} />
      </div>
      <div className="edit-group">
        <label>기수 색상</label>
        <input type="text" value={curriculum.color} readOnly />
        <div className="color-input-select" onClick={() => setIsColorPickerOpen(true)}>
          <div className="color-box" style={{ backgroundColor: curriculum.color }}></div>
        </div>
      </div>
      {isColorPickerOpen && (
        <div className="color-picker-modal-overlay" onClick={() => setIsColorPickerOpen(false)}>
          <div className="color-picker-modal-content" onClick={(e) => e.stopPropagation()}>
            <CirclePicker
              color={curriculum.color}
              onChangeComplete={handleColorChange}
            />
          </div>
        </div>
      )}
      <div className="edit-group">
        <label>강사 ID</label>
        <input type="text" name="teacherId" value={curriculum.teacherId} onChange={handleInputChange} />
      </div>
      <button className="update-button" onClick={handleUpdateCurriculum}>수정하기</button>
    </div>
  );
};

export default CurriculumDetail;
