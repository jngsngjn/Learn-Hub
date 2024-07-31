import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
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
    <div className="sidebar_container">
      <div className="sidebar_content">
        <div className="date-container">
          <span className="date">{currentDate}</span>
        </div>
        <div className="sidebar_link">
          <ul className="menu">
            <li><Link to="/manager" className="sidebar-link">대시보드</Link></li>
            <li><Link to="/manager/manager-curriculum" className="sidebar-link">교육 과정</Link></li>
            <li><Link to="/manager/student-management" className="sidebar-link">학생 관리</Link></li>
            <li><Link to="/manager/teacher-management" className="sidebar-link">강사 관리</Link></li>
            <li><Link to="/manager/notice" className="sidebar-link">공지사항</Link></li>
            <li className="dropdown">
              <div className="dropdown-header" onClick={toggleDropdown}>
                문의
                <span className={`dropdown-arrow ${dropdownOpen ? 'open' : ''}`}>▼</span>
              </div>
              <ul className={`submenu ${dropdownOpen ? 'open' : ''}`}>
                <li><Link to="/manager/contact-student" className="sidebar-link">학생 문의</Link></li>
                <li><Link to="/manager/contact-teacher" className="sidebar-link">강사 문의</Link></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
      <div className="sidebar_line"></div>
    </div>
  );
};

export default Sidebar;
