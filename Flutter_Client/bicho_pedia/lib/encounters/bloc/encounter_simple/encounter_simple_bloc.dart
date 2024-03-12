import 'package:bicho_pedia/encounters/model/encounter_simple_response.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'encounter_simple_event.dart';
part 'encounter_simple_state.dart';

class EncounterSimpleBloc
    extends Bloc<EncounterSimpleEvent, EncounterSimpleState> {
  final EncountersRepository encountersRepository;

  EncounterSimpleBloc(this.encountersRepository)
      : super(EncounterSimpleInitial()) {
    on<DoEncounterSimpleEvent>(_getSimpleEncounter);
  }

  void _getSimpleEncounter(
      DoEncounterSimpleEvent event, Emitter<EncounterSimpleState> emit) async {
    try {
      final response = await encountersRepository.getMostLikedEncounters(
          event.count, event.page);
      emit(EncounterSimpleSuccess(response));
    } on Exception catch (e) {
      emit(EncounterSimpleError(e.toString()));
    }
  }
}
