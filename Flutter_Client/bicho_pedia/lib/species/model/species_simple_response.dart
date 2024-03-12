class SpeciesSimpleResponse {
  String? id;
  String? url;
  String? scientificName;

  SpeciesSimpleResponse({this.id, this.url, this.scientificName});

  SpeciesSimpleResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    url = json['url'];
    scientificName = json['scientificName'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['url'] = this.url;
    data['scientificName'] = this.scientificName;
    return data;
  }
}
