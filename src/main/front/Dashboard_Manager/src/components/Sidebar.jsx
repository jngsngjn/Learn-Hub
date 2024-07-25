import React, { useState, useEffect } from 'react';
import './Sidebar.css';

const Sidebar = () => {
  const [currentDate, setCurrentDate] = useState('');
  const [dropdownOpen, setDropdownOpen] = useState(false);

  useEffect(() => {
    const date = new Date();
    const formattedDate = `${date.getFullYear()}.${String(date.getMonth() + 1).padStart(2, '0')}.${String(date.getDate()).padStart(2, '0')} (${['일', '월', '화', '수', '목', '금', '토'][date.getDay()]})`;
    setCurrentDate(formattedDate);
  }, []);

  const toggleDropdown = () => {
    setDropdownOpen(!dropdownOpen);
  };

  return (
    <div className="sidebar">
      <div className="date-container">
        <span className="date">{currentDate}</span>
      </div>
      <h2>대시보드</h2>
      <ul className="menu">
        <li>교육 과정</li>
        <li>학생 관리</li>
        <li>강사 관리</li>
        <li>공지사항</li>
        <li className="dropdown">
          <div className="dropdown-header" onClick={toggleDropdown}>
            문의
            <span className={`dropdown-arrow ${dropdownOpen ? 'open' : ''}`}>▼</span>
          </div>
          <ul className={`submenu ${dropdownOpen ? 'open' : ''}`}>
            <li>학생 문의</li>
            <li>강사 문의</li>
          </ul>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
