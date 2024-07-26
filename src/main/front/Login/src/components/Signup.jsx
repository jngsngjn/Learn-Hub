import React, { useState, useEffect } from 'react';
import './Signup.css';
import { useNavigate } from 'react-router-dom';

function Signup() {
  const [name, setName] = useState('안성민');
  const [phone, setPhone] = useState('010-9722-5739');
  const [email, setEmail] = useState('smahn4069@gmail.com');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [passwordMatch, setPasswordMatch] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    setPasswordMatch(password === confirmPassword);
  }, [password, confirmPassword]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!passwordMatch) {
      return;
    }
    console.log('회원가입 시도:', { name, phone, email, username, password });
  };

  const handlePreviousStep = () => {
    navigate('/email');
  };

  return (
    <div className="signup-container">
      <form onSubmit={handleSubmit}>
        <h2 className="signup-title">회원가입</h2>
        <div className="signup-input-group">
          <label htmlFor="name" className="signup-label">이름</label>
          <div className="signup-input-wrapper">
            <input
              type="text"
              id="name"
              className="signup-input"
              value={name}
              readOnly
            />
            <i className="fa-solid fa-lock signup-lock-icon"></i>
          </div>
        </div>
        <div className="signup-input-group">
          <label htmlFor="phone" className="signup-label">전화번호</label>
          <div className="signup-input-wrapper">
            <input
              type="text"
              id="phone"
              className="signup-input"
              value={phone}
              readOnly
            />
            <i className="fa-solid fa-lock signup-lock-icon"></i>
          </div>
        </div>
        <div className="signup-input-group">
          <label htmlFor="email" className="signup-label">이메일</label>
          <div className="signup-input-wrapper">
            <input
              type="email"
              id="email"
              className="signup-input"
              value={email}
              readOnly
            />
            <i className="fa-solid fa-lock signup-lock-icon"></i>
          </div>
        </div>
        <div className="signup-input-group">
          <label htmlFor="username" className="signup-label">아이디</label>
          <input
            type="text"
            id="username"
            className="signup-input"
            placeholder="아이디 입력"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="signup-input-group">
          <label htmlFor="password" className="signup-label">비밀번호</label>
          <input
            type="password"
            id="password"
            className="signup-input"
            placeholder="비밀번호 입력"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="signup-input-group">
          <label htmlFor="confirm-password" className="signup-label">비밀번호 확인</label>
          <input
            type="password"
            id="confirm-password"
            className="signup-input"
            placeholder="비밀번호 확인"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          {confirmPassword && (
            <div className={`password-match-message ${passwordMatch ? 'match' : 'no-match'}`}>
              {passwordMatch ? '비밀번호가 일치합니다' : '비밀번호가 일치하지 않습니다'}
            </div>
          )}
        </div>
        <div className="signup-button-group">
          <button type="button" className="signup-prev-button" onClick={handlePreviousStep}>이전 단계</button>
          <button type="submit" className="signup-button">회원가입</button>
        </div>
      </form>
    </div>
  );
}

export default Signup;
