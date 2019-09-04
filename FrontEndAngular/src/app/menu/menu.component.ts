import { Component, OnInit, Input } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';
import { Message } from 'primeng/components/common/api';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],

})
export class MenuComponent implements OnInit {

  items: MenuItem[];

  msgs: Message[] = [];
  @Input()
  message: string;

  constructor() { }

  ngOnInit() {

    this.items = [
      {

        label: 'Administración',
        items: [
          {
            label: 'Vendedores',
            routerLink: ['/persona']
          },
          {
            label: 'Productos',
            routerLink: ['/persona']
          }
        ]
      },
      {
        label: 'Entradas/Salidas',
        items: [
          {
            label: 'Entrada',
            routerLink: ['/kardexin']
          },
          {
            label: 'Salida',
            routerLink: ['/kardexout']
          }
        ]
      }
    ];
  }


}
