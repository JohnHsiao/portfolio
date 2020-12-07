package struts;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
/**
 * 針對UTF8的actionform encoding
 * @author JOHN
 *
 */
public class BaseDynaActionForm extends DynaActionForm {

	public void reset(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) { 
		/*
		String encoding="UTF-8";
		try {
				request.setCharacterEncoding("'"+encoding+"'");
				response.setContentType("text/html;charset='"+encoding+"'");
				
			}catch (UnsupportedEncodingException ex) {
			
				System.out.println(ex.getMessage());
		}
		*/
	} 
}
