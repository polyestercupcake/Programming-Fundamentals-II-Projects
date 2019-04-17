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
<!-- Programmer -> tomcat -> html -> Chrome -> end user -->
Today is <%= new java.util.Date() %>.
<br><br>
Welcome to the Ohm's Law calculator! This webpage is designed to help you calculate Ohm's Law
anyway you would like.
<br><br>
First of all, please choose what you would like to calculate: Voltage, Amerpage, or Resistance?
<br>
<br>
<form>
	<input required type="number" step="0.01" placeholder="[Enter Voltage Here]" name="txtVoltage">
	<input required type="number" step="0.01" placeholder="[Enter Resistance Here]" name="txtResistance">
	<input type="submit" name="txtSubmit" value="Calculate">
	<%
	if (request.getParameter("txtSubmit") != null) {
	double voltage = Double.parseDouble(request.getParameter("txtVoltage"));
	double resistance = Double.parseDouble(request.getParameter("txtResistance"));
	double amperage = voltage / resistance;
	out.println("<br><br>The amperage is " + amperage); 
	}
	%>
</form>

</body>
</html>