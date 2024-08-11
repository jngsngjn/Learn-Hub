import { useEffect, useState } from "react";
import StudentModal from "../../components/Modal/StudentModal/StudentModal";
import "./StudentFreeBoard.css";
import { useNavigate } from "react-router-dom";
import useGetFetch from "../../hooks/useGetFetch";

const StudentFreeBoard = () => {
  const navigate = useNavigate();
  const id = "boardId";

  const { data: freeboard, error: freeboardError } = useGetFetch(
    "/data/student/mainLecture/freeBoard.json",
    []
  );

  console.log(freeboard);
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

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

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
        <thead>
          <tr className="student_freeboard_table_header">
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>답변 수</th>
          </tr>
        </thead>
        <tbody>
          {freeboard.map((el, idx) => (
            <tr className="student_freeboard_table_body" key={idx}>
              <td>{idx + 1}</td>
              <td onClick={() => navigate(`/students/freeboard/${el.boardId}`)}>
                {el.title}
              </td>
              <td>{el.author}</td>
              <td>{el.createDate}</td>
              <td>{el.commentCount}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <StudentModal
        isOpen={isModalOpen}
        closeModal={closeModal}
        formData={formData}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        handleFileChange={handleFileChange}
        selectedFileName={selectedFileName}
        modalName="게시글 등록"
        contentTitle="제목"
        contentBody="내용"
        contentFile="이미지 첨부"
        url="/students/boards"
      />
    </div>
  );
};

export default StudentFreeBoard;
