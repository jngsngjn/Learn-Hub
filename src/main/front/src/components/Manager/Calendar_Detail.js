import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './Calendar_Detail.css';
import Calendar from './Calendar';

const CalendarDetail = () => {
  const { eventId } = useParams();
  const navigate = useNavigate();
  const [event, setEvent] = useState(null);
  const [events, setEvents] = useState([]);
  const [selectedEvent, setSelectedEvent] = useState(null); // 추가
  const [editMode, setEditMode] = useState(false);
  const [editedEvent, setEditedEvent] = useState({ title: '', start: '', end: '', color: '', content: '' });

  useEffect(() => {
    const storedEvents = JSON.parse(localStorage.getItem('calendarEvents')) || [];
    setEvents(storedEvents);
    const foundEvent = storedEvents.find((e) => e.id === Number(eventId));
    setEvent(foundEvent);
    setEditedEvent(foundEvent);
  }, [eventId]);

  const getEventsForDate = (date) => {
    return events.filter(event =>
      new Date(event.start).toDateString() === date.toDateString() ||
      (new Date(event.start) <= date && new Date(event.end) >= date)
    );
  };

  if (!event) {
    return <div>이벤트를 찾을 수 없습니다.</div>;
  }

  const handleDeleteEvent = (id) => {
    const updatedEvents = events.filter((e) => e.id !== id);
    localStorage.setItem('calendarEvents', JSON.stringify(updatedEvents));
    setEvents(updatedEvents);
    if (id === event.id) {
      navigate('/managers');
    }
  };

  const handleEditEvent = () => {
    setEditMode(true);
  };

  const handleSaveEvent = () => {
    const updatedEvents = events.map((e) => e.id === event.id ? editedEvent : e);
    localStorage.setItem('calendarEvents', JSON.stringify(updatedEvents));
    setEvents(updatedEvents);
    setEvent(editedEvent);
    setEditMode(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditedEvent({ ...editedEvent, [name]: value });
  };

  const handleTitleClick = (evt) => {
    setSelectedEvent(evt);
  };

  const selectedDate = new Date(event.start);

  return (
    <div className="detail-calendar-detail-page">
      <div className="detail-calendar-detail-sidebar">
        <Calendar />
      </div>
      <div className="detail-calendar-detail-container">
        <div className="detail-calendar-detail-header">
          <h2>일정 관리</h2>
          <button onClick={() => navigate(-1)}>돌아가기</button>
        </div>
        <div className="detail-calendar-detail-content">
          <div className="detail-all-events">
            <h3>해당 날짜의 일정</h3>
            <ul>
              {getEventsForDate(selectedDate).map((evt) => (
                <li key={evt.id}>
                  <div className="detail-event-title-container">
                    <span className="detail-event-color" style={{ backgroundColor: evt.color }}></span>
                    <span className="detail-event-title" onClick={() => handleTitleClick(evt)}>{evt.title}</span>
                    <div className="detail-event-actions">
                      <button onClick={() => {
                        setEditedEvent(evt);
                        setEditMode(true);
                      }}>
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
          <div className="detail-event-info">
            {editMode ? (
              <>
                <input
                  type="text"
                  name="title"
                  value={editedEvent.title}
                  onChange={handleChange}
                  className="detail-event-title-input"
                />
                <input
                  type="date"
                  name="start"
                  value={editedEvent.start}
                  onChange={handleChange}
                  className="detail-event-date-input"
                />
                <input
                  type="date"
                  name="end"
                  value={editedEvent.end}
                  onChange={handleChange}
                  className="detail-event-date-input"
                />
                <div className="detail-event-actions-edit">
                  <button onClick={handleSaveEvent}><i className="fas fa-save"></i></button>
                  <button onClick={() => handleDeleteEvent(event.id)}><i className="fas fa-trash-alt"></i></button>
                </div>
              </>
            ) : selectedEvent ? (
              <>
                <div className="detail-event-title-container">
                  <span className="detail-event-color" style={{ backgroundColor: selectedEvent.color }}></span>
                  <span className="detail-event-title">{selectedEvent.title}</span>
                </div>
                <div className="detail-event-dates">
                  <p><strong>시작일:</strong> {new Date(selectedEvent.start).toLocaleDateString()}</p>
                  <p><strong>종료일:</strong> {new Date(selectedEvent.end).toLocaleDateString()}</p>
                  <p><strong>내용:</strong> {selectedEvent.content}</p>
                </div>
              </>
            ) : (
              <p>일정정보 확인</p>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default CalendarDetail;
