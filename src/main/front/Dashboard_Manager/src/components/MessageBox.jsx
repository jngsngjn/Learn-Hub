import React from 'react';
import './MessageBox.css';

const MessageBox = () => {
  return (
    <div>
      <h2>1:1 문의 내역</h2>
      <div className="message-item">
        <p>문의 제목</p>
        <p>내용 미리보기</p>
        <p>날짜</p>
      </div>

    </div>
  );
};

export default MessageBox;
