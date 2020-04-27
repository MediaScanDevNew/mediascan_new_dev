<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>

	<s:form>
		<table>
			<tr>
				<td>Select project Type</td>
				<td><select>
						<option value="volvo">film</option>
						<option value="saab">movie</option>
						<option value="mercedes">sports</option>
						<option value="audi">Audi</option>
				</select></td>
			</tr>
			<tr>
				<td>Select Client</td>
				<td><select>
						<option value="volvo">novi digital</option>
						<option value="saab">msm</option>
						<option value="mercedes">indiacast</option>
						<option value="audi">balaji</option>
				</select></td>
			</tr>
			<tr>
				<td>Select project</td>
				<td><select>
						<option value="volvo">baal veer</option>
						<option value="saab">staying live</option>
						<option value="mercedes">apple</option>
						<option value="audi">motog</option>
				</select></td>
			</tr>
			<%-- <tr>
				<td>Key phrasre</td>
				<td><select>
						<option>select</option>
						<option value="volvo">staying live download</option>
						<option value="saab">staying live movie download</option>
						<option value="mercedes">staying live mp4</option>
						<option value="audi">staying live dvdrip</option>
				</select></td>

			</tr> --%>


			<tr>
				<td></td>
				<td><input type="submit"></td>
			</tr>
		</table>
		<div></div>
		<h2>Content filter</h2>
		<table>
			<tr>
				<th>Content filter1</th>
				<th>Content filter2</th>
				<th>Content filter3</th>
				<th>Content filter4</th>
				<th>Content filter5</th>
				<th>Content filter6</th>
				<th>Content filter7</th>
				<th>Content filter8</th>
			</tr>

			<tr>
				<td><input type="text" value="staying"></td>
				<td><input type="text" value="live"></td>
				<td><input type="text" value="movie"></td>
				<td><input type="text" value="download"></td>
				<td><input type="text" value="dvdrip"></td>
				<td><input type="text" value="mp4"></td>
				<td><input type="text" value="2015"></td>
				<td><input type="text" value="1024"></td>
			</tr>

		</table>
		<h2>Query setup</h2>


		<table>
			<tr>
				<th>keyword phrase</th>
				<th>pipe</th>
				<th>machine</th>
				<th>task peiority</th>
				<th>action</th>
			</tr>
			<tr>
				<td><select>
						<option>select</option>
						<option value="volvo">staying live download</option>
						<option value="saab">staying live movie download</option>
						<option value="mercedes">staying live mp4</option>
						<option value="audi">staying live dvdrip</option>
				</select></td>


				<td><select>
						<option>select pipe</option>
						<option value="volvo">google</option>
						<option value="saab">yahoo</option>
						<option value="mercedes">bing</option>
						
				</select></td>
				<td><select>
						<option>select BOT Machine</option>
						<option value="volvo">BOT 1</option>
						<option value="saab">BOT 2</option>
						<option value="mercedes">BOT 3</option>
						
				</select></td>
				<td><input type="text" value=""></td>
				<td><input type="checkbox" name="vehicle" value="Bike"></td>
			
			</tr>

<tr>
				<td><select>
						<option>select</option>
						<option value="volvo">staying live download</option>
						<option value="saab">staying live movie download</option>
						<option value="mercedes">staying live mp4</option>
						<option value="audi">staying live dvdrip</option>
				</select></td>


				<td><select>
						<option>select pipe</option>
						<option value="volvo">google</option>
						<option value="saab">yahoo</option>
						<option value="mercedes">bing</option>
						
				</select></td>
				<td><select>
						<option>select BOT Machine</option>
						<option value="volvo">BOT 1</option>
						<option value="saab">BOT 2</option>
						<option value="mercedes">BOT 3</option>
						
				</select></td>
				<td><input type="text" value=""></td>
				<td><input type="checkbox" name="vehicle" value="Bike"></td>
			
			</tr>
			<tr>
				<td><select>
						<option>select</option>
						<option value="volvo">staying live download</option>
						<option value="saab">staying live movie download</option>
						<option value="mercedes">staying live mp4</option>
						<option value="audi">staying live dvdrip</option>
				</select></td>


				<td><select>
						<option>select pipe</option>
						<option value="volvo">google</option>
						<option value="saab">yahoo</option>
						<option value="mercedes">bing</option>
						
				</select></td>
				<td><select>
						<option>select BOT Machine</option>
						<option value="volvo">BOT 1</option>
						<option value="saab">BOT 2</option>
						<option value="mercedes">BOT 3</option>
						
				</select></td>
				<td><input type="text" value=""></td>
				<td><input type="checkbox" name="vehicle" value="Bike"></td>
			
			</tr>
		</table>


		<div></div>



		<input type="submit">
	</s:form>
</body>
</html>