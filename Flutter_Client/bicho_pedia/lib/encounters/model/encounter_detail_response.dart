class EncounterDetailsResponse {
  String? scientificName;
  String? mainPhoto;
  String? danger;
  String? username;
  String? description;
  List<String>? media;
  String? lat;
  String? lon;

  EncounterDetailsResponse(
      {this.scientificName,
      this.mainPhoto,
      this.danger,
      this.username,
      this.description,
      this.media,
      this.lat,
      this.lon});

  EncounterDetailsResponse.fromJson(Map<String, dynamic> json) {
    scientificName = json['scientificName'];
    mainPhoto = json['mainPhoto'];
    danger = json['danger'];
    username = json['username'];
    description = json['description'];
    media = json['media'].cast<String>();
    lat = json['lat'];
    lon = json['lon'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['scientificName'] = this.scientificName;
    data['mainPhoto'] = this.mainPhoto;
    data['danger'] = this.danger;
    data['username'] = this.username;
    data['description'] = this.description;
    data['media'] = this.media;
    data['lat'] = this.lat;
    data['lon'] = this.lon;
    return data;
  }
}
