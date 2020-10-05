import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class FFMMService {
  constructor(private http: HttpClient) { }

  private static FFMM_URL = 'http://localhost:8080/FFMM/api/fondosmutuos';

  async fetchFFMM() {
    try {
      const data: any = await this.http.get(FFMMService.FFMM_URL).toPromise();
      return data;
    } catch (error) {
      console.error(`Error occurred: ${error}`);
    }
  }
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [ FFMMService ],
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  FFMM: any[] = [];

  constructor(private FFMMService: FFMMService) { }

  ngOnInit() {
    this.FFMMService.fetchFFMM().then(data => {
      this.FFMM = data;
    });
  }
}