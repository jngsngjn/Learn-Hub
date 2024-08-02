import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import swal from 'sweetalert';
import axios from 'axios';
import './Login_email.css';

function LoginEmail() {
  const [email, setEmail] = useState('');
  const [verificationCode, setVerificationCode] = useState('');
  const navigate = useNavigate();

  const handleNextStep = async () => {
    try {
      const response = await axios.post('http://localhost:8080/code-verify', { email, code: verificationCode });
      if (response.status === 200) {
        const { name, phone } = response.data;
        navigate('/signup', { state: { email, name, phone } });
      } else {
        swal({
          icon: 'error',
          title: '인증 오류',
          text: '인증 코드가 올바르지 않습니다.',
        });
      }
    } catch (error) {
      console.error('인증 오류:', error);
      swal({
        icon: 'error',
        title: '오류 발생',
        text: '코드 인증 중 오류가 발생했습니다.',
      });
    }
  };

  const handlePreviousStep = () => {
    navigate('/login');
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
              className="login-email-input"
              type="email"
              placeholder="이메일 입력"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
        </div>
        <div className="login-email-input-group">
          <label className="login-email-label">인증코드</label>
          <div className="login-email-input-wrapper">
            <input
              className="login-email-input"
              type="text"
              placeholder="인증코드 입력"
              value={verificationCode}
              onChange={(e) => setVerificationCode(e.target.value)}
            />
          </div>
        </div>
        <div className="login-email-button-group">
          <button
            className="login-email-prev-button"
            type="button"
            onClick={handlePreviousStep}
          >
            이전
          </button>
          <button
            className="login-email-next-button"
            type="button"
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
