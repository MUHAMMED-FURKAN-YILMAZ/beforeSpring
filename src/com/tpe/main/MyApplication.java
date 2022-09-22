package com.tpe.main;

import com.tpe.domain.Message;
import com.tpe.repository.DBRepository;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.SMSService;
import com.tpe.service.WhatsAppService;

public class MyApplication {

	public static void main(String[] args) {
		
		/*
	1- Normalde repo objelerini servis classlarindaki cons araciligi ile olusturuyoruz. (Genelde parametresiz)
	2- Bu sefer parametreli olusturdk ki baska yerden veri atayabilelim.
	3- Classlarda parametreli cons oluşturunca ve parametresiz oluşturmayınca objeleri mecburen 
		parametreli olusturmak zorunda kaldik.
	4- Application classta obje olusturunca bizden parametre istedi.
	5- Birden fazla parametre tipi oldugu icin biz bu parametreyi if clause lari ile dinamik hale getirdik. 
		Sonrasinda yeni bir repo gerekirse bunu if clause a eklemkek yeterli hale geldi.
	6- Son olarak yukarida olusturulan bir variable ile parametreleri depoladik ki bunları if clause'larda 
		kullanalim.
			 * */
		
		String serviceName="mailService";
		String repositoryName="dbRepository";
		if(args.length>0) {
			serviceName=args[0];
			repositoryName=args[1];
		}
		
		Repository repository=null;
		if(repositoryName.equalsIgnoreCase("fileRepository")) {
			repository=new FileRepository();
		}else {
			repository=new DBRepository();
		}
		
		Message message=new Message();
		message.setMessage("Your order  was sent");
	
		MessageService messageService=null;

		if(serviceName.equalsIgnoreCase("whatsAppService")) {
			messageService=new WhatsAppService(repository);
			messageService.sendMessage(message);
		}else if(serviceName.equalsIgnoreCase("smsService")) {
			messageService=new SMSService(repository);
			messageService.sendMessage(message);
		}else {
			messageService=new MailService(repository);
			messageService.sendMessage(message);
		}
		

//		MessageService whatsAppService=new WhatsAppService();
//		whatsAppService.sendMessage();
//		
//		MessageService smsService=new SMSService();
//		smsService.sendMessage();
	}

}
