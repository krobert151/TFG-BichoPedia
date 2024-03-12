import 'package:bicho_pedia/encounters/model/markes_response.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'markers_event.dart';
part 'markers_state.dart';

class MarkersBloc extends Bloc<MarkersEvent, MarkersState> {
  final EncountersRepository encountersRepository;

  MarkersBloc(this.encountersRepository) : super(MarkersInitial()) {
    on<DoMarkersEvent>(_getMarkers);
  }

  void _getMarkers(DoMarkersEvent event, Emitter<MarkersState> emit) async {
    try {
      final response = await encountersRepository.getMarkers();
      emit(MarkersSuccess(response));
    } on Exception catch (e) {
      emit(MarkersError(e.toString()));
    }
  }
}
