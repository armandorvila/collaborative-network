 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/login.css">
<title>Collaborative Network for Project Development</title>
</head>

<body>
	<div id="header">Collaborative Network for Project Development</div>

	<div id="content">

		<div id="left">

			<div class="info">

				<h3>Objectives</h3>
				<p>This is an application to create, share and develop projects
					with partners.</p>

				<h3>Requirements</h3>
				<p>To start developing project on your network you have to do a
					wizard and give any Google account credentials.</p>
				<p>Credentials are protected by following contract.</p>
				<p>
					<a href="#">Credentials protection contract</a>
				</p>

				<h3>Use</h3>
				<p>The use of this application is complicated as you want, a
					complete guide is at the following link.</p>
				<p>
					<a href="manual.pdf">User guide</a>
				</p>

			</div>

		</div>

		<div id="right">

			<div id="login">
				<h3>Please Login or Sing up</h3>
				
				<c:if test="${not empty param.login_error}">
					<span class="actionError"> Your login attempt was not
						successful, try again.<br />
					</span>
				</c:if>
				
				<form action="login" method="post">
					<table>
						<tr>
							<td>Name:</td>
							<td><input type="text" name="j_username"></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="j_password"></td>
						</tr>
					</table>
					<br> <input type="submit" value="login">
				</form>
				<p>
					<a href="Signup.html#signup">Sing up</a>
				</p>
			</div>
		</div>

	</div>

	<div id="footer">
		<ul>
			<li>Escuela de Ingeniería Informática</li>
			<li>Universidad de Oviedo</li>
		</ul>
	</div>

</body>

</html>