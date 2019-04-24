<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="ISO-8859-1">
<title>Ohm's Law</title>
</head>
<body>
	<!-- Programmer -> tomcat -> html -> web browser -> end user -->
	Today is
	<%=new java.util.Date()%>.
	<br>
	<br> Welcome to the Ohm's Law calculator! This webpage is designed
	to help you calculate Ohm's Law anyway you would like.
	<br>
	<br> First of all, please choose what you would like to calculate:
	Voltage, Amperage, or Resistance?
	<br>
	<br>
	<hr>
	<!-- method=get sticks it into the url (user, passw, values) and method=post will not (hides data) -->
	<form method=post>
		<div id="directions">
			<h4>Directions</h4>
			<p>If calculating Voltage, please only enter values in the
				Amperage and Resistance fields.</p>
			<p>If calculating Amperage, please only enter values in the
				Voltage and Resistance fields.</p>
			<p>If calculating Resistance, please only enter values in the
				Voltage and Amperage fields.</p>
		</div>

		<br> <br> <br> <br>
		<!-- Could use required input type as well -->
		<input type="number" step="0.01" placeholder="[Enter Voltage]"
			name="txtVoltage"> <br> <br> <input type="number"
			step="0.01" placeholder="[Enter Amperage]" name="txtAmperage">
		<br> <br> <input type="number" step="0.01"
			placeholder="[Enter Resistance]" name="txtResistance"> <br>
		<br> <input type="submit" name="txtSubmit" value="Calculate">
		<input type="submit" name="txtReset" value="Reset Form">
		
	<!-- jstl (java standard tag library) Script-let -->
		<c:if test="${not empty message}">
			<br>
			<br>
			<c:out value = "${message}"/>
		</c:if>
		
	</form>
	<br>
	<hr>
</body>
</html>

<!-- IF SEVER FAILS
Project -> Clean
Remove CircuitJSP from server
Right click on project -> Run As -> 1 Run on Server
 -->