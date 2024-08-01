import React, { useRef, useState } from 'react';
import ReactDOM from 'react-dom';
import FroalaEditor from 'froala-editor';
import FroalaEditorComponent from 'react-froala-wysiwyg';

// Require Editor CSS files.
import 'froala-editor/css/froala_style.min.css';
import 'froala-editor/css/froala_editor.pkgd.min.css';

// Import all Froala Editor plugins
import 'froala-editor/js/plugins.pkgd.min.js';

// Import a single Froala Editor plugin.
import 'froala-editor/js/plugins/align.min.js';

// Import a language file.
import 'froala-editor/js/languages/de.js';

// Import third-party plugins.
import 'froala-editor/js/third_party/image_tui.min.js';
import 'froala-editor/js/third_party/embedly.min.js';

// Include font-awesome css if required.
import 'font-awesome/css/font-awesome.css';
import 'froala-editor/js/third_party/font_awesome.min.js';


// 사용자 정의 'codeBlock' 아이콘과 명령을 정의합니다
FroalaEditor.DefineIcon("codeBlock", {
    NAME: "codeBlock",
    SVG_KEY: "codeView",
});

FroalaEditor.RegisterCommand("codeBlock", {
    title: "codeBlock",
    focus: true,
    undo: true,
    refreshAfterCallback: true,
    callback: function () {
        this.html.set(
            this.html.get().replace(/&nbsp;&nbsp;/g, "<br/>&nbsp;&nbsp;"),
        );
    },
});

// FroalaEditor 컴포넌트를 정의합니다
function MyFroalaEditor() {
    const [value, setValue] = useState('');
    const editorRef = useRef(null);

    // 툴바 버튼을 정의합니다
    const toolbarButtons = {
        moreText: {
            buttons: ['bold', 'underline', 'strikeThrough', 'fontFamily', 'fontSize', 'textColor', 'backgroundColor'],
        },
        moreParagraph: {
            buttons: ['alignLeft', 'alignCenter', 'alignRight', 'formatOLSimple', 'formatOL', 'formatUL'],
        },
        moreRich: {
            buttons: ['insertImage', 'insertFile', 'insertHR'],
        },
        moreMisc: {
            buttons: ['undo', 'redo'],
        }
    };

    // Froala 에디터 설정
    const config = {
        placeholderText: '내용을 입력하세요...',
        charCounterCount: true,
        toolbarButtons,
        // 여기에 추가 설정을 넣을 수 있습니다
    };

    // 에디터 내용이 변경될 때 호출되는 함수
    const handleContentChange = (model) => {
        setValue(model);
    };

    return (
        <div>

            <FroalaEditorComponent
                tag="textarea"
                ref={editorRef}
                config={config}
                model={value}
                onModelChange={handleContentChange}
            />


        </div>
    );
}



export default MyFroalaEditor;