class UserDetailsResponse {
  String? username;
  String? email;
  String? userPhoto;
  int? encounters;
  int? level;
  int? articles;
  int? percentExp;

  UserDetailsResponse(
      {this.username,
      this.email,
      this.userPhoto,
      this.encounters,
      this.level,
      this.articles,
      this.percentExp});

  UserDetailsResponse.fromJson(Map<String, dynamic> json) {
    username = json['username'];
    email = json['email'];
    userPhoto = json['userPhoto'];
    encounters = json['encounters'];
    level = json['level'];
    articles = json['articles'];
    percentExp = json['percentExp'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['username'] = this.username;
    data['email'] = this.email;
    data['userPhoto'] = this.userPhoto;
    data['encounters'] = this.encounters;
    data['level'] = this.level;
    data['articles'] = this.articles;
    data['percentExp'] = this.percentExp;
    return data;
  }
}
