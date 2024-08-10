import React, { useState } from "react";
import "./StudentModal.css";

const StudentModal = ({
  isOpen,
  closeModal,
  formData,
  selectedFileName,
  handleChange,
  handleSubmit,
  handleFileChange,
}) => {
  if (!isOpen) return null;

  return (
    <div className="student_modal show">
      <div className="student_modal_content">
        <span className="close" onClick={closeModal}>
          &times;
        </span>
        <h1 className="student_modal_title">과제 제출</h1>
        <form onSubmit={handleSubmit} className="student_modal_form_body">
          <label>
            <p className="student_modal_name_tag">제목</p>
            <input
              type="text"
              name="title"
              value={formData.title}
              onChange={handleChange}
              className="student_modal_input_title"
            />
          </label>
          <label>
            <p className="student_modal_content_tag">내용</p>
            <textarea
              name="content"
              value={formData.content}
              onChange={handleChange}
              className="student_modal_input_content"
            ></textarea>
          </label>
          <label className="student_modal_file_label">
            <p className="student_modal_file_tag">파일 첨부</p>
            <div className="student_modal_file_input_wrapper">
              <input
                type="text"
                readOnly
                value={selectedFileName}
                className="student_modal_input_file_display"
              />
              <label className="student_modal_file_button">
                파일 선택
                <input
                  type="file"
                  name="file"
                  onChange={handleFileChange}
                  className="student_modal_input_file"
                />
              </label>
            </div>
          </label>
          <div className="student_modal_submit_button_box">
            <button type="submit" className="student_modal_submit_button">
              과제 제출
            </button>
            <button
              type="button"
              className="student_modal_cancel_button"
              onClick={closeModal}
            >
              제출 취소
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default StudentModal;
