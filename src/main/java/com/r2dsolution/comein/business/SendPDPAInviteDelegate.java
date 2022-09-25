package com.r2dsolution.comein.business;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.cognitoidp.model.UserType;
import com.r2dsolution.comein.client.AdminCognitoClient;
import com.r2dsolution.comein.entity.PDPAInviteTokenM;
import com.r2dsolution.comein.repository.PDPAInviteTokenRepository;


@Component
public class SendPDPAInviteDelegate extends BusinessDelegate{
	
//	@Autowired
//	private AdminCognitoClient cognitoClient;
	
	@Autowired
	private PDPAInviteTokenRepository tokenRepository;

	public void invitePDPA(String email,String secret) {
		//PDPAInviteRequest req = bodyToModel(message,context);
		//String email=req.getCognito_email();
//		String target_email = "";
//		String ref_name = "";
		String tokenStr = UUID.randomUUID().toString();
		log("email:"+email);
		if (email!=null && !email.trim().isEmpty()) {
			log("find cognito by email"+email);
			//UserType user = cognitoClient.findByEmail(email);
			
			//String comeinId = cognitoClient.getAttr(user, cognitoClient.ATTRIBUTE_COMEIN_ID);
			//String secretCode = cognitoClient.getAttr(user, cognitoClient.ATTRIBUTE_COMEIN_CARD_ID);
			//ref_name = cognitoClient.getAttr(user, cognitoClient.ATTRIBUTE_REF_NAME);
			//target_email = user.getUsername();
		
			Optional<PDPAInviteTokenM> opt = tokenRepository.findByComeinIdAndStatus(email,PDPAInviteTokenM.STATUS_ACTIVE);
			if (opt.isPresent()) {
				PDPAInviteTokenM activeToken = opt.get();
				activeToken.setStatus(PDPAInviteTokenM.STATUS_EXPIRED);
				tokenRepository.save(activeToken);
			}
			
			PDPAInviteTokenM token = new PDPAInviteTokenM();
			token.setComeinId(email);
			token.setMaxUsed(5);
			token.setSecretCode(secret);
			token.setToken(tokenStr);
			token.setStatus(PDPAInviteTokenM.STATUS_ACTIVE);
			
			Calendar cal = Calendar.getInstance(Locale.ENGLISH);	
			cal.add(Calendar.DATE, 30);
			Date d = cal.getTime();
			token.setExpireDate(new Timestamp(d.getTime()));
			
			tokenRepository.save(token);
		};
	}
}
