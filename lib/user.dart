class User {
  int id;
  String birhdate;
  String bloodGroup;
  String gender;
  String name;
  String login;
  Null password;

  User(
      {this.id,
      this.birhdate,
      this.bloodGroup,
      this.gender,
      this.name,
      this.login,
      this.password});

  User.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    birhdate = json['birhdate'];
    bloodGroup = json['bloodGroup'];
    gender = json['gender'];
    name = json['name'];
    login = json['login'];
    password = json['password'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['birhdate'] = this.birhdate;
    data['bloodGroup'] = this.bloodGroup;
    data['gender'] = this.gender;
    data['name'] = this.name;
    data['login'] = this.login;
    data['password'] = this.password;
    return data;
  }
}