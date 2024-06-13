class FileResponse {
  final String name;
  final int size;
  final String type;
  final String uri;

  FileResponse({
    required this.name,
    required this.size,
    required this.type,
    required this.uri,
  });

  factory FileResponse.fromJson(Map<String, dynamic> json) {
    return FileResponse(
      name: json['name'],
      size: json['size'],
      type: json['type'],
      uri: json['uri'],
    );
  }
}
