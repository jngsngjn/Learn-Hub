import React, { useState } from 'react';
import './Signup.css';

function Signup() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      alert('비밀번호가 일치하지 않습니다.');
      return;
    }
    console.log('회원가입 시도:', { username, password });
  };

  return (
    <div className="signup-container">
      <form onSubmit={handleSubmit}>
        <h2>회원가입</h2>
        <div className="input-group">
          <label htmlFor="username">아이디</label>
          <input
            type="text"
            id="username"
            placeholder="아이디 입력"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="input-group">
          <label htmlFor="password">비밀번호</label>
          <input
            type="password"
            id="password"
            placeholder="비밀번호 입력"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="input-group">
          <label htmlFor="confirm-password">비밀번호 확인</label>
          <input
            type="password"
            id="confirm-password"
            placeholder="비밀번호 확인"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </div>
        <div className="button-group">
          <button type="submit" className="signup-button">회원가입</button>
        </div>
      </form>
    </div>
  );
}

export default Signup;
