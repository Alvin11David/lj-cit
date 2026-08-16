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
    public String process();

}



@Component("flutterwave")
class FlutterwavePaymentService implements PaymentService{

    @Override
    public String initiate() {
        return "Flutterwave is initiating";
    }

    @Override
    public String process() {
        return "Flutterwave is processing";
    }

}

@Component("stripe")
class StripePaymentService implements PaymentService{

    @Override
    public String initiate() {
        return "Stripe is initiating";
    }

    @Override
    public String process() {
        return "Stripe is processing";
    }
}

@Component("mtn")
class MTNPaymentService implements PaymentService{

    @Override
    public String initiate() {
        return "MTN is initiating";
    }

    @Override
    public String process() {
        return "MTN is processing";
    }
}



interface PaymentServiceFactory{
    PaymentService getProvider(String provider);
}



@Component
class MainPaymentServiceFactory implements PaymentServiceFactory{
    private final Map<String, PaymentService> paymentServices;
    MainPaymentServiceFactory(Map<String, PaymentService> paymentServices){
        this.paymentServices=paymentServices;
    }
    @Override
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
        return  paymentService.initiate() + "........" + paymentService.process();
    }

}


@Service
class JumiaOrderCheckoutService{
    private final PaymentServiceFactory paymentServiceFactory;
    public JumiaOrderCheckoutService( PaymentServiceFactory paymentServiceFactory){
        this.paymentServiceFactory = paymentServiceFactory;
    }

    public String checkout() {
        PaymentService paymentService = paymentServiceFactory.getProvider("mtn");
        return paymentService.initiate() + " ... " + paymentService.process();
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
        PaymentService paymentService = paymentServiceFactory.getProvider("flutterwave");
        return paymentService.initiate() + " ... " + paymentService.process();
    }
}