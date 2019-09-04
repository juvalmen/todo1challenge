import { Component, OnInit, ViewChild } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { UrlsEnum } from 'src/model/enum/UrlsEnum';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private items: MenuItem[];
  home:MenuItem;
  constructor(private router: Router) { }

  ngOnInit() {
    
  }

}
