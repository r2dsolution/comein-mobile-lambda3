package com.r2dsolution.comein.lambda.handler;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.cognitoidp.model.UserType;
import com.amazonaws.services.lambda.runtime.Context;
import com.r2dsolution.comein.client.AdminCognitoClient;
import com.r2dsolution.comein.cognito.model.CognitoUser;
import com.r2dsolution.comein.entity.BookingInfoM;
import com.r2dsolution.comein.entity.Language;
import com.r2dsolution.comein.entity.TitleM;
import com.r2dsolution.comein.entity.UserKYCInfoM;
import com.r2dsolution.comein.lambda.model.GateWayRequest;
import com.r2dsolution.comein.repository.BookingInfoRepository;
import com.r2dsolution.comein.repository.TitleRepository;
import com.r2dsolution.comein.repository.UserKYCRepository;

public class AddKYCInfoHandler extends BaseHandler<GateWayRequest>{
	
	

	@Override
	protected Map<String, Object> doHandlerRequest(GateWayRequest input, Map<String, Object> output_json, Context context){
		//String profile_email = input.getProfile().getEmail();
		String ownerId = input.getProfile().getComein_id();
		String email = (String) input.getBody().get("email");
		String title = (String) input.getBody().get("title");
//		String language = (String) input.getBody().get("language");
		String firstname = (String) input.getBody().get("firstname");
		String lastname = (String) input.getBody().get("lastname");
		String name = (String) input.getBody().get("name");
		String birthdate = (String) input.getBody().get("birthdate");
		String cardId = (String) input.getBody().get("card-id");
		String cardType = (String) input.getBody().get("card-type");
		String cardExpireDate = (String) input.getBody().get("card-expire-date");
		String displayName = (String) input.getBody().get("display-name");
//		String refImage = (String) input.getBody().get("ref-image");
//		String signImage = (String) input.getBody().get("sign-image");
		
//		log("email="+email+", ref-type="+refType+", owner="+ownerId);
		log("email="+email);
		UserKYCRepository kycRepo = ctx.getBean(UserKYCRepository.class);
		AdminCognitoClient client = ctx.getBean(AdminCognitoClient.class);
		
//		UserType cognitoUser = client.findByEmail(email);
//		if (cognitoUser==null) {
//			log("not-found user by email: "+email);
			CognitoUser	dataUser = new CognitoUser();
			dataUser.setUsername(UUID.randomUUID().toString()+"@thecomein.com");
			dataUser.setEmail(email);
			dataUser.setTitle(title);
			dataUser.setFirstname(firstname);
			dataUser.setLastname(lastname);
			dataUser.setRefName(name);
			dataUser.setCardId(cardId);
			dataUser.setCardType(cardType);
			dataUser.setBirthDate(birthdate);
			dataUser.setCardExpireDate(cardExpireDate);
			UserType cognitoUser  = client.addUser(dataUser);
	//	}
		
		//UserType 
//		String refId = "";
//		if (refType!=null && refType.equals(UserKYCInfoM.COMEIN_ID_REF)) {
//			refId = client.getAttr(cognitoUser, AdminCognitoClient.ATTRIBUTE_COMEIN_ID);
//		} else if (refType!=null && refType.equals(UserKYCInfoM.EMAIL_REF)) {
//			refId = email;
//		} else if (refType!=null && refType.equals(UserKYCInfoM.NATIONAL_CARD_REF)) {
//			refId = cardId;
//		}
//		log("card-id: "+refId);
//		String refId = client.getAttr(cognitoUser, AdminCognitoClient.ATTRIBUTE_COMEIN_ID);
		String refId = client.getAttr(cognitoUser, AdminCognitoClient.ATTRIBUTE_USERNAME);		
		log("ref-id: "+refId);
		//UserKYCInfoM user = kycRepo.findByEmailAndProfile(email, profile_email);
		UserKYCInfoM user = null;//kycRepo.findByRefIdAndRefTypeAndOwnerId(refId, refType, ownerId);
		if (user==null) {
			user = new UserKYCInfoM();
			//user.setProfile(profile_email);
			user.setOwnerId(ownerId);
			user.setRefId(refId);
			user.setRefType(UserKYCInfoM.COMEIN_ID_REF);
			user.setRefName(displayName);
			user = kycRepo.save(user);
			log("save-user id:"+user.getId());
		}
//		String titleCode = "";
//		TitleRepository titleRepo = ctx.getBean(TitleRepository.class);
//		if (language!=null && language.trim().equals(Language.TH)) {
//			Optional<TitleM> opt = titleRepo.findByNameTh(title);
//			if (opt.isPresent()) {
//				titleCode = opt.get().getCode();
//			}
//		} else {
//			Optional<TitleM> opt = titleRepo.findByNameEn(title);
//			if (opt.isPresent()) {
//				titleCode = opt.get().getCode();
//			}
//		}
//		
//		//user.setEmail(email);
//		user.setTitle(titleCode);
//		user.setFirstname(firstname);
//		user.setLastname(lastname);
//		user.setLanguage(language);
//		user.setRefImage(refImage);
//		user.setSignImage(signImage);
//		
//		
//		
//		
//		kycRepo.save(user);
		
		log("add success");
		return output_json;
	}

	
}