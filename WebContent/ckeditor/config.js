CKEDITOR.editorConfig = function( config )
{
    config.toolbar = 'Full';

	config.toolbar_Full =
	[
	    ['Source','-','Save','NewPage','Preview','-','Templates'],
	    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	    ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
	    '/',
	    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	    ['Link','Unlink','Anchor'],
	    ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	    '/',
	    ['Styles','Format','Font','FontSize'],
	    ['TextColor','BGColor'],
	    ['Maximize', 'ShowBlocks','-','About']
	];
	
	config.toolbar_Basic =
	[
	    ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink','Smiley']
	];
	
	config.toolbar_Default =
	[
	    ['Source','Maximize','Print','-',
	     'Cut','Copy','Paste','PasteText','PasteFromWord','-',
	     'SpellChecker','Scayt',
	     'JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-',
	     'Outdent','Indent','NumberedList','BulletedList','-',
	     'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','Anchor'],
	     '/',	    
	    ['Styles','TextColor','BGColor','Bold','Italic','Underline','Strike','Subscript','Superscript','Font','FontSize','Format','-',
	     'Blockquote','Link','Unlink']
	    
	    
	];
	
	
	
};

