package example;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.webservice.sms.adpdigital.JaxRpcMessagingService;
import com.soshiant.webservice.sms.adpdigital.JaxRpcMessagingServiceServiceLocator;
import com.soshiant.webservice.sms.adpdigital.SendResult;

/**
 * Created with IntelliJ IDEA.
 * User: hamid
 * Date: 4/12/12
 * Time: 11:27 PM
 */
public class HelloWorldClient {
  public static void main(String[] argv) {

      try {
          boolean longMessageSupported = false;
          short messageType = 1;
          short messageEncoding = ServerConstants.SMS_MESSAGE_ENCODING_LATIN;

          String userName = "";
          String password = "";
          String sourceNumber = "";
          String[] destinationNumber = {"989121822492"};
          String messageText = "salam";
          JaxRpcMessagingServiceServiceLocator locator = new JaxRpcMessagingServiceServiceLocator();
          JaxRpcMessagingService adpMessagingService =  locator.getMessagingService();
//          SendResult result = locator.getMessagingService().send(userName,password,sourceNumber,destinationNumber,null,null,null,messageType,messageEncoding,longMessageSupported,null,messageText);
          SendResult sendMessageResult = adpMessagingService.send(userName,password,sourceNumber,destinationNumber,null,null,null,messageType,messageEncoding,longMessageSupported,null,messageText);
          System.out.print("Message Status : " + sendMessageResult.getStatus());

          // DebugModel service = locator.get();
          // If authorization is required
          //((MessagingServiceSoapBindingStub)service).setUsername("user3");
          //((MessagingServiceSoapBindingStub)service).setPassword("pass3");
          // invoke business method
         // service.businessMethod();
      } catch (javax.xml.rpc.ServiceException ex) {
          ex.printStackTrace();
      } catch (java.rmi.RemoteException ex) {
          ex.printStackTrace();
      }  
  }
}