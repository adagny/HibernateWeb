package ejemplo.presentacion;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fpmislata.excepciones.BussinessException;

import ejemplo.dominio.Profesor;
import ejemplo.persistencia.dao.ProfesorDAO;

@Controller
public class ProfesorController {
	@Autowired
	private ProfesorDAO profesorDAO;

	@RequestMapping({ "/index.html" })
	public ModelAndView read(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> model = new HashMap<>();
		String viewName;
		try {
			Profesor profesor = profesorDAO.get(1215);
			System.out.println(profesor.getNombre());
			model.put("texto", profesor.getNombre().getNombreCompleto()+ " "+profesor.getTipoFuncionario());
			viewName = "profesorr";
		} catch (BussinessException ex) {
			model.put("msgError", "No es posible obtener los datos");
			viewName = "error";
		}

		return new ModelAndView(viewName, model);
	}
}
