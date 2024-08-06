import React from 'react';
import './MessageBox.css';
const MessageBox = () => {
  return (
    <section className="message-box-wrapper1">
      <div className="message-item1">
        <div className="message-content1">
          <h2>1:1 문의 내역</h2>
          <div className="message-icon1">
            <div className="message-count1">12</div>
            <i className="fa-regular fa-envelope"></i>
          </div>
          <a href="#" className="view-more1">자세히 보기 &gt;</a>
        </div>
      </div>
      <div className="message-item1">
        <div className="message-content2">
          <h2>매니저 공지사항</h2>
          <div className="teacher-notice-container">
            <div className="teacher-notice">
              <div className="teacher-manager-notice-state">
                긴급
              </div>
              <a href="#">공지사항</a>
              <div>2024.08.01</div>
            </div>
          </div>
          <div className="teacher-notice-container">
            <div className="teacher-notice">
              <div className="teacher-manager-notice-state">
                긴급
              </div>
              <a href="#">공지사항</a>
              <div>2024.08.01</div>
            </div>
          </div>
          <div className="teacher-notice-container">
            <div className="teacher-notice">
              <div className="teacher-manager-notice-state">
                긴급
              </div>
              <a href="#">공지사항</a>
              <div>2024.08.01</div>
            </div>
          </div>
          <div className="teacher-notice-container">
            <div className="teacher-notice">
              <div className="teacher-manager-notice-state">
                긴급
              </div>
              <a href="#">공지사항</a>
              <div>2024.08.01</div>
            </div>
          </div>
          <a href="#" className="view-more1">자세히 보기 &gt;</a>
        </div>
      </div>
    </section>
  );
};

export default MessageBox;
