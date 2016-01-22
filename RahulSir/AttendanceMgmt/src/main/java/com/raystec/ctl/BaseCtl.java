package com.raystec.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raystec.bean.BaseBean;
import com.raystec.bean.UserBean;
import com.raystec.utility.DataUtility;
import com.raystec.utility.DataValidator;
import com.raystec.utility.ServletUtility;

/**
 * Servlet implementation class BaseCtl
 */
@WebServlet("/BaseCtl")
public abstract class BaseCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";

	/**
	 * Success message key constant
	 */
	public static final String MSG_SUCCESS = "success";

	/**
	 * Error message key constant
	 */
	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by User
	 * 
	 * @param request
	 * @return
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */

	protected BaseBean populateBean(HttpServletRequest request) {
		return null;
	}

	/**
	 * Populates Generic attributes in DTO
	 * 
	 * @param dto
	 * @param request
	 * @return
	 */

	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {
		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;
		UserBean userBean = (UserBean) request.getSession()
				.getAttribute("user");
		if (userBean == null) {
			// If record is created without login
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userBean.getLogin();
			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy)
					|| DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDateTime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.geTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}
		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());
		return dto;
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Load the preloaded data required to display at HTML form
		preload(request);
		String op = DataUtility.getString(request.getParameter("operation"));

		// Check if operation is not DELETE, VIEW, CANCEL, and NULL then
		// perform input data validation
		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)
				&& !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op)) {
			// Check validation, If fail then send back to page with error
			// messages
			if (!validate(request)) {
				BaseBean bean = (BaseBean) populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}
		super.service(request, response);
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	protected abstract String getView();
}
