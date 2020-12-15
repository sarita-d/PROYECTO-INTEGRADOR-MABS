import { Injectable } from '@angular/core';
import { LightClinic } from 'src/app/shared/models/clinic-light-model';
import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Booking } from 'src/app/shared/models/booking-models';

const API = 'http://localhost:8080/booking-clinic/v1/clinics/'
@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(
    private http: HttpClient
  ) { }

  getAllClinics() {
    return this.http.get( API + 'clinics')
  }
  getClinic(id: number) {
    return this.http.get(API + 'clinic'+ '/'+ id)
  }
  createReservation(booking: Booking) {
    return this.http.post(API + 'reservation', booking)
  }
  cancelReservation(reservationCode: string) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
    return this.http.delete(API + 'deleteReservation?locator='+ reservationCode, options)
  }

  getAllClinicsMock() {
    const clinics: LightClinic[] = []
    let clinic = new LightClinic()
    clinic.address = 'Gran Via 123';
    clinic.id =  1
    clinic.image = ""
    clinic.name = "Clinica Goob Hope"

    const clinic2: LightClinic = {
      address: "Gran Rambla 145",
      id: 2,
      image: "",
      name: "Clinica Americana"
    }
    clinics.push(clinic)
    clinics.push(clinic2)

    return of(clinics)
  }
}
