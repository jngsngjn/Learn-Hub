import React, { useRef, useState } from 'react';
import FroalaEditorComponent from 'react-froala-wysiwyg';
import 'froala-editor/css/froala_style.min.css';
import 'froala-editor/css/froala_editor.pkgd.min.css';
import 'froala-editor/js/plugins.pkgd.min.js';
import 'froala-editor/js/plugins/align.min.js';
import 'froala-editor/js/languages/de.js';
import 'froala-editor/js/third_party/image_tui.min.js';
import 'froala-editor/js/third_party/embedly.min.js';
import 'font-awesome/css/font-awesome.css';
import 'froala-editor/js/third_party/font_awesome.min.js';

function MyFroalaEditor() {
    const [title, setTitle] = useState('');
    const [value, setValue] = useState('');
    const editorRef = useRef(null);

    const toolbarButtons = {
        moreText: {
            buttons: ['bold', 'underline', 'strikeThrough', 'fontFamily', 'fontSize', 'textColor', 'backgroundColor'],
        },
        moreParagraph: {
            buttons: ['alignLeft', 'alignCenter', 'alignRight'],
        },
        moreRich: {
            buttons: ['insertImage', 'insertFile', 'insertHR'],
        },
        moreMisc: {
            buttons: ['undo', 'redo'],
        }
    };

    const config = {
        placeholderText: '내용을 입력하세요.',
        charCounterCount: true,
        toolbarButtons,
    };

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleContentChange = (model) => {
        setValue(model);
    };

    const handleButton = () => {
        const formData = new FormData();
        formData.append('title', title);
        formData.append('content', value);

        fetch("http://localhost:8080/test-editor", {
            method: "POST",
            body: formData
        })
            .then(res => {
                if (!res.ok) {
                    throw new Error('Network response was not ok');
                }
                return res.json();
            })
            .then(result => console.log(result))
            .catch(error => console.error('Fetch error:', error));
    };

    return (
        <div>
            <input
                type="text"
                placeholder="제목을 입력하세요."
                value={title}
                onChange={handleTitleChange}
            />
            <FroalaEditorComponent
                tag="textarea"
                ref={editorRef}
                config={config}
                model={value}
                onModelChange={handleContentChange}
            />
            <button className="onSubmit" onClick={handleButton}>제출</button>
        </div>
    );
}

export default MyFroalaEditor;