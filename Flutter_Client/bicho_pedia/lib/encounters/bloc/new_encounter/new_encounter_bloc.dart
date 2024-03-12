import 'package:bicho_pedia/encounters/model/new_encounter_dto.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'new_encounter_event.dart';
part 'new_encounter_state.dart';

class NewEncounterBloc extends Bloc<NewEncounterEvent, NewEncounterState> {
  final EncountersRepository encountersRepository;
  NewEncounterBloc(this.encountersRepository) : super(NewEncounterInitial()) {
    on<DoNewEncounterEvent>(_registerEncounter);
  }

  void _registerEncounter(
      DoNewEncounterEvent event, Emitter<NewEncounterState> emit) async {
    try {
      final NewEncounterDTO encounter = NewEncounterDTO(
          specieId: event.specieId,
          description: event.description,
          location: event.location,
          photos: event.photos);
      final response = await encountersRepository.newEncounter(encounter);
      emit(NewEncounterSucess(response));
    } on Exception catch (e) {
      emit(NewEncounterError(e.toString()));
    }
  }
}
