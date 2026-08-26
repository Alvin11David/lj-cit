package org.example.capstoneapi.service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

interface PaymentService{
    public String providerId();
    public String initiate();

}


@Component
class FlutterwavePaymentService implements PaymentService{

    @Override
    public String providerId() {
        return "flutterwave";
    }

    @Override
    public String initiate() {
        return "Flutterwave is initiating to proess and return the transaction payload ";
    }


}

@Component
class StripePaymentService implements PaymentService{

    @Override
    public String providerId() {
        return "stripe";
    }

    @Override
    public String initiate() {
        return "Stripe is initiating to proess and return the transaction payload ";
    }

}

@Component
class MTNPaymentService implements PaymentService{

    @Override
    public String providerId() {
        return "mtn";
    }

    @Override
    public String initiate() {
        return "MTN is initiating to proess and return the transaction payload ";
    }


}





@Component
class PaymentServiceFactory{
    private final Map<String, PaymentService> paymentServices;
    PaymentServiceFactory(List<PaymentService> allProviders){
        this.paymentServices=allProviders.stream().collect(Collectors.toMap(PaymentService::providerId,p->p));
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
        PaymentService paymentService = paymentServiceFactory.getProvider("mtn");
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
        PaymentService paymentService = paymentServiceFactory.getProvider("flutterwave");
        return paymentService.initiate();
    }

}


@Component
class PaymentDemo implements CommandLineRunner{
    private final AmazonOrderCheckoutService amazonOrderCheckoutService;
    private final JumiaOrderCheckoutService jumiaOrderCheckoutService;
    private final ExplicitCheckoutService explicitCheckoutService;

    public PaymentDemo( AmazonOrderCheckoutService amazonOrderCheckoutService,
                        JumiaOrderCheckoutService jumiaOrderCheckoutService,
                        ExplicitCheckoutService explicitCheckoutService
                        ){
        this.amazonOrderCheckoutService= amazonOrderCheckoutService;
        this.jumiaOrderCheckoutService = jumiaOrderCheckoutService;
        this.explicitCheckoutService = explicitCheckoutService;
    }



    @Override
    public void run(String... args) {
        System.out.println("\n      PAYMENT FACTORY DEMO ");

        System.out.println("\n      Amazon, dynamic provider: flutterwave ");
        System.out.println(amazonOrderCheckoutService.checkout("flutterwave"));

        System.out.println("\n      Amazon, dynamic provider: mtn ");
        System.out.println(amazonOrderCheckoutService.checkout("mtn"));

        System.out.println("\n       Amazon, dynamic provider: stripe  ");
        System.out.println(amazonOrderCheckoutService.checkout("stripe"));

        System.out.println("\n     Jumia, fixed provider (always mtn) ");
        System.out.println(jumiaOrderCheckoutService.checkout());

        System.out.println("\n    Explicit, fixed provider (always flutterwave) ");
        System.out.println(explicitCheckoutService.checkout());

        System.out.println("\n     Amazon, unregistered provider: paypal (expect exception) ");
        try {
            amazonOrderCheckoutService.checkout("paypal");
        } catch (SelectedPaymentProviderNotFound ex) {
            System.out.println("Caught expected exception: " + ex.getMessage());
        }


    }
}