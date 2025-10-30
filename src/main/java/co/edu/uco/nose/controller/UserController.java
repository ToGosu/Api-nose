package co.edu.uco.nose.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.nose.business.facade.impl.UserFacadeImpl;
import co.edu.uco.nose.controller.dto.Response;
import co.edu.uco.nose.crosscuting.exception.NoseException;
import co.edu.uco.nose.dto.UserDTO;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@GetMapping
	public ResponseEntity<Response<UserDTO>> findAllUsers() {
		HttpStatus responseStatusCode = HttpStatus.OK;
		Response<UserDTO> responseObjectData = Response.createSuccededResponse();
		try {
			var facade = new UserFacadeImpl();
			responseObjectData.setData(facade.findAllUsers());
			responseObjectData.addMessage("User registered succesfully");
				
	} catch (final NoseException  exception) {
		responseObjectData = Response.createFailedResponse();
		responseObjectData.addMessage(exception.getUserMessage());
		responseStatusCode = HttpStatus.BAD_REQUEST;
		exception.printStackTrace();
	} catch (Exception exception) {
		var userMessage= "Unexpected error";
		responseObjectData = Response.createFailedResponse();
		responseObjectData.addMessage(userMessage);
		responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

		exception.printStackTrace();
	}
		return new ResponseEntity<Response<UserDTO>>(responseObjectData,responseStatusCode);
	}
	
	@PostMapping
	public String registerNewUserInformation() {
		return "POST: User registered";
	}
	
	@PutMapping
	public String updateUserInformation() {
		return "PUT: User updated";
	}
	
	@DeleteMapping
	public String deleteUserInformation() {
		return "DELETE: User deleted";
	}
	
	
	
}
