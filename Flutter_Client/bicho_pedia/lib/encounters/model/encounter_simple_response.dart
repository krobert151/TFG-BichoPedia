class EncounterSimpleResponse {
  String? id;
  String? scientificName;
  String? description;
  String? photo;

  EncounterSimpleResponse(
      {this.id, this.scientificName, this.description, this.photo});

  EncounterSimpleResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    scientificName = json['scientificName'];
    description = json['description'];
    photo = json['photo'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['scientificName'] = this.scientificName;
    data['description'] = this.description;
    data['photo'] = this.photo;
    return data;
  }
}
