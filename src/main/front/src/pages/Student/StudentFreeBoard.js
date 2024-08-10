import { useState } from "react";
import StudentModal from "../../components/Modal/StudentModal/StudentModal";
import "./StudentFreeBoard.css";

const StudentFreeBoard = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    title: "",
    content: "",
    file: null,
  });
  const [selectedFileName, setSelectedFileName] = useState("");

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "file") {
      setFormData((prevState) => ({ ...prevState, [name]: files[0] }));
    } else {
      setFormData((prevState) => ({ ...prevState, [name]: value }));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("모달 데이터 : " + formData);
    closeModal();
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedFileName(file.name);
      handleChange(e);
    }
  };
  return (
    <div className="main_container">
      <div className="student_freeboard_page_title_box">
        <h1 className="student_freeboard_page_title">자유 게시판</h1>
        <button className="regist_freeboard_writing" onClick={openModal}>
          글 등록
        </button>
      </div>
      {/* 자유 게시판 글 목록 */}
      <table className="student_freeboard_table">
        <tr className="student_freeboard_table_header">
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>답변 수</th>
        </tr>
        <tr className="student_freeboard_table_body">
          <th>1</th>
          <th>다들 수업 잘 따라 갈 수 있나요</th>
          <th>김수정</th>
          <th>2024-08-07</th>
          <th>3</th>
        </tr>
      </table>
      <StudentModal
        isOpen={isModalOpen}
        closeModal={closeModal}
        formData={formData}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        handleFileChange={handleFileChange}
        selectedFileName={formData.selectedFileName}
      />
    </div>
  );
};

export default StudentFreeBoard;
