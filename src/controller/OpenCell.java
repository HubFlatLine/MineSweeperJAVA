package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Board;

public class OpenCell extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String row = request.getParameter("row");
    	String column = request.getParameter("column");
    	String command = request.getParameter("command");
    	int x,y,f;
    	x=Integer.parseInt(row);
    	y=Integer.parseInt(column);
    	if(command.equals("mark")) f=2;
    	else f=0;
		HttpSession session=request.getSession();
		Board board=(Board)session.getAttribute("board");
		board.openCellMask(x,y,f);
		board.incTern();
		board.isEnd();
		session.setAttribute("board", board);
		request.getRequestDispatcher("../view/displayBoard.jsp").forward(request, response);
    }

}
