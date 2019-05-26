export class SignUpInfo {
  login: string;
  email: string;
  role: string[];
  password: string;
  fullName: string;
  gender: string;
  age: string;
  weight: string;
  growth: string;
  goal: string;


  constructor(fullName: string, login: string, email: string, password: string,
  gender: string,   age: string,   weight: string,   growth: string,   goal: string) {
    this.fullName = fullName;
    this.login = login;
    this.email = email;
    this.password = password;
    this.role = ['user'];
    this.gender=gender;
    this.age=age;
    this.weight=weight;
    this.growth=growth;
    this.goal=goal;
  }
}
