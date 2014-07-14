document.domain='daum.net';

function getSWF(movieName){
  return (navigator.appName.indexOf("Microsoft") != -1)?window[movieName]:document[movieName];
}

Coca = {

	//function은 수정 금지

	//function : upload 시작
	upload: function(){
  		getSWF("coca").browse();
	},
	//function : activate 시작(현재는 모든 업로드 서버 -php, 업로드팜- 들이 바로 activation 하기 때문에 호출할 필요가 없다. 
	activate: function(){
		getSWF("coca").activate();
	},
	//function : remove 파일 삭제 key 는 attach type url을 집어 넣는다.
	remove: function(key){
		return getSWF("coca").remove(key);//return remain count
	},
	//upload enable
	enableCoca: function(key){
		return getSWF("coca").enableCoca();
	},
	//upload disable
	disableCoca: function(key){
		return getSWF("coca").disableCoca();
	},

	//callback은 서비스에서 알아서 오버라이드
	//ctx 는 한 페이지 안에 여러 플래시 업로더 인스턴스가 있을 때 구분하기 위한 용도, ctx를 따로 주지 않는 경우 빈 문자열을 반환한다. 

	//플래시 인스턴스에 mouse_over
	on_mouse_over: function(ctx)
	{
		console.log(ctx+"_mouseover");
	},
	//플래시 인스턴스에 mouse_out
	on_mouse_out: function(ctx)
	{
		console.log(ctx+"_mouseout");
	},
	//callback : 에러 발생 시 호출
	on_error: function(msg, ctx){
		console.log(ctx+"_error:"+msg);
	},
	//callback : 파일 브라우저 창에서 취소 할 때 호출
	on_browse_cancel: function(hasError, ctx){
		console.log(ctx+"_browse_cancel");
	},
	//callback : 파일 브라우저에서 파일 선택 후 확인 시 호출
	on_upload_start: function(ctx, count){
		console.log(ctx+"_upload_start:"+count);
	},
	//callback : 파일 업로드가 시작 될 때 호출 - 파일을 3개 선택했으면 3번 호출
	on_upload_open: function(name, size, type, ctx){
		console.log(ctx+"_upload_open:"+name);
	},
	//callback : 전체 선택 파일의 업로드 량 변화 시 호출 - 퍼센트
	on_upload_progress: function(percent, ctx){
		console.log(ctx+"_upload_progress:"+percent);
	},
	//callback : 파일 업로드가 끝나고, result가 오기까지 기다리기 시작
	on_upload_wating_complete: function(name, type, ctx){
		console.log(ctx+"_upload_wating_complete:"+name);
	},
	//callback : 파일 업로드가 끝났을 때 호출 - result 는 이전 카페 php 모듈의 리턴값과 동일, 파일 3개를 올렸으면 3번 호
	on_upload_complete: function(result, ctx){
		console.log(ctx+"_upload_complete:\n"+result);
		try {
			var jdx = result.indexOf("http");
	    	var url = result.substring(jdx);
	    	jdx = url.indexOf("||");
	    	url = url.substring(0, jdx).replace("attach","image");
			coca.listener[ctx](url);
		} catch (e) {
			console.log(e);
		}
	},
	//callback : 모든 업로드가 끝났을 때 호출
	on_upload_finish: function(ctx){
		console.log(ctx+"_upload_finish");
	},
	//callback : 파일 액티베이트가  끝났을 때 호출 - 현재 파일서버들은 자동 액티베이트 방식이기 때문에 호출되지 않는다.
	on_activate_complete: function(result, ctx){
		console.log(ctx+"_activate_complete:\n"+result);
	},
	//callback : 파일 액티베이트가  모두 끝났을 때 호출 - 현재 파일서버들은 자동 액티베이트 방식이기 때문에 호출되지 않는다.
	on_activate_finish: function(ctx){
		console.log(ctx+"_activate_finish");
	},
	//callback : 설정해놓은 파일 사이즈보다  큰 파일을 선택했을 경우
	on_over_filesize: function(filename, maxsize, ctx){
		alert("파일 사이즈가 1M를 초과하였습니다.");
	},
	//callback : 설정해놓은 파일 개수보다  많이  파일을 선택했을 경우
	on_over_filecount: function(overcount, maxcount, ctx){
		console.log(ctx+"_overcount:"+overcount+","+maxcount);
	},
	//callback : 설정해놓은 전체 파일 사이즈 파일를 넘게 선택했을 경우
	on_over_filequota: function(overquota, maxquota, ctx){
		console.log(ctx+"_overquota:"+overquota+","+maxquota);
	},
	//callback : 용량이나 개수 제한이 업로드 도중 넘을 경우 해당 파일은 SKIP, extension_quota_flag는 특정 파일에 쿼타를 줄 경우 해당 파일의 그룹명(다음 에디터에 커플링)
	on_upload_skip: function(name, size, type, ctx, extension_quota_flag){
		console.log(ctx+"_upload_skip:"+name);
	},
	//callback : 100% progress 이후, complete 대기 중
	on_upload_wating_complete: function(name, extension, ctx){
		console.log(ctx+"_wait_complete:"+name);
	},
	on_upload_restrict_complete: function(result, ctx){
		console.log(ctx+"_restrict_complete:"+result);
	}
};


var coca = new SWFObject("http://editor.daum.net/coca_service/1.1.22/coca.swf",	"coca", "70", "26", "9.0.28");// , "#484848");
coca.addParam("quality", "high");
coca.addParam("menu", "false");
coca.addParam("swLiveConnect", "true");
coca.addParam("allowScriptAccess", "always");
coca.addParam("scale", "noscale");
coca.addParam("salign", "LT");
coca.addVariable("single_selection", "true");
coca.addVariable("coca_service", "FinanceCoca");
coca.addVariable("sid", "sid");
coca.addVariable("service", "storyBall");
coca.addVariable("sname", "storyBall");
coca.addVariable("service_farm", "use")
coca.addVariable("coca_skin", "http://cfile179.uf.daum.net/image/030A5E35518C467F01B8B0");
coca.listener = {};
coca.addImageUpload = function(id, listener) {
	coca.addVariable("coca_ctx", id);
	coca.write(id);
	coca.listener[id] = listener;
};