import 'dart:async';

import 'package:bicho_pedia/encounters/model/encounter_response.dart';
import 'package:bicho_pedia/encounters/repository/encounters_repository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'encounter_event.dart';
part 'encounter_state.dart';

class EncounterBloc extends Bloc<EncounterEvent, EncounterState> {
  final EncountersRepository encounterRepository;

  EncounterBloc(this.encounterRepository) : super(EncounterInitial()) {
    on<DoEncounterEvent>(_getEncounterList);
  }

  FutureOr<void> _getEncounterList(
      DoEncounterEvent event, Emitter<EncounterState> emit) async {
    try {
      final response = await encounterRepository.getEncounters(
          event.count, event.page, event.search);
      emit(EncounterSuccess(response));
    } on Exception catch (e) {
      emit(EncounterError(e.toString()));
    }
  }
}
