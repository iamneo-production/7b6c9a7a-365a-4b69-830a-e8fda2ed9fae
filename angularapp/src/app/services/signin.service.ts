
export class SigninService {
  private email: string;
  private password: string;

  constructor() {
    this.email = "";
    this.password = "";
  }

  setEmail(email: string): void {
    this.email = email;
  }

  setPassword(password: string): void {
    this.password = password;
  }

  /**
   * check if valid email
   */
  checkValidityOfEmail(): boolean {
    var regexp = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$');
    return regexp.test(this.email);
  }

  /**
   * check if valid password
   */
  checkValidityOfPassword(): boolean {
    var regexp = new RegExp('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$');
    return regexp.test(this.password);
  }
}