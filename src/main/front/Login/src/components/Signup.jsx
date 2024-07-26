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
              className="signup-input"
              type="text"
              id="name"
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
              className="signup-input"
              type="text"
              id="phone"
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
              className="signup-input"
              type="email"
              id="email"
              value={email}
              readOnly
            />
            <i className="fa-solid fa-lock signup-lock-icon"></i>
          </div>
        </div>
        <div className="signup-input-group">
          <label htmlFor="username" className="signup-label">아이디</label>
          <input
            className="signup-input"
            type="text"
            id="username"
            placeholder="아이디 입력"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="signup-input-group">
          <label htmlFor="password" className="signup-label">비밀번호</label>
          <input
            className="signup-input"
            type="password"
            id="password"
            placeholder="비밀번호 입력"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="signup-input-group">
          <label htmlFor="confirm-password" className="signup-label">비밀번호 확인</label>
          <input
            className="signup-input"
            type="password"
            id="confirm-password"
            placeholder="비밀번호 확인"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          <div className="password-match-message">
            {confirmPassword && (
              <span className={passwordMatch ? 'match' : 'no-match'}>
                {passwordMatch ? '비밀번호가 일치합니다' : '비밀번호가 일치하지 않습니다'}
              </span>
            )}
          </div>
        </div>
        <div className="signup-button-group">
          <button className="signup-prev-button" type="button" onClick={handlePreviousStep}>이전 단계</button>
          <button className="signup-button" type="submit">회원가입</button>
        </div>
      </form>
    </div>
  );
}

export default Signup;
