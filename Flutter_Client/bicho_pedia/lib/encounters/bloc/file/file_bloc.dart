import 'package:bicho_pedia/encounters/bloc/file/file_event.dart';
import 'package:bicho_pedia/encounters/bloc/file/file_state.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class FileUploadBloc extends Bloc<FileUploadEvent, FileUploadState> {
  final EncountersRepository repository;

  FileUploadBloc(this.repository) : super(FileUploadInitial());

  Stream<FileUploadState> mapEventToState(FileUploadEvent event) async* {
    if (event is UploadFilesEvent) {
      yield FileUploadLoading();
      try {
        final files = await repository.uploadFiles(event.files);
        yield FileUploadSuccess(files);
      } catch (e) {
        yield FileUploadFailure(e.toString());
      }
    }
  }
}
