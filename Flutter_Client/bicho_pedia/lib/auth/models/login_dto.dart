class LoginDto {
  String? username;
  String? password;

  LoginDto({this.username, this.password});

  LoginDto.fromJson(Map<String, dynamic> json) {
    username = json['username'];
    password = json['password'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['username'] = this.username;
    data['password'] = this.password;
    return data;
  }
}
