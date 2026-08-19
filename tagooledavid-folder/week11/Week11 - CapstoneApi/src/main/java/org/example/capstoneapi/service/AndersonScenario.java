package org.example.capstoneapi.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

interface PaymentService{
    public String initiate();


}



class FlutterwavePaymentService implements PaymentService{

    @Override
    public String initiate() {
        return "Flutterwave is initiating to proess and return the transaction payload ";
    }


}

class StripePaymentService implements PaymentService{

    @Override
    public String initiate() {
        return "Stripe is initiating to proess and return the transaction payload ";
    }

}


class MTNPaymentService implements PaymentService{

    @Override
    public String initiate() {
        return "MTN is initiating to proess and return the transaction payload ";
    }


}





@Component
class PaymentServiceFactory{
    private final Map<String, PaymentService> paymentServices;
    PaymentServiceFactory(Map<String, PaymentService> paymentServices){
        this.paymentServices=paymentServices;
    }

    public PaymentService getProvider(String provider) {
        PaymentService selectedProvider = paymentServices.get(provider);
        if (selectedProvider == null){
            throw new SelectedPaymentProviderNotFound("No Payment Provider Registred for "+provider);
        }
        return selectedProvider;
    }
    }




@Service
class AmazonOrderCheckoutService{
    private final PaymentServiceFactory paymentServiceFactory;
    public AmazonOrderCheckoutService(PaymentServiceFactory paymentServiceFactory){
        this.paymentServiceFactory = paymentServiceFactory;
    }

    public String checkout(String provider){
        PaymentService paymentService = paymentServiceFactory.getProvider(provider);
        return  paymentService.initiate();
    }

}


@Service
class JumiaOrderCheckoutService{
    private final PaymentServiceFactory paymentServiceFactory;
    public JumiaOrderCheckoutService( PaymentServiceFactory paymentServiceFactory){
        this.paymentServiceFactory = paymentServiceFactory;
    }

    public String checkout() {
        PaymentService paymentService = paymentServiceFactory.getProvider("mTNPaymentService");
        return paymentService.initiate();
    }
}


class SelectedPaymentProviderNotFound extends RuntimeException{
    public SelectedPaymentProviderNotFound(String message){
        super(message);
    }
}




@Service
class ExplicitCheckoutService{
    private final PaymentServiceFactory paymentServiceFactory;
    public ExplicitCheckoutService(PaymentServiceFactory paymentServiceFactory){
        this.paymentServiceFactory = paymentServiceFactory;
    }
    public String checkout() {
        PaymentService paymentService = paymentServiceFactory.getProvider("flutterwavePaymentService");
        return paymentService.initiate();
    }
}