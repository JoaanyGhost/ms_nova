import { faEye, faEyeSlash, faPaperPlane, faCreditCard, faEnvelope } from '@fortawesome/free-regular-svg-icons';
import { faCartShopping, faMoneyBill, faMapLocationDot, faPhone, faArrowUp } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Component, HostListener, OnInit } from '@angular/core';


@Component({
  selector: 'app-header',
  imports: [FontAwesomeModule],
  templateUrl: './header.html',
  styleUrl: './header.scss',
})
export class Header {faEye = faEye
  faEyeSlash = faEyeSlash;
  faCartShopping = faCartShopping;
  faPaperPlane = faPaperPlane;
  faCreditCard = faCreditCard;
  faMoneyBill = faMoneyBill;
  faEnvelope = faEnvelope;
  faMapLocationDot = faMapLocationDot;
  faPhone = faPhone;
  faArrowUp = faArrowUp;

  isBackTopScrolled = false;
  scrollOffset = 100;

  private reset_close: HTMLElement | null = null;
  private reset: HTMLElement | null = null;

  ngOnInit(): void {
    this.reset_close = document.querySelector('.reset-close');
    this.reset = document.querySelector('.reset');


    if (this.reset) {
      this.reset.classList.add('active');
    }

  }

  /* ACTIVADOR LEFT, MIDDLE O RIGHT */
  switchBanner(name: string, event?: Event): void {
    event?.preventDefault();

    const banner = document.querySelector('#banner') as HTMLElement | null;
    if (!banner) return;

    banner.classList.remove('left', 'middle', 'right');
    banner.classList.add(name);
  }

  /* BOTON DE CIERRE */
  closeBanner(event?: Event): void {
    event?.preventDefault();

    const banner = document.querySelector('#banner') as HTMLElement | null;
    if (!banner) return;

    banner.classList.remove('left', 'middle', 'right');
  }





  @HostListener('window:scroll', [])
  onWindowScroll() {
    if (window.scrollY > this.scrollOffset) {
      this.isBackTopScrolled = true;
    } else {
      this.isBackTopScrolled = false;
    }
  }


  scrollTop(){
    window.scrollTo(0, 0);
  }

}

