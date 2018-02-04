<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Memo.css">
<title>Memo</title>
</head>
<body>
	<div id="sidemenue">
		</div> 
		<div id="mainsite">
			<div id="motto">
				%
				String motto
				getAttribute(motto);
				<h1>motto</h1>
				%
			</div>
		</div>
		<div id="login">
			<ul>
				<li><img id="userpic" src="https://picsum.photos/40/40"></li>
				<li><div class="signup">Login</div>
					<ul>
						<li><br></li>
						<li>username:</li>
						<li><input style="width: 80px;" type="text" name="username"></li>
						<li><br></li>
						<li>password:</li>
						<li><input style="width: 80px;" type="text" name="password"></li>
						<li><br></li>
						<li><input id="loginbotton" type="submit" name="submit" value="login"></li>
						<li><div style="border-bottom: 2px solid black"><br></div></li>
					</ul>
				</li>
				<li><div class="signup"> Sign up</div></li>
			</ul>
		</div>
	
</body>
</html>