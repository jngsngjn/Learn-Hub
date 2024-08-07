import React, { useState } from "react";
import "./StudentSubmitModal.css";

const StudentSubmitModal = ({
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
    <div className="modal show">
      <div className="modal_content">
        <span className="close" onClick={closeModal}>
          &times;
        </span>
        <h1 className="modal_title">과제 제출</h1>
        <form onSubmit={handleSubmit} className="modal_form_body">
          <label>
            <p className="modal_name_tag">제목</p>
            <input
              type="text"
              name="title"
              value={formData.title}
              onChange={handleChange}
              className="modal_input_title"
            />
          </label>
          <label>
            <p className="modal_content_tag">내용</p>
            <textarea
              name="content"
              value={formData.content}
              onChange={handleChange}
              className="modal_input_content"
            ></textarea>
          </label>
          <label className="modal_file_label">
            <p className="modal_file_tag">파일 첨부</p>
            <div className="modal_file_input_wrapper">
              <input
                type="text"
                readOnly
                value={selectedFileName}
                className="modal_input_file_display"
              />
              <label className="modal_file_button">
                파일 선택
                <input
                  type="file"
                  name="file"
                  onChange={handleFileChange}
                  className="modal_input_file"
                />
              </label>
            </div>
          </label>
          <div className="modal_submit_button_box">
            <button type="submit" className="modal_submit_button">
              과제 제출
            </button>
            <button
              type="button"
              className="modal_cancel_button"
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

export default StudentSubmitModal;
