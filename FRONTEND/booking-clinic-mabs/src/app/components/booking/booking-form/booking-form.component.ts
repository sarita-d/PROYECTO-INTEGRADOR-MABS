import { PaymentService } from './../../../services/payment.service';
import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { InfoDialogComponent } from 'src/app/shared/dialogs/info-dialog/info-dialog.component';
import { AppService } from 'src/app/services/app.service';
import { Booking } from 'src/app/shared/models/booking-models';
import { Clinic } from 'src/app/shared/models/clinic-model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.scss']
})
export class BookingFormComponent implements OnInit {
  public bookingForm
  public booking = new Booking()
  @Input() clinic: Clinic

  constructor(    public dialog: MatDialog,
    private fb: FormBuilder,
    private service: AppService,
    private paymentService: PaymentService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initForm()
  }
  sendBooking() {
    this.setBooking()
    this.service.createReservation(this.booking).subscribe((result: any)=> {
      const title = "CÓDIGO DE RESERVA: " + result.data
      const info = "Necesitarás el código para poder acceder a la clinic o cancelar la reserva. Por favor guardalo en un lugar seguro"
      this.openDialog( title,info)
    })
  }
  payBooking(){
    this.setBooking()
    this.service.createReservation(this.booking).subscribe((result: any)=> {
      this.paymentService.setBooked({ ...this.booking, locator: result.data})
      this.router.navigate(['payment'])
    })
  }
  initForm() {
    this.bookingForm = this.fb.group({
      date: [new Date(),Validators.required],
      time: ['', Validators.required],
      customers: ['', Validators.required],
      email: ['', Validators.required],
      name: ['', Validators.required]
    });
  }
  setBooking(){
    this.booking.clinicId = this.clinic.id
    this.booking.turnId = this.bookingForm.get('time').value
    this.booking.date = this.bookingForm.get('date').value
    this.booking.person = this.bookingForm.get('customers').value
    this.booking.name = this.bookingForm.get('name').value
    this.booking.email = this.bookingForm.get('email').value
    this.booking.price = this.clinic.price
  }

  openDialog(title: string, info: string): void {
    const dialogRef = this.dialog.open(InfoDialogComponent, {
      width: '350px',
      data: {title: title, info: info}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }


}




