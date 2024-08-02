import React from 'react';
import { Link } from 'react-router-dom';
import './Nav.css';

const Nav = () => {
  return (
    <div className="nav_container">
      <h3>안녕하세요? Nav입니다</h3>
      <nav>
        <Link to="/login">로그인</Link>
      </nav>
    </div>
  );
};

export default Nav;
