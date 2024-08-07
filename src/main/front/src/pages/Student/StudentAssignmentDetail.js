import React, { useState } from "react";
import SubmitModal from "../../components/SubmitModal/SubmitModal";
import "./StudentAssignmentDetail.css";
import useGetFetch from "../../hooks/useGetFetch";

const StudentAssignmentDetail = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({ title: "", content: "" });
  const [selectedFileName, setSelectedFileName] = useState("");

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setSelectedFileName(file ? file.name : "");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("제출 내용:", formData, selectedFileName);
    closeModal();
  };

  const { data: assignment, error: assignmentError } = useGetFetch(
    "/data/student/mainLecture/submittedAssginment.json",
    []
  );

  if (assignmentError) {
    return <div>Error: {assignmentError.message}</div>;
  }

  return (
    <div className="student_assignment_detail_main_container">
      <h1 className="student_assignment_detail_page_title">과제</h1>
      {/* 강사의 과제 공지 내용 */}
      <div className="student_assignment_detail_notice_box">
        <div className="student_assignment_detail_notice_title_box">
          <h3 className="student_assginment_detail_notice_title">
            Spring 게시판 제출 과제
          </h3>
          <a href="/assignment_files/응.없어~.zip" download>
            <span className="student_assignment_detail_notice_file">
              과제 자료.zip
            </span>
          </a>
        </div>
        <p className="student_assignment_detail_notice_content">
          Sprign MVC로 CRUD 기능을 구현해서 제출하세요
        </p>
        <div className="student_assignment_detail_notice_date_box">
          <span className="student_assignment_detail_notice_deadline">
            <span className="date_color">2024-08-31</span>까지
          </span>
          <span className="student_assignment_detail_notice_write_date">
            <span className="date_color">2024-08-01</span> 까지
          </span>
        </div>
      </div>
      {/* 학생의 과제 제출 여부 및 제출한 과제 페이지 */}
      {assignment && assignment.id ? (
        <div className="student_submit_assignment_detail_box">
          <div className="student_submit_assignment_detail_title_box">
            <h1 className="student_submit_assignment_detail_title">
              {assignment.title}
            </h1>
            <a href={assignment.filePath} download={assignment.fileName}>
              <span className="student_submit_assignment_detail_file">
                {assignment.fileName}
              </span>
            </a>
          </div>
          <p className="student_submit_assignment_detail_content">
            {assignment.content}
          </p>
          <span className="student_submit_assignment_detail_date">
            <span className="date_color">{assignment.writeDate}</span> 제출
          </span>
        </div>
      ) : (
        <div className="student_non_submit_assignment_detail_box">
          <h1 className="student_non_submit_assignment_detail_title">
            아직 과제를 제출하지 않았습니다.
          </h1>
          <button className="assignment_submit_button" onClick={openModal}>
            제출하기
          </button>
        </div>
      )}
      {/* 피드백 요소 */}
      <div className="student_submit_assignment_feedback_box">
        <p className="student_submit_assignment_feedback_content">
          아주 아주 잘 했습니다. 당신은 1등 입니다
        </p>
        <div className="student_submit_assignment_feedback_date_box">
          <span className="student_submit_assignment_feedback_date date_color">
            2024-08-11
          </span>{" "}
          까지
        </div>
      </div>

      <SubmitModal
        isOpen={isModalOpen}
        closeModal={closeModal}
        formData={formData}
        selectedFileName={selectedFileName}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        handleFileChange={handleFileChange}
      />
    </div>
  );
};

export default StudentAssignmentDetail;
