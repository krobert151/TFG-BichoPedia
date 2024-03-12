class MarkersResponse {
  String? id;
  String? latitud;
  String? longuitud;

  MarkersResponse({this.id, this.latitud, this.longuitud});

  MarkersResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    latitud = json['latitud'];
    longuitud = json['longuitud'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['latitud'] = this.latitud;
    data['longuitud'] = this.longuitud;
    return data;
  }
}
