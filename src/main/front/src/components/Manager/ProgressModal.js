import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import './ProgressModal.css';

const ProgressModal = ({ isOpen, onClose }) => {
    const [uploadProgress, setUploadProgress] = useState(0);
    const [currentStudent, setCurrentStudent] = useState(0);
    const [totalStudents, setTotalStudents] = useState(0);
    const [isCompleted, setIsCompleted] = useState(false);
    const [successCount, setSuccessCount] = useState(0);

    useEffect(() => {
        if (isOpen) {
            // 모달이 열릴 때 상태 초기화
            setUploadProgress(0);
            setCurrentStudent(0);
            setTotalStudents(0);
            setIsCompleted(false);
            setSuccessCount(0);

            const socket = new SockJS(`http://localhost:8080/gs-guide-websocket`);
            const stompClient = new Client({
                webSocketFactory: () => socket,
                onConnect: () => {
                    console.log('Connected to WebSocket');
                    stompClient.subscribe('/topic/progress', (message) => {
                        const progressUpdate = JSON.parse(message.body);
                        console.log('Received progress update:', progressUpdate);
                        setUploadProgress(progressUpdate.progress);
                        setCurrentStudent(progressUpdate.current);
                        setTotalStudents(progressUpdate.total);

                        // 업로드 완료 상태 확인
                        if (progressUpdate.current === progressUpdate.total) {
                            setIsCompleted(true);
                            setSuccessCount(progressUpdate.successCount || progressUpdate.total); // 성공한 개수, progressUpdate에 포함되어 있지 않으면 totalRows 사용
                        }
                    });
                },
                onStompError: (frame) => {
                    console.error('Broker reported error: ' + frame.headers['message']);
                    console.error('Additional details: ' + frame.body);
                }
            });

            stompClient.activate();

            return () => {
                if (stompClient) {
                    stompClient.deactivate();
                }
            };
        }
    }, [isOpen]);

    if (!isOpen) {
        return null;
    }

    return (
        <div className="progress-modal-overlay">
            <div className="progress-modal">
                <h2>파일 업로드 중...</h2>
                <progress value={uploadProgress} max="100" />
                <span>{uploadProgress}%</span>
                <br />
                <span>{currentStudent}/{totalStudents}</span>
                <br />
                {isCompleted && (
                    <>
                        <span>총 {totalStudents}명 중 {successCount}명 성공</span>
                        <br />
                        <button onClick={onClose}>닫기</button>
                    </>
                )}
            </div>
        </div>
    );
};

export default ProgressModal;