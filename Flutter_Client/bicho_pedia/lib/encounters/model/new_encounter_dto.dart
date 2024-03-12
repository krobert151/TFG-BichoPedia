class NewEncounterDTO {
  String? specieId;
  String? description;
  String? location;
  List<String>? photos;

  NewEncounterDTO(
      {this.specieId, this.description, this.location, this.photos});

  NewEncounterDTO.fromJson(Map<String, dynamic> json) {
    specieId = json['specieId'];
    description = json['description'];
    location = json['location'];
    photos = json['photos'].cast<String>();
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['specieId'] = this.specieId;
    data['description'] = this.description;
    data['location'] = this.location;
    data['photos'] = this.photos;
    return data;
  }
}
