$(document).ready(function () {
	
	var baseUrl = window.location.protocol + "//" + window.location.host + "/"; 
		
	// 변환 버튼 클릭 시,
	$("#shorteningBtn").click(function() {
	
		var tempData = {
			"originUrl" : $("#originUrl").val()
		};
	
		$.ajax({
			type : 'POST',
			url	: '/urlShortening',
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'POST'
			},
			data : JSON.stringify(tempData),
			dataType: 'JSON',
			async: false,
			cache : false,
			success:function(data) {
				if(data.resultMsg != "FAIL") {
					
					$("#shortedUrl").html("<a href='"+ data.originUrl + "' target='_blank'>" + baseUrl + data.shortenedUrl + "</a>");
					alert("주소 변환 완료");
				} else {
					alert("주소 변환 실패");
				}
			}
		});
	});
	
	// 이동 버튼 클릭 시,
	$("#moveBtn").click(function() {
		
		var inputShortenedUrl = $("#shortenedUrl").val();

		if(!inputShortenedUrl.includes(baseUrl, 0)) {
			alert("올바른 주소를 입력해주세요.\n예)http://localhost/abc");
			return false;
		}
		
		$.ajax({
			type : 'GET',
			url	: '/getOriginUrl?shortenedUrl=' + inputShortenedUrl.replace(baseUrl,''),
			dataType: 'JSON',
			success:function(data) {
				
				if(data.resultMsg == "SUCCES") {
	
					if(confirm("원본 url 로 이동하시겠습니까?")) {
						window.open(data.originUrl, '_blank'); 
					} else {
						return false;
					}
				} else {
					alert("입력한 단축URL로 등록된 데이터가 없습니다.\n다시 시도해주세요.");
					return false;
				}
			}
		});
	});
	
	
	// 변환 버튼 엔터키 트리거 적용
	$('#originUrl').keypress(function(event){
	     if ( event.which == 13 ) {
	         $('#shorteningBtn').click();
	         return false;
	     }
	});
	
	// 이동 버튼 엔터키 트리거 적용
	$('#shortenedUrl').keypress(function(event){
	     if ( event.which == 13 ) {
	         $('#moveBtn').click();
	         return false;
	     }
	});
});