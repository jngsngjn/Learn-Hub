import React, { useEffect, useState, useRef, useCallback } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import './ProgressModal.css';

const ProgressModal = ({ isOpen, onClose }) => {
    const [uploadProgress, setUploadProgress] = useState(0);
    const [currentStudent, setCurrentStudent] = useState(0);
    const [totalStudents, setTotalStudents] = useState(0);
    const [isCompleted, setIsCompleted] = useState(false);
    const [successCount, setSuccessCount] = useState(0);
    const animationRef = useRef(null);
    const targetProgressRef = useRef(0);

    const animateProgress = useCallback(() => {
        if (uploadProgress < targetProgressRef.current) {
            setUploadProgress(prev => {
                const newProgress = Math.min(prev + 1, targetProgressRef.current);
                if (newProgress >= 100) {
                    cancelAnimationFrame(animationRef.current);
                    setTimeout(() => setIsCompleted(true), 500);
                }
                return newProgress;
            });
            animationRef.current = requestAnimationFrame(animateProgress);
        }
    }, [uploadProgress]);

    useEffect(() => {
        if (targetProgressRef.current > uploadProgress) {
            if (!animationRef.current) {
                animationRef.current = requestAnimationFrame(animateProgress);
            }
        }
    }, [targetProgressRef.current, uploadProgress, animateProgress]);

    useEffect(() => {
        if (isOpen) {
            setUploadProgress(0);
            setCurrentStudent(0);
            setTotalStudents(0);
            setIsCompleted(false);
            setSuccessCount(0);
            targetProgressRef.current = 0;

            const socket = new SockJS(`http://localhost:8080/gs-guide-websocket`);
            const stompClient = new Client({
                webSocketFactory: () => socket,
                onConnect: () => {
                    console.log('Connected to WebSocket');
                    stompClient.subscribe('/topic/progress', (message) => {
                        const progressUpdate = JSON.parse(message.body);
                        console.log('Received progress update:', progressUpdate);
                        targetProgressRef.current = progressUpdate.progress;
                        setCurrentStudent(progressUpdate.current);
                        setTotalStudents(progressUpdate.total);

                        if (progressUpdate.progress === 100) {
                            setSuccessCount(progressUpdate.successCount || progressUpdate.total);
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
                if (animationRef.current) {
                    cancelAnimationFrame(animationRef.current);
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
                {!isCompleted ? (
                    <>
                        <h2>파일 업로드 중...</h2>
                        <div id="myProgress">
                            <div id="myBar" style={{ width: `${uploadProgress}%` }}>
                                {Math.round(uploadProgress)}%
                            </div>
                        </div>
                        <br />
                        <span>{currentStudent}/{totalStudents}</span>
                    </>
                ) : (
                    <>
                        <h2>파일 업로드 완료</h2>
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