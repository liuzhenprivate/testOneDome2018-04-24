<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>世银期货</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	var browser = {
		versions : function() {
			var u = navigator.userAgent, app = navigator.appVersion;
			return {
				trident : u.indexOf('Trident') > -1, //IE内核                
				presto : u.indexOf('Presto') > -1, //opera内核                
				webKit : u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核                
				gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核                
				mobile : !!u.match(/AppleWebKit.*Mobile.*/)
						|| !!u.match(/AppleWebKit/), //是否为移动终端                
				ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端                
				android : u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器                
				iPhone : u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器                
				iPad : u.indexOf('iPad') > -1, //是否iPad                
				webApp : u.indexOf('Safari') == -1
			//是否web应该程序，没有头部与底部            
			};
		}()
	}

	//document.writeln(" 是否为移动终端: "+browser.versions.mobile);
	//document.writeln(" ios终端: "+browser.versions.ios);
	//document.writeln(" android终端: "+browser.versions.android);
	//document.writeln(" 是否为iPhone: "+browser.versions.iPhone);
	//document.writeln(" 是否iPad: "+browser.versions.iPad);
	//document.writeln(navigator.userAgent); 
	//alert(browser.versions.mobile || browser.versions.ios || browser.versions.android || browser.versions.iPhone || browser.versions.iPad);
	if (browser.versions.mobile) {
		if (browser.versions.ios || browser.versions.android
				|| browser.versions.iPhone || browser.versions.iPad) {
			window.location.href = '/RegisterPro/register/wap';
		} else {
			window.location.href = '/RegisterPro/register/web';
		}
	} else {
	    	window.location.href = '/RegisterPro/act/web';
	}
</script>
</head>

<body>
	This is my JSP page.
	<br>
</body>
</html>
