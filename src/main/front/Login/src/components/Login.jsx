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
    navigate('/email');
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit}>
        <h2 className="login-title">로그인</h2>
        <div className="login-input-group">
          <span className="login-id-title">아이디</span>
          <input
            className="login-input"
            type="text"
            placeholder="아이디 입력"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="login-input-group">
          <span className="login-pw-title">비밀번호</span>
          <input
            className="login-input"
            type="password"
            placeholder="비밀번호 입력"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="login-checkbox-group">
          <label className="save-id">
            <input
              type="checkbox"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
            />
            아이디 저장
          </label>
          <div className="user-found">
            <span className="user-found-id">아이디 찾기 </span>/
            <span className="user-found-pw">비밀번호 찾기</span>
          </div>
        </div>
        <div className="login-button-group">
          <button className="login-button" type="submit">로그인</button>
          <button className="login-signup-button" type="button" onClick={handleSignup}>회원가입</button>
        </div>
      </form>
    </div>
  );
}

export default Login;
