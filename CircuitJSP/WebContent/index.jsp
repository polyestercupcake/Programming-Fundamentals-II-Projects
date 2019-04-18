<%@ page import="model.Circuit"%>
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
First of all, please choose what you would like to calculate: Voltage, Amperage, or Resistance?
<br><br>
<form>
<hr>
Calculate:
<br>
<!-- Cannot figure out how to put a name by the check box -->
<input type="checkbox" name="chkVoltage" title="Voltage">
<input type="checkbox" value="Amperage" name="chkAmperage">
<input type="checkbox" value="Resistance" name="chkResistance">
<br>
<!-- Could use required input type as well -->
	<input type="number" step="0.01" placeholder="[Enter Voltage Here]" name="txtVoltage">
<br>
	<input type="number" step="0.01" placeholder="[Enter Amperage Here]" name="txtAmperage">
<br>
	<input type="number" step="0.01" placeholder="[Enter Resistance Here]" name="txtResistance">
	<input type="submit" name="txtSubmit" value="Calculate">
	
	<%Circuit myCircuit = new Circuit();
	if (request.getParameter("txtSubmit") != null) {
		if (request.getParameter("chkVoltage") != null) {
			myCircuit.setAmperage(Double.parseDouble(request.getParameter("txtAmperage")));
			myCircuit.setResistance(Double.parseDouble(request.getParameter("txtResistance")));
			myCircuit.calcualteVoltage();
			out.println("<br><br>The voltage is " + myCircuit.getVoltage()); 
		} else if (request.getParameter("chkAmperage") != null) {
			myCircuit.setVoltage(Double.parseDouble(request.getParameter("txtVoltage")));
			myCircuit.setResistance(Double.parseDouble(request.getParameter("txtResistance")));
			myCircuit.calcualteAmperage();
			out.println("<br><br>The amperage is " + myCircuit.getAmperage());
		} else {
			myCircuit.setVoltage(Double.parseDouble(request.getParameter("txtVoltage")));
			myCircuit.setAmperage(Double.parseDouble(request.getParameter("txtAmperage")));
			myCircuit.calcualteResistance();
			out.println("<br><br>The resistance is " + myCircuit.getResistance());
			}
		}%>
</form>
</body>
</html>