class ImageDTO {
  String? name;
  String? uri;
  String? type;
  String? size;

  ImageDTO({this.name, this.uri, this.type, this.size});

  ImageDTO.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    uri = json['uri'];
    type = json['type'];
    size = json['size'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['uri'] = this.uri;
    data['type'] = this.type;
    data['size'] = this.size;
    return data;
  }
}
