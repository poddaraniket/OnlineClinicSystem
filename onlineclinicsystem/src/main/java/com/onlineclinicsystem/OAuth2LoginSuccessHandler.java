package com.onlineclinicsystem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private PatientRepository patRepo;

	@Autowired
	private PatientService patientServices;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		CustomOauth2User oAuth2User = (CustomOauth2User) authentication.getPrincipal();

		String email = oAuth2User.getName();
		String name = oAuth2User.getFullName();

		Patient patient = patRepo.findByEmail(email);

		if (patient == null) {
			patientServices.createNewCustomerAfterOAuthLoginSuccess(email, name, AuthenticationProvider.GOOGLE);
		} else {
			patientServices.updateNewCustomerAfterOAuthLoginSuccess(patient, name, AuthenticationProvider.GOOGLE);
		}

		System.out.println("Email via OAuth " + email);

		response.sendRedirect("/patient/dashboard");
		//super.onAuthenticationSuccess(request, response, authentication);
	}

}
