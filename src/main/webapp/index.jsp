<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="padding-right: 25%; padding-left: 25%; padding-top: 5%;">

	<div class="container mt-3">
		<div class="container mt-3"
			style="border: solid 5px #e8f0fe; padding: 20px">
			<h2>Login form</h2>
			<form action="login" method="post">
				<div class="mb-3 mt-3">
					<label for="email">Email:</label> <input type="email"
						class="form-control" id="emailId" placeholder="Enter email"
						name="emailId">
				</div>
				<div class="mb-3">
					<label for="pwd">Password:</label> <input type="password"
						class="form-control" id="password" placeholder="Enter password"
						name="password">
				</div>
				<button type="submit" class="btn btn-primary">Login</button>
				<a href="registrationForm.jsp" style="float: right;">register
					here!</a>
			</form>
		</div>
	</div>
</body>
</html>