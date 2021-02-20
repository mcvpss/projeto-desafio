import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'spring-boot-interview-front';
  cliente;

  ngOnInit(){
    this.cliente = "Mauri";
  }
}
