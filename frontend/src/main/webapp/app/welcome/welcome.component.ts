import { Component } from '@angular/core';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss'],
  standalone: true,
  imports: [SharedModule]
})
export class WelcomeComponent {

}
