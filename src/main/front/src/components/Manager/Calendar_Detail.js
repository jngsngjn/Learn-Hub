import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './Calendar_Detail.css';
import Calendar from './Calendar';

const CalendarDetail = () => {
  const { eventId } = useParams();
  const navigate = useNavigate();
  const [event, setEvent] = useState(null);

  useEffect(() => {
    const storedEvents = JSON.parse(localStorage.getItem('calendarEvents')) || [];
    const foundEvent = storedEvents.find((e) => e.id === Number(eventId));
    setEvent(foundEvent);
  }, [eventId]);

  if (!event) {
    return <div>이벤트를 찾을 수 없습니다.</div>;
  }

  const handleDeleteEvent = () => {
    const storedEvents = JSON.parse(localStorage.getItem('calendarEvents')) || [];
    const updatedEvents = storedEvents.filter((e) => e.id !== event.id);
    localStorage.setItem('calendarEvents', JSON.stringify(updatedEvents));
    navigate('/managers');
  };

  return (
    <div className="calendar-detail-page">
      <div className="calendar-detail-sidebar">
        <Calendar /> {/* Calendar 컴포넌트 추가 */}
      </div>
      <div className="calendar-detail-container">
        <div className="calendar-detail-header">
          <h2>일정 관리</h2>
          <button onClick={() => navigate(-1)}>돌아가기</button>
        </div>
        <div className="calendar-detail-content">
          <h3>{event.title}</h3>
          <p>
            <strong>시작일:</strong> {new Date(event.start).toLocaleDateString()}
          </p>
          <p>
            <strong>종료일:</strong> {new Date(event.end).toLocaleDateString()}
          </p>
          <p>
            <strong>색상:</strong> <span style={{ backgroundColor: event.color, padding: '0 10px' }}>{event.color}</span>
          </p>
          <div className="calendar-detail-actions">
            <button onClick={handleDeleteEvent}>삭제</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CalendarDetail;
