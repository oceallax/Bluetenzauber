package presentation.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="emailValidator")
public class EMailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String userInput = (String)value;
		
		if(userInput.indexOf('@')<1) {
			throw new ValidatorException(new FacesMessage("Bitte eine gültige E-Mail angeben","Bitte eine gültige E-Mail angeben"));
			
		}
	}

}
