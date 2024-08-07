import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import './ManagerHeader.css';

const ManagerHeader = () => {
    const [openDropdown, setOpenDropdown] = useState(null);

    const toggleDropdown = (dropdown) => {
        if (openDropdown === dropdown) {
            setOpenDropdown(null);
        } else {
            setOpenDropdown(dropdown);
        }
    };

    return (
        <header id="header">
            <div className="h-inner">
                <div className="h-left">
                    <h1 className="h-logo">
                        <a className="navbar-brand" href="/">
                            <img src="/images/logo/HomeLearn_Manager_Header_Logo.png" alt="로고" />
                        </a>
                    </h1>
                </div>

                <div className="h-right">
                    <ul className="h-gnb_items">
                        <li>
                            <NavLink to="/managers">
                                <span>
                                    <i className="fa-solid fa-clipboard-list"></i>
                                </span>
                            </NavLink>
                        </li>

                        <div className="h-alarm">
                            <div className="h-alarm_btn" onClick={() => toggleDropdown('alarm')}>
                                <li>
                                    <a href="#">
                                        <span>
                                            <i className="fa-solid fa-bell"></i>
                                        </span>
                                        <span className="h-alarm_count"></span>
                                    </a>
                                </li>
                            </div>

                            <ul id="h-alarm_list" className={`h-alarm_list ${openDropdown === 'alarm' ? 'open' : ''}`}>
                                <div id="alarm_list">
                                    <li>
                                        <span>알림 예시입니다.</span>
                                    </li>
                                    <li>
                                        <span>알림 예시입니다.</span>
                                    </li>
                                    <li>
                                        <span>알림 예시입니다.</span>
                                    </li>
                                </div>
                            </ul>
                        </div>
                    </ul>

                    <div className="h-profile_box">
                        <div className={`h-profile ${openDropdown === 'profile' ? 'active' : ''}`} onClick={() => toggleDropdown('profile')}>
                            <div>
                                <img className="h-profile_img" src="/" alt="프로필"/>
                            </div>
                            <span className="h-profile_manager_name">매니저</span>
                            <i className="fa-solid fa-caret-down"></i>
                        </div>

                        <ul className={`h-profile_menu ${openDropdown === 'profile' ? 'open' : ''}`}>
                            <li>
                                <a href="/managers">마이페이지</a>
                            </li>
                            <li>
                                <a href="/">로그아웃</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default ManagerHeader;