import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private toastService: ToastrService) { }

  success(msg: string) {
    this.toastService.success(msg);
  }

  error(msg: string) {
    this.toastService.error(msg);
  }

  info(msg: string) {
    this.toastService.info(msg);
  }

  warning(msg: string) {
    this.toastService.warning(msg);
  }
}
