package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Board;

public class CreateBoard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	String level = request.getParameter("level");
		int bombs = getBombNum(level);
		Board board = new Board(bombs);

		HttpSession session=request.getSession();
		session.setAttribute("board", board);
		request.getRequestDispatcher("../view/displayBoard.jsp").forward(request, response);
	}

    int getBombNum(String level) {
		int bombs;
		int MAX_BOMBS = 40;
		switch (level) {
		case "e":
			bombs = (MAX_BOMBS/8)+(int)(Math.random()*5);
			break;
		case "n":
			bombs = (MAX_BOMBS/4)+(int)(Math.random()*10);
			break;
		case "h":
			bombs = (MAX_BOMBS/2)+(int)(Math.random()*10);
			break;
		case "d":
			bombs = (MAX_BOMBS*3/4)+(int)(Math.random()*10);
			break;
		default:
			bombs = MAX_BOMBS;
			break;
		}
		return bombs;
	}

}