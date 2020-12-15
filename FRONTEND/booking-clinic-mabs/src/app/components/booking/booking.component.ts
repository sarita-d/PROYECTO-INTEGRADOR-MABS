import { BookingFormComponent } from './booking-form/booking-form.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Booking } from 'src/app/shared/models/booking-models';
import { AppService } from 'src/app/services/app.service';
import { ActivatedRoute } from '@angular/router';
import { Clinic } from 'src/app/shared/models/clinic-model';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.scss']
})
export class BookingComponent implements OnInit {
  @ViewChild(BookingFormComponent) bookingForm: BookingFormComponent

  public clinic = new Clinic()
  private idClinic: number
  constructor(
    private service: AppService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.idClinic = Number(this.route.snapshot.paramMap.get('id'))
    this.getClinic()
  }
  getClinic() {
    this.service.getClinic(this.idClinic).subscribe((result:any)=> {
      this.bookingForm.clinic = result.data
      this.clinic = result.data
    })
  }



}
