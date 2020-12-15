import { PaymentService } from './../../services/payment.service';
import { PaymentIntent, Booked, PaymentConfirm } from './../../shared/models/payment-model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { StripeService, StripeCardComponent } from 'ngx-stripe';
import {
  StripeCardElementOptions,
  StripeElementsOptions
} from '@stripe/stripe-js';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})

export class PaymentComponent implements OnInit {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;
  cardOptions: StripeCardElementOptions = {
    style: {
      base: {
        iconColor: '#666EE8',
        color: '#31325F',
        fontWeight: '300',
        fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
        fontSize: '18px',
        '::placeholder': {
          color: '#CFD7E0'
        }
      }
    }
  };
   elementsOptions: StripeElementsOptions = {
    locale: 'es'
  };
 
  stripeTest: FormGroup;
  booked: Booked
  bookedConfirm: string
 successMessage: string = 'Espera...'
  constructor(private fb: FormBuilder, private stripeService: StripeService, private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.booked = this.paymentService.getBooked()
    this.stripeTest = this.fb.group({
      name: ['', [Validators.required]]
    });
  }
  cancel() {
    this.paymentService.cancel(this.bookedConfirm).subscribe((data: any)=> {
      this.successMessage = "Pago cancelado con éxito. Mire su bandeja de entrada"
    })
  }
  confirm(){
    const paymentConfirm: PaymentConfirm = {
      email: this.booked.email,
      locator: this.booked.locator,
      name: this.booked.name,
      paymentId: this.bookedConfirm
    }
    this.paymentService.confirm(paymentConfirm).subscribe((data: any)=> {
      this.successMessage = "Pago confirmado. Mire su bandeja de entrada"
    })
  }
  buy(): void {
    const name = this.stripeTest.get('name').value;
    this.stripeService
      .createToken(this.card.getCard(), { name })
      .subscribe((result) => {
        if (result.token) {
          // Use the token
        const paymentIntent: PaymentIntent =  {
          description: this.booked.name + ': ' + this.booked.locator,
          price: this.booked.price
        }
        this.executeIntent(paymentIntent)
        } else if (result.error) {
          // Error creating the token
          console.log(result.error.message);
        }
      });
  }
  executeIntent(payment: PaymentIntent) {
    this.paymentService.buy(payment).subscribe((result: any) => this.bookedConfirm = result.id)
  }

}
