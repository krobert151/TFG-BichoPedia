class SpecieDetailsResponse {
  String? scientificName;
  String? danger;
  String? mainPhoto;
  List<Article>? info;
  List<Article>? identification;
  List<Article>? cares;

  SpecieDetailsResponse(
      {this.scientificName,
      this.danger,
      this.mainPhoto,
      this.info,
      this.identification,
      this.cares});

  SpecieDetailsResponse.fromJson(Map<String, dynamic> json) {
    scientificName = json['ScientificName'];
    danger = json['danger'];
    mainPhoto = json['mainPhoto'];
    if (json['info'] != null) {
      info = <Article>[];
      json['info'].forEach((v) {
        info!.add(new Article.fromJson(v));
      });
    }
    if (json['identification'] != null) {
      identification = <Article>[];
      json['identification'].forEach((v) {
        identification!.add(new Article.fromJson(v));
      });
    }
    if (json['cares'] != null) {
      cares = <Article>[];
      json['cares'].forEach((v) {
        cares!.add(new Article.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['ScientificName'] = this.scientificName;
    data['danger'] = this.danger;
    data['mainPhoto'] = this.mainPhoto;
    if (this.info != null) {
      data['info'] = this.info!.map((v) => v.toJson()).toList();
    }
    if (this.identification != null) {
      data['identification'] =
          this.identification!.map((v) => v.toJson()).toList();
    }
    if (this.cares != null) {
      data['cares'] = this.cares!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Article {
  String? title;
  String? description;
  List<String>? archives;

  Article({this.title, this.description, this.archives});

  Article.fromJson(Map<String, dynamic> json) {
    title = json['title'];
    description = json['description'];
    archives = json['archives'].cast<String>();
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['title'] = this.title;
    data['description'] = this.description;
    data['archives'] = this.archives;
    return data;
  }
}
