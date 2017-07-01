/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    $('.box').mouseover(function(){
        $(this).css({
            'background':'#00DDDD',
            'filter':'alpha(Opacity=50)',
            '-moz-opacity':'0.3',
            'opacity': '0.3'
        });
    });
    $('.box').mouseout(function(){
        $(this).css({
            'background':'',
            'filter':'',
            '-moz-opacity':'',
            'opacity': ''
        });
    });
});


