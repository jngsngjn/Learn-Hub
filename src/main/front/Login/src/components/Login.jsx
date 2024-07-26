import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('로그인 시도:', { username, password, rememberMe });
  };

  const handleSignup = () => {
    navigate('/signup');
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit}>
        <h2>로그인</h2>
        <div className="input-group">
           <span className="login-id-title">아이디</span>
          <input
            type="text"
            placeholder="아이디"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="input-group">
          <span className="login-pw-title">비밀번호</span>
          <input
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="checkbox-group">
          <label>
            <input
              type="checkbox"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
            />
            아이디 저장
          </label>
          <span>아이디 찾기 / 비밀번호 찾기</span>
        </div>
        <div className="button-group">
          <button type="submit" className="login-button">로그인</button>
          <button type="button" className="signup-button-login" onClick={handleSignup}>회원가입</button>
        </div>
      </form>
    </div>
  );
}

export default Login;
