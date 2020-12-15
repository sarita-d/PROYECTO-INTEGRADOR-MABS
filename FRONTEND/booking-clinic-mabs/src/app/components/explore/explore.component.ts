import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/services/app.service';
import { LightClinic } from 'src/app/shared/models/clinic-light-model';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.scss']
})
export class ExploreComponent implements OnInit {
  public clinics: LightClinic[];
  constructor(private service: AppService) { }

  ngOnInit(): void {
    this.service.getAllClinics().subscribe((result: any) => {
      this.clinics = result.data;
    })
  }

}
