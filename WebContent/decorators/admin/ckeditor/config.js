/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'zh-cn';
	config.font_names='\u5B8B\u4F53/\u5B8B\u4F53;\u9ED1\u4F53/\u9ED1\u4F53;\u4EFF\u5B8B/\u4EFF\u5B8B;\u6977\u4F53/\u6977\u4F53;\u96B6\u4E66/\u96B6\u4E66;\u5E7C\u5706/\u5E7C\u5706;\u5FAE\u8F6F\u96C5\u9ED1/\u5FAE\u8F6F\u96C5\u9ED1;'+ config.font_names;
	var pathName = window.document.location.pathname;
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	config.filebrowserImageUploadUrl = "uploadImage";
	config.filebrowserUploadUrl="uploadImage";
};
