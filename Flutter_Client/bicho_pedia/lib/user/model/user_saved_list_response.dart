class UserSavedListResponse {
  String? id;
  String? name;
  String? photo;

  UserSavedListResponse({this.id, this.name, this.photo});

  UserSavedListResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    name = json['name'];
    photo = json['photo'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['name'] = this.name;
    data['photo'] = this.photo;
    return data;
  }
}
