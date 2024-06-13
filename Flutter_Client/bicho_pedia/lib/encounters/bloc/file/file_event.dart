import 'dart:io';

abstract class FileUploadEvent {
  List<Object> get props => [];
}

class UploadFilesEvent extends FileUploadEvent {
  final List<File> files;

  UploadFilesEvent(this.files);

  @override
  List<Object> get props => [files];
}
