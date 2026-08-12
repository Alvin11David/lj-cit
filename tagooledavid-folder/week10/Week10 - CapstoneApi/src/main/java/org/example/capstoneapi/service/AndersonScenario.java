package org.example.capstoneapi.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Primary
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



// this will use the primary
@Service
class AmazonOrderCheckoutService{
    private final PaymentService paymentService;
    public AmazonOrderCheckoutService(PaymentService paymentService){
        this.paymentService = paymentService;
    }

}


@Service
class JumiaOrderCheckoutService{
    private final PaymentService paymentService;
    public JumiaOrderCheckoutService( @Qualifier("mtn") PaymentService paymentService){
        this.paymentService = paymentService;
    }
}


// this gives room for polymorhism since various classes implement the interface so if we inject the interfcae, we get access to the classes
// hence giving room for dynamic selection at runtime
@Service
class DynamicOrderCheckoutService{
    private final List<PaymentService> paymentServices;
    public DynamicOrderCheckoutService(List<PaymentService> paymentServices){
        this.paymentServices = paymentServices;
    }

    // then here si where the code for selecting which service is going
}



// this is explicitly saying me i only deal with flutterwave
@Service
class ExplicitCheckoutService{
    private final FlutterwavePaymentService flutterwavePaymentService;
    public ExplicitCheckoutService(FlutterwavePaymentService flutterwavePaymentService){
        this.flutterwavePaymentService = flutterwavePaymentService;
    }
}