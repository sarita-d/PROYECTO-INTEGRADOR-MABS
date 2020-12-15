import { HttpClient } from '@angular/common/http';
import { PaymentIntent, PaymentConfirm } from './../shared/models/payment-model';
import { Injectable } from '@angular/core';
import { Booked } from 'src/app/shared/models/payment-model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private booked: Booked
  private API = "http://localhost:8080//booking-clinic/payments/"
  constructor(
    private http: HttpClient
  ) { }
  setBooked(booked: Booked) {
    this.booked = booked
  }
  getBooked(){
    return this.booked
  }
  buy(payment: PaymentIntent) {
    return this.http.post(this.API + 'paymentIntent', payment)
  }
  cancel(id: string) {
    return this.http.post(this.API + 'cancel/' + id, {})
  }
  confirm(paymentConfirm: PaymentConfirm) {
    return this.http.post(this.API + 'confirm/' +paymentConfirm.paymentId, paymentConfirm)
  }
}
