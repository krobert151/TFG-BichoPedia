class LoginResponse {
  String? id;
  String? username;
  String? token;

  LoginResponse({this.id, this.token, this.username});

  LoginResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    token = json['token'];
    username = json['username'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['token'] = this.token;
    data['username'] = this.username;
    return data;
  }
}
