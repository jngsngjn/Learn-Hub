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
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isColorPickerOpen, setIsColorPickerOpen] = useState(false);
  const [newCurriculum, setNewCurriculum] = useState({
    type: "",
    startDate: "",
    endDate: "",
    color: "",
    teacherId: "",
  });

  // 토큰을 가져오기
  const getToken = () => localStorage.getItem("access-token");

  // 입력 값 변경 시
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewCurriculum({ ...newCurriculum, [name]: value });
  };

  // 교육 과정 변경 시
  const handleCourseChange = (courseName) => {
    setNewCurriculum({ ...newCurriculum, type: courseName });
  };

  // 색상 선택
  const handleColorChange = (color) => {
    setNewCurriculum({ ...newCurriculum, color: color.hex });
    setIsColorPickerOpen(false);
  };

  // 과정 추가
  const handleAddCurriculum = async () => {
    const newCurriculumItem = {
      type: newCurriculum.type,
      startDate: newCurriculum.startDate,
      endDate: newCurriculum.endDate,
      color: newCurriculum.color,
      teacherId: newCurriculum.teacherId,
    };

    // 종료일이 시작일보다 빠르거나 같으면 swal 메시지
    if (
      new Date(newCurriculumItem.endDate) <=
      new Date(newCurriculumItem.startDate)
    ) {
      swal("등록 실패", "종료일은 시작일 이후여야 합니다.", "warning");
      return;
    }

    try {
      const token = getToken();
      const response = await axios.post(
        "/managers/manage-curriculums/enroll",
        newCurriculumItem,
        {
          headers: { access: token },
        }
      );

      if (response.status === 200) {
        setIsModalOpen(false);
        fetchCurriculums(newCurriculum.type); // 목록 갱신
      } else {
        console.error("교육 과정 등록 실패");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  // NCP AWS 교육 과정 목록 가져오기
  const fetchCurriculums = async (type) => {
    try {
      const token = getToken();
      const response = await axios.get(`/managers/manage-curriculums/${type}`, {
        headers: { access: token },
      });

      if (type === "NCP") {
        setNcpCurriculums(response.data || []);
      } else if (type === "AWS") {
        setAwsCurriculums(response.data || []);
      }
    } catch (error) {
      console.error("에러 메시지:", error);
    }
  };

  // 과정 목록을 가져옴
  useEffect(() => {
    fetchCurriculums("NCP");
    fetchCurriculums("AWS");
  }, []);

  // 과정 목록 렌더링
  const renderCurriculumList = (curriculums) =>
    curriculums.map((curriculum) => (
      <div key={curriculum.id} className="curriculum-card">
        <div className="curriculum-header">
          <span
            className="curriculum-th"
            style={{ backgroundColor: curriculum.color }}
          >
            {curriculum.th}기
          </span>
          <Link
            to={`/managers/manage-curriculums/${curriculum.id}`}
            className="curriculum-type"
          >
            {curriculum.name}
          </Link>
        </div>
        <div className="curriculum-footer">
          <span className="curriculum-teacher">
            강사 {curriculum.teacherName}
          </span>
          <span className="curriculum-students">
            <i className="fas fa-user"></i> {curriculum.attendance} /{" "}
            {curriculum.total}
          </span>
        </div>
      </div>
    ));

  return (
    <div className="curriculum-management">
      <h1>교육 과정</h1>
      <div className="button-container">
        <button
          className="curriculum-add-button"
          onClick={() => setIsModalOpen(true)}
        >
          교육 과정 추가
        </button>
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
            <button
              className="modal-close"
              onClick={() => setIsModalOpen(false)}
            >
              ×
            </button>
            <span className="curriculum-submit">교육 과정 등록</span>
            <div className="course-selection">
              <button
                className={`course-button ${
                  newCurriculum.type === "NCP" ? "selected" : ""
                }`}
                onClick={() => handleCourseChange("NCP")}
              >
                NCP
              </button>
              <button
                className={`course-button ${
                  newCurriculum.type === "AWS" ? "selected" : ""
                }`}
                onClick={() => handleCourseChange("AWS")}
              >
                AWS
              </button>
            </div>
            <div className="curriculum-input-group">
              <label>시작일</label>
              <input
                className="curriculum-start-date-input"
                type="date"
                name="startDate"
                value={newCurriculum.startDate}
                onChange={handleInputChange}
              />
            </div>
            <div className="curriculum-input-group">
              <label>종료일</label>
              <input
                className="curriculum-end-date-input"
                type="date"
                name="endDate"
                value={newCurriculum.endDate}
                onChange={handleInputChange}
              />
            </div>
            <div className="curriculum-input-group">
              <label>기수 색상</label>
              <div className="color-input-wrapper">
                <input
                  className="color-input"
                  type="text"
                  name="color"
                  value={newCurriculum.color}
                  readOnly
                />
                <div
                  className="color-input-select"
                  onClick={() => setIsColorPickerOpen(true)}
                >
                  <div
                    className="color-box"
                    style={{ backgroundColor: newCurriculum.color }}
                  ></div>
                </div>
              </div>
            </div>
            {isColorPickerOpen && (
              <div
                className="color-picker-modal-overlay"
                onClick={() => setIsColorPickerOpen(false)}
              >
                <div
                  className="color-picker-modal-content"
                  onClick={(e) => e.stopPropagation()}
                >
                  <CirclePicker
                    color={newCurriculum.color}
                    onChangeComplete={handleColorChange}
                    colors={[
                      "#F3C41E",
                      "#F58D11",
                      "#B85B27",
                      "#A90C57",
                      "#F45CE5",
                      "#AE59F0",
                      "#0A8735",
                      "#6F961E",
                      "#19E308",
                      "#1D1AA6",
                      "#20CFF5",
                      "#98B3E5",
                    ]}
                  />
                </div>
              </div>
            )}
            <div className="curriculum-input-group">
              <label>강사</label>
              <input
                type="text"
                name="teacherId"
                value={newCurriculum.teacherId}
                onChange={handleInputChange}
              />
            </div>
            <div className="modal-actions">
              <button className="modal-button" onClick={handleAddCurriculum}>
                교육 과정 등록
              </button>
              <button
                className="modal-button"
                onClick={() => setIsModalOpen(false)}
              >
                등록 취소
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default CurriculumManagement;
