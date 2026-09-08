import { Component } from '@angular/core';
import { Header } from "../../components/header/header";
import { PersonalizeStyle } from "../../components/personalize-style/personalize-style";

@Component({
  selector: 'app-home-page',
  imports: [Header, PersonalizeStyle],
  templateUrl: './home-page.html',
  styleUrl: './home-page.scss',
})
export class HomePage {}
