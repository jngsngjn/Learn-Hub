import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import './ProgressModal.css';

const ProgressModal = ({ isOpen, onClose }) => {
    const [uploadProgress, setUploadProgress] = useState(0);

    useEffect(() => {
        if (isOpen) {

            const socket = new SockJS(`http://localhost:8080/gs-guide-websocket`);
            const stompClient = new Client({
                webSocketFactory: () => socket,
                onConnect: () => {
                    console.log('Connected to WebSocket');
                    stompClient.subscribe('/topic/progress', (message) => {
                        const progressUpdate = JSON.parse(message.body);
                        console.log('Received progress update:', progressUpdate);
                        setUploadProgress(progressUpdate.progress);
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
                <button className="progress-close-button" onClick={onClose}>취소</button>
            </div>
        </div>
    );
};

export default ProgressModal;