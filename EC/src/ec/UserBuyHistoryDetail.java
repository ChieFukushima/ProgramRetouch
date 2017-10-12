package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import beans.BuyDetailDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;


/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int buyId = Integer.parseInt(request.getParameter("buy_id"));

		BuyDataBeans bdb = new BuyDataBeans();

		try {

			bdb = BuyDAO.getBuyDataBeansByBuyId(buyId);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		request.setAttribute("bdb", bdb);

		ArrayList<BuyDetailDataBeans> buyDetailList = new ArrayList<BuyDetailDataBeans>();

		try {

			buyDetailList = BuyDetailDAO.getBuyDataBeansListByBuyId(buyId);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

	}
}
