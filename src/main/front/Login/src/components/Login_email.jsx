import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login_email.css';

function LoginEmail() {
  const [email, setEmail] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const navigate = useNavigate();

  const handleEmailVerification = () => {
    console.log('이메일 인증 요청:', { email });
  };

  const handleCodeVerification = () => {
    console.log('코드 인증 요청:', { verificationCode });
  };

  const handleNextStep = () => {
    navigate('/signup');
  };

  const handlePreviousStep = () => {
    navigate('/');
  };

  return (
    <div className="login-email-container">
      <form>
        <h2 className="login-email-title">회원가입</h2>
        <h3 className="login-email-subtitle">이메일 인증</h3>
        <div className="login-email-input-group">
          <label className="login-email-label">이메일</label>
          <div className="login-email-input-wrapper">
            <input
              type="email"
              className="login-email-input"
              placeholder="이메일 입력"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <button
              type="button"
              className="login-email-verify-button"
              onClick={handleEmailVerification}
            >
              인증 하기
            </button>
          </div>
        </div>
        <div className="login-email-input-group">
          <label className="login-email-label">인증코드</label>
          <div className="login-email-input-wrapper">
            <input
              type="text"
              className="login-email-input"
              placeholder="인증코드 입력"
              value={verificationCode}
              onChange={(e) => setVerificationCode(e.target.value)}
            />
            <button
              type="button"
              className="login-email-verify-button"
              onClick={handleCodeVerification}
            >
              인증 확인
            </button>
          </div>
        </div>
        <div className="login-email-button-group">
          <button
            type="button"
            className="login-email-prev-button"
            onClick={handlePreviousStep}
          >
            이전
          </button>
          <button
            type="button"
            className="login-email-next-button"
            onClick={handleNextStep}
          >
            다음
          </button>
        </div>
      </form>
    </div>
  );
}

export default LoginEmail;
