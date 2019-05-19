export class SignUpInfo {
  fullName: string;
  login: string;
  email: string;
  role: string[];
  password: string;

  constructor(fullName: string, login: string, email: string, password: string) {
    this.fullName = fullName;
    this.login = login;
    this.email = email;
    this.password = password;
    this.role = ['user'];
  }
}
