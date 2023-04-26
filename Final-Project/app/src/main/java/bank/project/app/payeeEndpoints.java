package bank.project.app;

import bank.project.dao.BankService;
import bank.project.dao.Payee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import project.bank.soap.ListPayeeRequest;
import project.bank.soap.ListPayeeResponse;
import project.bank.soap.ServiceStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
       //payee endpoints
@Endpoint
public class payeeEndpoints {

        private static final String url="http://soap.bank.project";
        private Logger logger= LoggerFactory.getLogger(payeeEndpoints.class);

        @Autowired
        private BankService bankService;

        @PayloadRoot(namespace = url,localPart = "listPayeeRequest")
        @ResponsePayload
        public ListPayeeResponse listPayeeResponse(@RequestPayload ListPayeeRequest listPayeeRequest){
            ListPayeeResponse response=new ListPayeeResponse();
            ServiceStatus serviceStatus=new ServiceStatus();
            List<Payee> jpaComponent = bankService.listPayee(listPayeeRequest.getUsername());// pojo objects
            List<project.bank.soap.Payee> listPayee=new ArrayList<>();// xml list of objects as of its empty

            Iterator<Payee> iterator=jpaComponent.iterator();
            while(iterator.hasNext()){
                project.bank.soap.Payee payee= new project.bank.soap.Payee();// XSD POJO
                BeanUtils.copyProperties(iterator.next(),payee);
                listPayee.add(payee);
            }
               // response.setServiceStatus(serviceStatus);
            response.getPayee().addAll(listPayee);
            logger.info(response.toString());
            return response;

        }}








