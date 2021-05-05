/**
 * ajax로 파일 다운로드 테스트를 해보기 위한 js
 */

console.log('==fileDown.js start==');

function ajaxDownload(){
	console.log('== ajaxDownLoad function start ==');
	$.ajax({
		type: "POST"
		, url: "/ajaxDownload"
		, success: function(result){
			console.log(result);
		}
		, error: function(result){
			console.log('error: ', result)
		}
		
		
	});
}