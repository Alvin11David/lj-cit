import { Component, signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('CIT Portal');
  protected readonly hasAboutAccess = signal(this.readAccessFlag());
  protected readonly hasToken = signal(this.readToken());

  protected grantAboutAccess(): void {
    localStorage.setItem('citPortalAuth', 'true');
    this.hasAboutAccess.set(true);
  }

  protected revokeAboutAccess(): void {
    localStorage.removeItem('citPortalAuth');
    this.hasAboutAccess.set(false);
  }

  protected grantToken(): void {
    localStorage.setItem('token', 'demo-jwt-token');
    this.hasToken.set(true);
  }

  protected revokeToken(): void {
    localStorage.removeItem('token');
    this.hasToken.set(false);
  }

  private readAccessFlag(): boolean {
    return typeof localStorage !== 'undefined' && localStorage.getItem('citPortalAuth') === 'true';
  }

  private readToken(): boolean {
    return typeof localStorage !== 'undefined' && localStorage.getItem('token') !== null;
  }
}
