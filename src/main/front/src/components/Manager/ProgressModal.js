import React, { useEffect } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import './ProgressModal.css';

const ProgressModal = ({ isOpen, onClose, setUploadProgress }) => {
    useEffect(() => {
        if (isOpen) {
            const socket = new SockJS('http://localhost:8080/gs-guide-websocket');
            const stompClient = new Client({
                webSocketFactory: () => socket,
                onConnect: () => {
                    stompClient.subscribe('/topic/progress', (message) => {
                        const progressUpdate = JSON.parse(message.body);
                        setUploadProgress(progressUpdate.progress);
                    });
                },
            });

            stompClient.activate();

            return () => {
                if (stompClient) {
                    stompClient.deactivate();
                }
            };
        }
    }, [isOpen, setUploadProgress]);

    if (!isOpen) {
        return null;
    }

    return (
        <div className="progress-modal-overlay">
            <div className="progress-modal">
                <h2>파일 업로드 중...</h2>
                <progress value={setUploadProgress} max="100" />
                <span>{setUploadProgress}%</span>
                <button onClick={onClose}>취소</button>
            </div>
        </div>
    );
};

export default ProgressModal;