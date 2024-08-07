import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './Calendar_Detail.css';
import Calendar from './Calendar';

const CalendarDetail = () => {
  const { eventId } = useParams();
  const navigate = useNavigate();
  const [event, setEvent] = useState(null);
  const [events, setEvents] = useState([]);
  const [selectedEvent, setSelectedEvent] = useState(null);
  const [editMode, setEditMode] = useState(false);
  const [selectedDate, setSelectedDate] = useState(null); // 추가: 선택된 날짜 상태

  const predefinedColors = ['#FF9999', '#99FF99', '#9999FF']; // 임시 색상 -> 백엔드 연도 필요

  // 로컬 스토리지에서 일정 불러오기
  useEffect(() => {
    const storedEvents = JSON.parse(localStorage.getItem('calendarEvents')) || [];
    setEvents(storedEvents);
    const foundEvent = storedEvents.find((e) => e.id === Number(eventId));
    setEvent(foundEvent);
    setSelectedEvent(foundEvent);
  }, [eventId]);

  // 특정 날짜 일정
  const getEventsForDate = (date) => {
    return events.filter(event =>
      new Date(event.start).toDateString() === date.toDateString() ||
      (new Date(event.start) <= date && new Date(event.end) >= date)
    );
  };

  if (!event) {
    return navigate('/managers');
  }

  // 일정 삭제
  const handleDeleteEvent = (id) => {
    const updatedEvents = events.filter((e) => e.id !== id);
    localStorage.setItem('calendarEvents', JSON.stringify(updatedEvents));
    setEvents(updatedEvents);
    if (id === selectedEvent.id) {
      setSelectedEvent(null); // 선택 취소
      setEditMode(false); // 수정 모드 해제
    }
  };

  // 일정 수정 모드
  const handleEditEvent = (evt) => {
    setSelectedEvent(evt);
    setEditMode(true);
  };

  // 일정 저장
  const handleSaveEvent = () => {
    const updatedEvents = events.map((e) => e.id === selectedEvent.id ? selectedEvent : e);
    localStorage.setItem('calendarEvents', JSON.stringify(updatedEvents));
    setEvents(updatedEvents);
    setEvent(selectedEvent);
    setEditMode(false);
  };

  // 입력 변경
  const handleChange = (e) => {
    const { name, value } = e.target;
    setSelectedEvent({ ...selectedEvent, [name]: value });
  };

  // 일정 제목 클릭
  const handleTitleClick = (evt) => {
    if (editMode) {
      setEditMode(false);
    } else if (selectedEvent && selectedEvent.id === evt.id) {
      setSelectedEvent(null); // 선택 취소
    } else {
      setSelectedEvent(evt); // 일정 선택
    }
  };

  // 날짜 클릭 핸들러 추가
  const handleDayClick = (date) => {
    setSelectedDate(date);
    setSelectedEvent(null);
    setEditMode(false);
  };

  return (
    <div className="detail-calendar-detail-page">
      <div className="detail-calendar-detail-sidebar">
        <Calendar events={events} onDeleteEvent={handleDeleteEvent} onDayClick={handleDayClick} />
      </div>
      <div className="detail-calendar-detail-container">
        <div className="detail-calendar-detail-header">
          <h2>일정 관리</h2>
          <button onClick={() => navigate("/managers")}>돌아가기</button>
        </div>
        <div className="detail-calendar-detail-content">
          <div className="detail-all-events">
            <h3>해당 날짜의 일정</h3>
            <ul>
              {getEventsForDate(selectedDate || new Date(event.start)).map((evt) => (
                <li key={evt.id}>
                  <div className="detail-event-title-container">
                    <span className="detail-event-color" style={{ backgroundColor: evt.color }}></span>
                    <span className="detail-event-title" onClick={() => handleTitleClick(evt)}>{evt.title}</span>
                    <div className="detail-event-actions">
                      <button onClick={() => handleEditEvent(evt)}>
                        <i className="fas fa-pencil-alt"></i>
                      </button>
                      <button onClick={() => handleDeleteEvent(evt.id)}>
                        <i className="fas fa-trash-alt"></i>
                      </button>
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          </div>

          {(selectedEvent || editMode) && (
            <div className="detail-event-info">
              {editMode && (
                <div className="detail-event-color-picker">
                  {predefinedColors.map(color => (
                    <div
                      key={color}
                      className={`color-option ${selectedEvent.color === color ? 'selected' : ''}`}
                      style={{ backgroundColor: color }}
                      onClick={() => setSelectedEvent({ ...selectedEvent, color })}
                    ></div>
                  ))}
                </div>
              )}
              {editMode ? (
                <>
                  <input
                    type="text"
                    name="title"
                    value={selectedEvent.title}
                    onChange={handleChange}
                    className="detail-event-title-input"
                  />
                  <input
                    type="date"
                    name="start"
                    value={selectedEvent.start}
                    onChange={handleChange}
                    className="detail-event-date-input"
                  />
                  <input
                    type="date"
                    name="end"
                    value={selectedEvent.end}
                    onChange={handleChange}
                    className="detail-event-date-input"
                  />
                  <div className="detail-event-actions-edit">
                    <button onClick={handleSaveEvent}>저장<i className="fas fa-save"></i></button>
                    <button onClick={() => handleDeleteEvent(event.id)}>삭제<i className="fas fa-trash-alt"></i></button>
                  </div>
                </>
              ) : selectedEvent && (
                <>
                  <div className="detail-event-title-container">
                    <span className="detail-event-color" style={{ backgroundColor: selectedEvent.color }}></span>
                    <span className="detail-event-title">{selectedEvent.title}</span>
                  </div>
                  <div className="detail-event-dates">
                    <p><strong>시작일:</strong> {new Date(selectedEvent.start).toLocaleDateString()}</p>
                    <p><strong>종료일:</strong> {new Date(selectedEvent.end).toLocaleDateString()}</p>
                  </div>
                </>
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default CalendarDetail;
