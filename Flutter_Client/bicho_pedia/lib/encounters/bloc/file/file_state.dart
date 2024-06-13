import 'package:bicho_pedia/encounters/model/file_response.dart';

abstract class FileUploadState {
  List<Object> get props => [];
}

class FileUploadInitial extends FileUploadState {}

class FileUploadLoading extends FileUploadState {}

class FileUploadSuccess extends FileUploadState {
  final List<FileResponse> files;

  FileUploadSuccess(this.files);

  @override
  List<Object> get props => [files];
}

class FileUploadFailure extends FileUploadState {
  final String error;

  FileUploadFailure(this.error);

  @override
  List<Object> get props => [error];
}
