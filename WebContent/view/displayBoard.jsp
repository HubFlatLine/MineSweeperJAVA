<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Board"%>
<%
	Board board = (Board) session.getAttribute("board");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Board</title>
</head>
<body>
	<%
	boolean allOpen=false;
	if (board.isEnd()) allOpen=true;
	%>
	<p>
		爆弾数：<%=board.getBombs()%>個   未開封セル：<%=board.getCountOfRemains()%>枚
	</p>
	<table border="1">
		<tr>
			<th width="32" height="32"></th>
			<th width="32" height="32">a</th>
			<th width="32" height="32">b</th>
			<th width="32" height="32">c</th>
			<th width="32" height="32">d</th>
			<th width="32" height="32">e</th>
			<th width="32" height="32">f</th>
			<th width="32" height="32">g</th>
			<th width="32" height="32">h</th>
			<th width="32" height="32">i</th>
		</tr>
<%	for (int i=0; i<9; i++) {%>
		<tr>
			<th><%=i+1%></th>
<%		for (int j=0; j<9; j++) {%>
			<td><img src="../view/images/<%=board.getIMG(i,j,allOpen)%>" width="32" height="32"></td>
<%		} %>
		</tr>
<%	} %>
	</table>
	<br>

	<%
		if (!board.isEnd()) {
	%>

	<form action="openCell" method="post">
		<%=(board.getTern() + 1)%>回目:<select name="row">
			<option value="0">1</option>
			<option value="1">2</option>
			<option value="2">3</option>
			<option value="3">4</option>
			<option value="4">5</option>
			<option value="5">6</option>
			<option value="6">7</option>
			<option value="7">8</option>
			<option value="8">9</option>
		</select> 行
		<select name="column">
			<option value="0">a</option>
			<option value="1">b</option>
			<option value="2">c</option>
			<option value="3">d</option>
			<option value="4">e</option>
			<option value="5">f</option>
			<option value="6">g</option>
			<option value="7">h</option>
			<option value="8">i</option>
		</select> 列を
		<select name="command">
			<option value="open">開封</option>
			<option value="mark">封印</option>
		</select>
		<input type="submit" value="します">
	</form>
	<%
		} else {
			if (board.isExplosion()) {
	%>
				<img src="../view/images/explosion.jpg">
				<p>爆発してしまいましたねぇ。</p>
	<%
			} else {
	%>
				<p>成功です。お疲れ様でした。</p>
				<img src="../view/images/success.jpg">
	<%
			}
	%>
	<%
		}
	%>
</body>
</html>